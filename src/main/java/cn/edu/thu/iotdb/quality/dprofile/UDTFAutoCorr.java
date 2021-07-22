/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/**
 *
 * @author Wang Haoyu
 */
public class UDTFAutoCorr implements UDTF {

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.FLOAT, TSDataType.DOUBLE, TSDataType.INT32, TSDataType.INT64);
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(TSDataType.DOUBLE);

    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        DoubleArrayList value = new DoubleArrayList();
        RowIterator iterator = rowWindow.getRowIterator();
        while (iterator.hasNextRow()) {
            Row row = iterator.next();
            double v = Util.getValueAsDouble(row);
            value.add(v);
        }
        double corr[] = autoCorrelation(value.toArray());
        for (int i = 0; i < corr.length; i++) {
            collector.putDouble(i, corr[i]);
        }
    }

    /**
     * 计算输入序列的自相关序列
     *
     * @param x 输入序列
     * @return 自相关序列
     */
    private double[] autoCorrelation(double x[]) {
        double[] corr = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            corr[i] = pearson(x, x.length - i);
        }
        return corr;
    }

    /**
     * 指定子序列长度，计算输入序列的前缀子序列和后缀子序列的Pearson相关系数
     *
     * @param x 输入序列
     * @param subLength 子序列长度
     * @return Pearson相关系数
     */
    private double pearson(double x[], int subLength) {
        double sum_x = 0, sum_y = 0, sum_xx = 0, sum_yy = 0, sum_xy = 0;
        int s1 = 0, s2 = x.length - subLength;
        for (int i = 0; i < subLength; i++) {
            sum_x += x[s1 + i];
            sum_y += x[s2 + i];
            sum_xx += x[s1 + i] * x[s1 + i];
            sum_yy += x[s2 + i] * x[s2 + i];
            sum_xy += x[s1 + i] * x[s2 + i];
        }
        return (subLength * sum_xy - sum_x * sum_y) / Math.sqrt(subLength * sum_xx - sum_x * sum_x) / Math.sqrt(subLength * sum_yy - sum_y * sum_y);
    }

}
