package cn.edu.thu.dquality.udf;

import cn.edu.thu.dquality.GKArray;
import cn.edu.thu.dquality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate approximate quantile
 * the function has two parameters: $rank$ and $error$
 * $rank$ is the rank ratio of the quantile, e.g. a quantile with $rank$=0.5 is the median
 * $error$ is the rank error, e.g., a quantile with $rank$=0.5 and $error$=0.01 is the the quantile whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION quantile AS "cn.edu.thu.dquality.udf.UDAFQuantile"
 * @usage: SELECT quantile(s0, "rank"="0.2", "error"="0.01") FROM root.test;
 */
public class UDAFQuantile implements UDTF {

    private double rank;
    private GKArray gkArray;
    private Long lastTime;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        rank = udfParameters.getDoubleOrDefault("rank", 0.5);
        if(rank < 0 || rank > 1){
            throw new IllegalArgumentException("parameter $rank$ should be within [0,1]");
        }
        double error = udfParameters.getDoubleOrDefault("error", 0.01);
        if(error <= 0 || error >= 1){
            throw new IllegalArgumentException("parameter $error$ should be within (0,1)");
        }
        gkArray = new GKArray(error);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        lastTime = row.getTime();
        Double value = Util.getValueAsDouble(row);
        if(value != null && !Double.isNaN(value)){
            gkArray.insert(value);
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(lastTime, gkArray.query(rank));
    }
}