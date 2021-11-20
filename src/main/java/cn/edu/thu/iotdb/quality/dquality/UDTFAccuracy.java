package cn.edu.thu.iotdb.quality.dquality;

import cn.edu.thu.iotdb.quality.NoNumberException;
import cn.edu.thu.iotdb.quality.Util;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.iotdb.db.metadata.PartialPath;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingTimeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * 用于计算时间序列的准确性性的UDTF
 *
 * @author Ma Wenxuan
 */
public class UDTFAccuracy implements UDTF {

    private int colCnt = 0;
    private String constraints = "$1 = $2";

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        colCnt = udfp.getPaths().size();
        if (udfp.hasAttribute("constraints")) {
            constraints = udfp.getString("constraints");
        }

        boolean isTime = false;
        long window = Integer.MAX_VALUE;
        if (udfp.hasAttribute("window")) {
            String s = udfp.getString("window");
            window = Util.parseTime(s);
            if (window > 0) {
                isTime = true;
            } else {
                window = Long.parseLong(s);
            }
        }
        if (isTime){
            udtfc.setAccessStrategy(new SlidingTimeWindowAccessStrategy(window));
        }else{
            udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy((int) window));
        }       
        udtfc.setOutputDataType(TSDataType.DOUBLE);
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
