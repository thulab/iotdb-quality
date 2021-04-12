/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import java.util.LinkedHashSet;
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
public class UDTFDistinct implements UDTF {

    LinkedHashSet set = new LinkedHashSet<>();

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
                    set.add(row.getInt(0));
                    break;
                case INT64:
                    set.add(row.getLong(0));
                    break;
                case FLOAT:
                    set.add(row.getFloat(0));
                    break;
                case DOUBLE:
                    set.add(row.getDouble(0));
                    break;
                case TEXT:
                    set.add(row.getString(0));
                    break;
                case BOOLEAN:
                    set.add(row.getBoolean(0));
                    break;
            }
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        long i = 0;
        for (Object o : set) {
            i++;
            Util.putValue(pc, i, o);
        }
    }

}
