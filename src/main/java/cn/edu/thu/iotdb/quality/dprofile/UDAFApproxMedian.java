package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate the approximate median
 * the function has one parameter: $error$
 * $error$ is the rank error, e.g., a median with $error$=0.01 is the element whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION approx_median AS "cn.edu.thu.iotdb.quality.dprofile.UDAFApproxMedian"
 * @usage: SELECT approx_median(s0, "error"="0.01") FROM root.test;
 */
public class UDAFApproxMedian implements UDTF {
    protected GKArray sketch;
    protected Long startTime;
    protected TSDataType dataType;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception{
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE)
                .validateRequiredAttribute("error")
                .validate(error -> (double) error > 0 && (double) error < 1,
                        "error has to be greater than 0 and less than 1.",
                        validator.getParameters().getDouble("error"));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException, UDFInputSeriesDataTypeNotValidException {
        sketch = new GKArray(parameters.getDouble("error"));
        dataType = parameters.getDataType(0);
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if(startTime == null){
            startTime = row.getTime();
        }
        switch (dataType) {
            case INT32:
                sketch.insert(row.getInt(0));
                break;
            case INT64:
                sketch.insert(row.getLong(0));
                break;
            case FLOAT:
                sketch.insert(row.getFloat(0));
                break;
            case DOUBLE:
                sketch.insert(row.getDouble(0));
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

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(startTime, sketch.query(0.5));
    }
}