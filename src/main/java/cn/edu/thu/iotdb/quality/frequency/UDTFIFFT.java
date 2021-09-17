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

/**
 *
 * @author Wang Haoyu
 */
public class UDTFIFFT implements UDTF {

    private boolean compressed;
    private final DoubleArrayList real = new DoubleArrayList();
    private final DoubleArrayList imag = new DoubleArrayList();

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(2)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> Boolean.FALSE.equals(x) || Boolean.TRUE.equals(x),
                        "Compress should be 'true' or 'false'.",
                        validator.getParameters().getBooleanOrDefault("compressed", false));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        this.compressed = parameters.getBooleanOrDefault("compressed", false);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (!row.isNull(0) && !row.isNull(1) && Double.isFinite(Util.getValueAsDouble(row, 0)) && Double.isFinite(Util.getValueAsDouble(row, 1))) {
            real.add(Util.getValueAsDouble(row, 0));
            imag.add(Util.getValueAsDouble(row, 1));
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if (compressed) {
            decompress();
        }
        int n = real.size();
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        //准备数据，每个数占用两个double
        double[] a = new double[2 * n];
        for (int i = 0; i < n; i++) {
            a[2 * i] = real.get(i);
            a[2 * i + 1] = imag.get(i);
        }
        fft.complexInverse(a, true);
        for (int i = 0; i < n; i++) {
            collector.putDouble(i, a[2 * i]);
        }
    }

    private void decompress() {

    }

}
