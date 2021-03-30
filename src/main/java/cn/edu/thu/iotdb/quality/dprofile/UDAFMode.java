/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
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
            Object key = Util.getValueAsObject(row);
            map.put(key, map.getOrDefault(key, 0) + 1);
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
        Util.putValue(pc, 0, o);
    }

}
