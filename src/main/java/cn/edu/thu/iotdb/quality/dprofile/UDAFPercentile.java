package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * calculate approximate percentile
 * the function has two parameters: $rank$ and $error$
 * $rank$ is the rank ratio of the percentile, e.g. a percentile with $rank$=0.5 is the median
 * $error$ is the rank error, e.g., a percentile with $rank$=0.5 and $error$=0.01 is the the percentile whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION percentile AS "cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile"
 * @usage: SELECT percentile(s0, "rank"="0.2", "error"="0.01") FROM root.test;
 */
public class UDAFPercentile implements UDTF {

    private Double phi;
    private GKArray gkArray;
    private List<Double> cache;
    private Long startTime;
    private boolean approx;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        phi = udfParameters.getDouble("rank");
        if(phi == null || phi < 0 || phi > 1){
            throw new IllegalArgumentException("parameter $rank$ should be specified within [0,1]");
        }
        double error = udfParameters.getDoubleOrDefault("error", 0);
        if(error < 0 || error >= 1){
            throw new IllegalArgumentException("parameter $error$ should be within [0,1)");
        }
        approx = error > 0;
        if(approx){
            gkArray = new GKArray(error);
        }else{
            cache = new ArrayList<>();
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if(startTime == null){
            startTime = row.getTime();
        }
        Double value = Util.getValueAsDouble(row);
        if(value != null && !Double.isNaN(value)){
            if(approx){
                gkArray.insert(value);
            }else{
                cache.add(value);
            }
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if(approx){
            collector.putDouble(startTime, gkArray.query(phi));
        }else{
            if(cache.isEmpty()){
                throw new NoSuchElementException("No values in the time series");
            }
            cache.sort(Comparator.comparingDouble(x-> x));
            collector.putDouble(startTime, cache.get((int)(Math.ceil(cache.size() * phi) - 1)));
        }
    }
}