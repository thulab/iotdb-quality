/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.dquality.udf;

import cn.edu.thu.dquality.LsGreedy;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

/**
 * 用于修复时间序列异常值的UDTF：利用基于速度变化似然的LsGreedy方法进行修复。
 *
 * @author Wang Haoyu
 */
public class UDTFLsGreedyRepair implements UDTF {

    double center, sigma;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(udfp.getDataType(0));
        center = udfp.getDoubleOrDefault("center", 0);
        sigma = udfp.getDoubleOrDefault("sigma", Double.NaN);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        LsGreedy lsGreedy = new LsGreedy(rowWindow.getRowIterator());
        if (!Double.isNaN(sigma)) {
            lsGreedy.setSigma(sigma);
        }
        lsGreedy.setCenter(center);
        lsGreedy.repair();
        double[] repaired = lsGreedy.getRepaired();
        long[] time = lsGreedy.getTime();
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
