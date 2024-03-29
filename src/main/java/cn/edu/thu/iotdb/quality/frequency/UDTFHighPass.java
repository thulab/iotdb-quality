/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.frequency;

import cn.edu.thu.iotdb.quality.Util;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

/**
 *
 * @author Wang Haoyu
 */
public class UDTFHighPass implements UDTF {

    private double wpass;
    private final DoubleArrayList valueList = new DoubleArrayList();
    private final LongArrayList timeList = new LongArrayList();

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validateRequiredAttribute("wpass")
                .validate(x -> (double) x > 0 && (double) x < 1,
                        "Wpass should be within (0,1).",
                        validator.getParameters().getDouble("wpass"));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        this.wpass = parameters.getDouble("wpass");
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        double v = Util.getValueAsDouble(row);
        if (Double.isFinite(v)) {
            valueList.add(v);
            timeList.add(row.getTime());
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        int n = valueList.size();
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        //准备数据，每个数占用两个double
        double[] a = new double[2 * n];
        for (int i = 0; i < n; i++) {
            a[2 * i] = valueList.get(i);
            a[2 * i + 1] = 0;
        }
        fft.complexForward(a);
        //清除低频成分
        int m = (int) Math.floor(wpass * n / 2);
        for (int i = 0; i <= 2 * m + 1; i++) {
            a[i] = 0;
        }
        for (int i = 2 * (n - m); i < 2 * n; i++) {
            a[i] = 0;
        }
        fft.complexInverse(a, true);
        //输出
        for (int i = 0; i < n; i++) {
            collector.putDouble(timeList.get(i), a[i * 2]);
        }

    }

}
