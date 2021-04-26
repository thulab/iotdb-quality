package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate the exact percentile
 * the function has a parameter: $rank$
 * $rank$ is the rank ratio of the percentile, e.g. a percentile with $rank$=0.5 is the median
 * @register: CREATE FUNCTION percentile AS "cn.edu.thu.iotdb.quality.dprofile.UDAFExactPercentile"
 * @usage: SELECT percentile(s0, "rank"="0.2") FROM root.test;
 */
public class UDAFExactPercentile extends UDAFExactMedian {

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
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws UDFInputSeriesDataTypeNotValidException, MetadataException {
        super.beforeStart(parameters,configurations);
        rank = parameters.getDouble("rank");
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        switch (dataType) {
            case INT32:
                collector.putDouble(startTime, ExactOrderStatistics.getPercentile(intArrayList, rank));
                break;
            case INT64:
                collector.putDouble(startTime, ExactOrderStatistics.getPercentile(longArrayList, rank));
                break;
            case FLOAT:
                collector.putDouble(startTime, ExactOrderStatistics.getPercentile(floatArrayList, rank));
                break;
            case DOUBLE:
                collector.putDouble(startTime, ExactOrderStatistics.getPercentile(doubleArrayList, rank));
                break;
            default:
                throw new UDFInputSeriesDataTypeNotValidException(
                        0,
                        dataType,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE);
        }
    }
}