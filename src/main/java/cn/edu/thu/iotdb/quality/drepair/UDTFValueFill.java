/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.drepair;


import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

/**
 * 给所有的UDTF的填补函数设定统一的接口，方便调用。
 *
 * @author Zhang Xiaojian 
 */
public class UDTFValueFill implements UDTF {

    private String method;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(udfp.getDataType(0));
        method = udfp.getStringOrDefault("method", "ffill");
//        beforeRange = udfp.getLongOrDefault("beforeRange", Long.MAX_VALUE);
//        afterRange = udfp.getLongOrDefault("afterRange", Long.MAX_VALUE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        ValueFill vf = null;
        if("ffill".equalsIgnoreCase(method)){
            vf = new PreviousFill(rowWindow.getRowIterator());
        }
        else if("linear".equalsIgnoreCase(method)){
            vf = new LinearFill(rowWindow.getRowIterator());
        }
        else if("mean".equalsIgnoreCase(method)){
            vf = new MeanFill(rowWindow.getRowIterator());
        }
        else if("ar".equalsIgnoreCase(method)){
            vf = new ARFill(rowWindow.getRowIterator());
        }
        else {
            throw new Exception("Illegal method");
        }
        vf.fill();
        double[] repaired = vf.getFilled();
        long[] time = vf.getTime();
        switch (rowWindow.getDataType(0)) {
            case DOUBLE:
                for (int i = 0; i < time.length; i++) {
                    collector.putDouble(time[i], repaired[i]);
                }
                break;
            case FLOAT:
                for (int i = 0; i < time.length; i++) {
                    collector.putFloat(time[i], (float) repaired[i]);
                }
                break;
            case INT32:
                for (int i = 0; i < time.length; i++) {
                    collector.putInt(time[i], (int) Math.round(repaired[i]));
                }
                break;
            case INT64:
                for (int i = 0; i < time.length; i++) {
                    collector.putLong(time[i], Math.round(repaired[i]));
                }
                break;
            default:
                throw new Exception();
        }
    }
}