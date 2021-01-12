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
 * 计算取值变化速度的IOTDB UDF函数
 *
 * @author Wang Haoyu
 */
public class SpeedUDF implements UDTF {

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
                double v0 = Util.getValueAsDouble(rowWindow.getRow(0));
                double v1 = Util.getValueAsDouble(rowWindow.getRow(1));
                long t0 = rowWindow.getRow(0).getTime();
                long t1 = rowWindow.getRow(1).getTime();
                double u = (v1 - v0) / (t1 - t0);
                collector.putDouble(rowWindow.getRow(0).getTime(), u);
            } catch (IOException | NoNumberException ex) {
                Logger.getLogger(SpeedUDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
