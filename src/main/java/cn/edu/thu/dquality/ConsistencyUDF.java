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
 *
 * @author Wang Haoyu
 */
public class ConsistencyUDF implements UDTF {

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.
                setAccessStrategy(new SlidingSizeWindowAccessStrategy(udfp.getIntOrDefault("window", Integer.MAX_VALUE)))
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        try {
            if (rowWindow.windowSize() > TimeSeriesQuality.WINDOWSIZE) {
                TimeSeriesQuality tsq = new TimeSeriesQuality(rowWindow.getRowIterator());
                tsq.timeDetect();
                collector.putDouble(rowWindow.getRow(0).getTime(), tsq.getConsistency());
            }
        } catch (IOException | NoNumberException ex) {
            Logger.getLogger(CompletenessUDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
