package cn.edu.thu.iotdb.quality.dquality;

import cn.edu.thu.iotdb.quality.NoNumberException;
import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingTimeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用于计算时间序列的准确性性的UDTF
 *
 * @author Ma Wenxuan
 */
public class UDTFAccuracy implements UDTF {

    private int colCnt;
    private String constraints;

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        String sizeWindowStr = parameters.getStringOrDefault("sizewindow", String.valueOf(Long.MAX_VALUE));
        long sizeWindow = Long.parseLong(sizeWindowStr);

        String timeWindowStr = parameters.getStringOrDefault("timewindow", "default");
        long timeWindow = Util.parseTime(timeWindowStr);

        if (parameters.hasAttribute("sizewindow")) {
            configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy((int) sizeWindow));
        }
        else {
            configurations.setAccessStrategy(new SlidingTimeWindowAccessStrategy(timeWindow));
        }
        colCnt = parameters.getPaths().size();
        constraints = parameters.getStringOrDefault("constraints", "$1==$2");
        configurations.setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        try {
            if (rowWindow.windowSize() > MultipleTimeSeriesQuality.WINDOWSIZE) {
                MultipleTimeSeriesQuality tsq = new MultipleTimeSeriesQuality(rowWindow.getRowIterator(), colCnt, constraints);
                collector.putDouble(rowWindow.getRow(0).getTime(), tsq.getAccuracy());
            }
        } catch (IOException | NoNumberException ex) {
            Logger.getLogger(UDTFCompleteness.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
