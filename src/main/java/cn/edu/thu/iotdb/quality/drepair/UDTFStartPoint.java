package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

public class UDTFStartPoint implements UDTF {

    String startPointMethod;
    int interval;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(udfp.getDataType(0));
        interval = udfp.getIntOrDefault("interval_method", 0);
        startPointMethod = udfp.getStringOrDefault("start_point_method", "Mode");
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        //设置修复方法及参数
        int startMode;
        if("Linear".equalsIgnoreCase(startPointMethod)){
            startMode=1;
        } else if("Mode".equalsIgnoreCase(startPointMethod)) {
            startMode = 2;
        }else{
            throw new Exception("Illegal method.");
        }
        TimestampInterval ts=new TimestampInterval(rowWindow.getRowIterator());
        if(interval!=0) {
            ts.deltaT=interval;
        }else {
            ts.getInterval(1);
        }
        ts.getStart0(startMode);
        collector.putLong(ts.time[0], ts.start0);
    }

}
