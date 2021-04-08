package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.NoNumberException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import static cn.edu.thu.iotdb.quality.Util.getValueAsDouble;

/**
 * calculate the integral or the area under the curve of input series
 * $unit$ is the time scale for the area calculation, chosen from 1s(second, default), 1m(minute), 1h(hour), 1d(day)
 * @register: CREATE FUNCTION INTEGRAL AS "cn.edu.thu.iotdb.quality.dprofile.UDTFIntegral"
 * @usage: SELECT INTEGRAL(s0, "unit"="1s") FROM root.test
 */
public class UDAFIntegral implements UDTF {

    private static final String TIME_UNIT_KEY="unit";
    private static final String TIME_UNIT_MS="1S";
    private static final String TIME_UNIT_S="1s";
    private static final String TIME_UNIT_M="1m";
    private static final String TIME_UNIT_H="1H";
    private static final String TIME_UNIT_D="1d";

    Long unitTime;
    Long lastTime;
    Double lastValue;
    Double integralValue;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy());
        switch (udfParameters.getDataType(0)) {
            case INT32:
            case INT64:
            case FLOAT:
            case DOUBLE:
                switch (udfParameters.getStringOrDefault(TIME_UNIT_KEY, TIME_UNIT_S).toLowerCase()) {
                    case TIME_UNIT_MS:
                        unitTime = 1L;
                    case TIME_UNIT_S:
                        unitTime = 1000L;
                        break;
                    case TIME_UNIT_M:
                        unitTime = 60000L;
                        break;
                    case TIME_UNIT_H:
                        unitTime = 3600000L;
                        break;
                    case TIME_UNIT_D:
                        unitTime = 3600000L*24L;
                        break;
                    default:
                        throw new Exception("Unknown time unit input: "+udfParameters.getStringOrDefault(TIME_UNIT_KEY, TIME_UNIT_S).toLowerCase());
                }
                udtfConfigurations.setOutputDataType(TSDataType.DOUBLE);
                lastTime = null;
                lastValue = null;
                integralValue = 0D;
                break;
            case BOOLEAN:
            case TEXT:
                throw new NoNumberException();
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (!row.isNull(0)) {
            long nowTime = row.getTime();
            double nowValue = getValueAsDouble(row);
            // skip NaN values
            if (Double.isNaN(nowValue)) {
                return;
            }
            // calculate the ladder-shaped area between last point and this one
            // skip and initialize the memory if no existing previous point is available
            if (lastValue != null) {
                integralValue += (lastValue + nowValue) * (nowTime - lastTime) / 2.0 / unitTime;
            }
            lastTime = nowTime;
            lastValue = nowValue;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, integralValue);//所有UDAF函数的时间戳都默认为0
    }

}
