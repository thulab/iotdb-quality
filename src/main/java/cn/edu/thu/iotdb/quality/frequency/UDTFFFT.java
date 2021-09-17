/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.frequency;

import cn.edu.thu.iotdb.quality.Util;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import java.util.Arrays;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/**
 *
 * @author Wang Haoyu
 */
public class UDTFFFT implements UDTF {

    private String result;
    private boolean compress;
    private final DoubleArrayList list = new DoubleArrayList();

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> ((String) x).equalsIgnoreCase("uniform") || ((String) x).equalsIgnoreCase("nonuniform"),
                        "Type should be 'uniform' or 'nonuniform'.",
                        validator.getParameters().getStringOrDefault("type", "uniform"))
                .validate(x -> "real".equalsIgnoreCase((String) x) || "imag".equalsIgnoreCase((String) x) || "abs".equalsIgnoreCase((String) x) || "angle".equalsIgnoreCase((String) x),
                        "Result should be 'real', 'imag', 'abs' or 'angle'.",
                        validator.getParameters().getStringOrDefault("result", "abs"));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        this.result = parameters.getStringOrDefault("result", "abs");
        this.compress = parameters.getBooleanOrDefault("compress", false);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        double v = Util.getValueAsDouble(row);
        if (Double.isFinite(v)) {
            list.add(v);
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        int n = list.size();
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        //准备数据，每个数占用两个double
        double[] a = new double[2 * n];
        for (int i = 0; i < n; i++) {
            a[2 * i] = list.get(i);
            a[2 * i + 1] = 0;
        }
        fft.complexForward(a);
        if (compress) {
            outputCompressed(collector, a);
        } else {
            outputUncompressed(collector, a);
        }
    }

    private void outputCompressed(PointCollector collector, double a[]) {
        int n = a.length / 2;
    }

    private void outputUncompressed(PointCollector collector, double a[]) throws Exception {
        int n = a.length / 2;
        for (int i = 0; i < n; i++) {
            double ans = 0;
            switch (result) {
                case "real":
                    ans = a[i * 2];
                    break;
                case "imag":
                    ans = a[i * 2 + 1];
                    break;
                case "abs":
                    ans = Math.sqrt(a[i * 2] * a[i * 2] + a[2 * i + 1] * a[2 * i + 1]);
                    break;
                case "angle":
                    ans = Math.atan2(a[2 * i + 1], a[2 * i]);
                    break;
                default:
                    throw new Exception("It's impossible");
            }
            collector.putDouble(i, ans);
        }
    }

}
