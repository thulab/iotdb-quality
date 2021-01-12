/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.dquality;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * 计算采集间隔的IOTDB UDF函数
 *
 * @author Wang Haoyu
 */
public class IntervalUDF implements UDTF {

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) {
        configurations
                .setAccessStrategy(new SlidingSizeWindowAccessStrategy(2, 1))
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) {
        if (rowWindow.windowSize() == 2) {
            try {
                long t0 = rowWindow.getRow(0).getTime();
                long t1 = rowWindow.getRow(1).getTime();
                double u = t1 - t0;
                collector.putDouble(rowWindow.getRow(0).getTime(), u);
            } catch (IOException ex) {
                Logger.getLogger(IntervalUDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
