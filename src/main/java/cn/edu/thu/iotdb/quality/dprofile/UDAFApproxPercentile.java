package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;

/**
 * calculate the approximate percentile
 * the function has two parameters: $rank$ and $error$
 * $rank$ is the rank ratio of the percentile, e.g. a percentile with $rank$=0.5 is the median
 * $error$ is the rank error, e.g., a percentile with $rank$=0.5 and $error$=0.01 is the the percentile whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION approx_percentile AS "cn.edu.thu.iotdb.quality.dprofile.UDAFApproxPercentile"
 * @usage: SELECT percentile(s0, "rank"="0.2", "error"="0.01") FROM root.test;
 */
public class UDAFApproxPercentile extends UDAFApproxMedian {
    private double rank;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception{
        super.validate(validator);
        validator.validateRequiredAttribute("rank")
                .validate(rank -> (double) rank > 0 && (double) rank <= 1,
                        "rank has to be greater than 0 and less than or equal to 1.",
                        validator.getParameters().getDouble("rank"));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException, UDFInputSeriesDataTypeNotValidException {
        super.beforeStart(parameters, configurations);
        rank = parameters.getDouble("rank");
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(startTime, sketch.query(rank));
    }
}