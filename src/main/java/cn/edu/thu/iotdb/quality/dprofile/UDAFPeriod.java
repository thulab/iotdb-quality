/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.commons.math3.util.Pair;
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
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

/**
 *
 * @author Wang Haoyu
 */
public class UDAFPeriod implements UDTF {

    private final double threshold = 0.5;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.FLOAT, TSDataType.DOUBLE, TSDataType.INT32, TSDataType.INT64);
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(TSDataType.INT32);

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
        int peeks[] = findPeeks(corr).toArray();
        int period = 0;
        if (peeks.length > 1) {
            int gap[] = Util.variation(peeks);
            period = (int) new IntArrayList(gap).median();
        }
        collector.putInt(0, period);
    }

    /**
     * @param x 输入数据
     * @return 若干个峰的位置
     */
    private IntArrayList findPeeks(double x[]) {
        int window = 100;
        IntArrayList peeks = new IntArrayList();
        peeks.add(0);
        for (int i = 1; i < Math.min(x.length - 1, window); i++) {
            if (x[i] > x[i - 1] && x[i] > x[i + 1] && x[i] > threshold) {
                window = i;
                break;
            }
        }
        for (int i = 0; i + window <= x.length; i++) {//滑动窗口            
            double v = x[i + window / 2];
            if (v > threshold) {
                Pair<Double, Integer> p = max(x, i, i + window);
                if (p.getSecond() == i + window / 2) {
                    peeks.add(p.getSecond());
                }
            }
        }
        return peeks;
    }

    /**
     * 寻找数组在指定索引范围内的最大值及其索引
     *
     * @param x 数组
     * @param startIndex 开始位置的索引（包含）
     * @param endIndex 结束位置的索引（不包含）
     * @return （最大值，索引）
     */
    private Pair<Double, Integer> max(double x[], int startIndex, int endIndex) {
        double maxValue = -Double.MAX_VALUE;
        int maxIndex = -1;
        for (int i = startIndex; i < endIndex; i++) {
            if (x[i] > maxValue) {
                maxValue = x[i];
                maxIndex = i;
            }
        }
        return Pair.create(x[maxIndex], maxIndex);
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
