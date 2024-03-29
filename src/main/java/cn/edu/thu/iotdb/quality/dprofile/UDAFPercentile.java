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
 * calculate the approximate percentile the function has two parameters: $rank$
 * and $error$ (optional) $rank$ is the rank ratio of the percentile, e.g. a
 * percentile with $rank$=0.5 is the median $error$ is the rank error, e.g., a
 * percentile with $rank$=0.5 and $error$=0.01 is the the percentile whose rank
 * is within (0.49,0.51)
 *
 * @register: CREATE FUNCTION percentile AS
 * "cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile"
 * @usage: SELECT percentile(s0, "rank"="0.2", "error"="0.01") FROM root.test;
 */
public class UDAFPercentile implements UDTF {

    private ExactOrderStatistics statistics;
    private GKArray sketch;
    private boolean exact;
    private double rank;

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
                        validator.getParameters().getDoubleOrDefault("error", 0))
                .validate(rank -> (double) rank > 0 && (double) rank <= 1,
                        "rank has to be greater than 0 and less than or equal to 1.",
                        validator.getParameters().getDoubleOrDefault("rank", 0.5));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        double error = parameters.getDoubleOrDefault("error", 0);
        rank = parameters.getDoubleOrDefault("rank", 0.5);
        exact = (error == 0);
        if (exact) {
            statistics = new ExactOrderStatistics(parameters.getDataType(0));
        } else {
            sketch = new GKArray(error);
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
            collector.putDouble(0, statistics.getPercentile(rank));
        } else {
            collector.putDouble(0, sketch.query(rank));
        }
    }
}
