/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.NoNumberException;
import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 *
 * @author Wang Haoyu
 */
public class UDAFSpread implements UDTF {

    double min = Double.MAX_VALUE, max = -Double.MAX_VALUE;
    TSDataType type;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        type = udfp.getDataType(0);
        if (type == TSDataType.BOOLEAN || type == TSDataType.TEXT) {
            throw new NoNumberException();
        }
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(type);
    }

    @Override
    public void transform(Row row, PointCollector pc) throws Exception {
        Double v = Util.getValueAsDouble(row);
        if (v != null && Double.isFinite(v)) {
            min = min < v ? min : v;
            max = max > v ? max : v;
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        if (max >= min) {
            switch (type) {
                case INT32:
                    pc.putInt(0, (int) (max - min));
                    break;
                case INT64:
                    pc.putLong(0, (long) (max - min));
                    break;
                case FLOAT:
                    pc.putFloat(0, (float) (max - min));
                    break;
                case DOUBLE:
                    pc.putDouble(0, (double) (max - min));
                    break;
                default:
                    throw new NoNumberException();
            }
        } else {
            pc.putDouble(0, Double.NaN);
        }
    }

}
