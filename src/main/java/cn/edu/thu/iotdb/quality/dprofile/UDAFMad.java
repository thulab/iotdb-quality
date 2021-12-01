package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate the exact or approximate median absolute deviation (mad) the
 * function has an optional parameter: $error$ $error$ is the desired relative
 * error, e.g., an approxMAD with $error$=0.01 satisfies 0.99 * MAD <= approxMAD
 * <= 1.01 * MAD @register
 *
 * CREATE FUNCTION mad AS "cn.edu.thu.iotdb.quality.dprofile.UDAFMad"
 * @usage: select mad(s0, "error"="0.01") from root.test;
 */
public class UDAFMad implements UDTF {

    private ExactOrderStatistics statistics;
    private MADSketch sketch;
    private boolean exact;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE)
                .validate(error -> (double) error >= 0 && (double) error < 1,
                        "error has to be greater than or equal to 0 and less than 1.",
                        validator.getParameters().getDoubleOrDefault("error", 0));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy()).setOutputDataType(TSDataType.DOUBLE);
        double error = parameters.getDoubleOrDefault("error", 0);
        exact = (error == 0);
        if (exact) {
            statistics = new ExactOrderStatistics(parameters.getDataType(0));
        } else {
            sketch = new MADSketch(error);
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (exact) {
            statistics.insert(row);
        } else {
            sketch.insert(Util.getValueAsDouble(row));
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if (exact) {
            collector.putDouble(0, statistics.getMad());
        } else {
            collector.putDouble(0, sketch.getMad().result);
        }
    }
}
