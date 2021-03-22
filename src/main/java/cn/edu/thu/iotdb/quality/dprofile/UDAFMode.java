/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;

/**
 *
 * @author Wang Haoyu
 */
public class UDAFMode implements UDTF {

    HashMap<Object, Integer> map = new LinkedHashMap<>();

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(udfp.getDataType(0));
    }

    @Override
    public void transform(Row row, PointCollector pc) throws Exception {
        if (!row.isNull(0)) {
            switch (row.getDataType(0)) {
                case INT32:
                    map.put(row.getInt(0), map.getOrDefault(row.getInt(0), 0) + 1);
                    break;
                case INT64:
                    map.put(row.getLong(0), map.getOrDefault(row.getLong(0), 0) + 1);
                    break;
                case FLOAT:
                    map.put(row.getFloat(0), map.getOrDefault(row.getFloat(0), 0) + 1);
                    break;
                case DOUBLE:
                    map.put(row.getDouble(0), map.getOrDefault(row.getDouble(0), 0) + 1);
                    break;
                case TEXT:
                    map.put(row.getString(0), map.getOrDefault(row.getString(0), 0) + 1);
                    break;
                case BOOLEAN:
                    map.put(row.getBoolean(0), map.getOrDefault(row.getBoolean(0), 0) + 1);
                    break;
            }
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        //寻找出现次数最多的元素
        int maxTimes = 0;
        Object o = null;
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Object key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxTimes) {
                maxTimes = value;
                o = key;
            }
        }
        //输出
        if (o instanceof Integer) {
            pc.putInt(0, (Integer) o);
        } else if (o instanceof Long) {
            pc.putLong(0, (Long) o);
        } else if (o instanceof Float) {
            pc.putFloat(0, (Float) o);
        } else if (o instanceof Double) {
            pc.putDouble(0, (Double) o);
        } else if (o instanceof String) {
            pc.putString(0, (String) o);
        } else if (o instanceof Boolean) {
            pc.putBoolean(0, (Boolean) o);
        }
    }

}
