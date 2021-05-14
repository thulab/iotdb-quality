package cn.edu.thu.iotdb.quality.anomaly;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;

public class UDTFRange implements UDTF{
    private TSDataType dataType;
    private double upperBound;
    private double lowerBound;
    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE);
    }

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(udfParameters.getDataType(0));
        this.lowerBound = udfParameters.getDouble("lower_bound");
        this.upperBound = udfParameters.getDouble("upper_bound");
        this.dataType = udfParameters.getDataType(0);
    }

    public void transform(Row row, PointCollector collector) throws Exception {
        int intValue;
        long longValue;
        float floatValue;
        double doubleValue;
        long timestamp;
        timestamp = row.getTime();
        switch (dataType){
            case INT32:
                intValue = row.getInt(0);
                if (intValue>upperBound||intValue<lowerBound){
                    Util.putValue(collector,dataType,timestamp, intValue);
                }
                break;
            case INT64:
                longValue = row.getLong(0);
                if (longValue>upperBound||longValue<lowerBound){
                    Util.putValue(collector,dataType,timestamp, longValue);
                }
                break;
            case FLOAT:
                floatValue = row.getFloat(0);
                if (floatValue>upperBound||floatValue<lowerBound){
                    Util.putValue(collector,dataType,timestamp, floatValue);
                }
                break;
            case DOUBLE:
                doubleValue = row.getDouble(0);
                if (doubleValue>upperBound||doubleValue<lowerBound){
                    Util.putValue(collector,dataType,timestamp, doubleValue);
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void terminate(PointCollector collector) throws Exception {}
}
