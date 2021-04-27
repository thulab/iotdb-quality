package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate the exact or approximate median
 * the function has an optional parameter: $error$
 * $error$ is the rank error, e.g., a median with $error$=0.01 is the element whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION median AS "cn.edu.thu.iotdb.quality.dprofile.UDAFMedian"
 * @usage: SELECT median(s0, "error"="0.01") FROM root.test;
 */
public class UDAFMedian implements UDTF {
    private UDTF median;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception{
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
        double error = parameters.getDoubleOrDefault("error", 0);
        if(error == 0){
            median = new ExactMedian();
        }else{
            median = new ApproxMedian();
        }
        median.beforeStart(parameters,configurations);
    }

    public void transform(Row row, PointCollector collector) throws Exception{
        median.transform(row, collector);
    }

    public void terminate(PointCollector collector) throws Exception {
        median.terminate(collector);
    }
}
