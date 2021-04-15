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
 * calculate approximate median
 * the function has one parameter: $error$
 * $error$ is the rank error, e.g., a median with $error$=0.01 is the element whose rank is within (0.49,0.51)
 * @register: CREATE FUNCTION median AS "cn.edu.thu.iotdb.quality.dprofile.UDAFMedian"
 * @usage: SELECT median(s0, "error"="0.01") FROM root.test;
 */
public class UDAFMedian implements UDTF {
    private GKArray gkArray;
    private List<Double> cache;
    private Long startTime;
    private boolean approx;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
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
            collector.putDouble(startTime, gkArray.query(0.5));
        }else{
            if(cache.isEmpty()){
                throw new NoSuchElementException("No values in the time series");
            }
            cache.sort(Comparator.comparingDouble(x-> x));
            int rank = (int)(Math.ceil(cache.size() * 0.5) - 1);
            if((cache.size() & 1) == 0){
                collector.putDouble(startTime, (cache.get(rank) + cache.get(rank + 1)) / 2.0);
            }else{
                collector.putDouble(startTime, cache.get(rank));
            }
        }
    }
}