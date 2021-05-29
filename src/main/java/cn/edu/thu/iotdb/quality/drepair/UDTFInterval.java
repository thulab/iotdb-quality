package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFInterval implements UDTF {
    String intervalMethod;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(TSDataType.INT64);
        intervalMethod = udfp.getStringOrDefault("interval_method", "Median");
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        //设置修复方法及参数
        int intervalMode;
        if("Median".equalsIgnoreCase(intervalMethod)){
            intervalMode=1;
        } else if("Mode".equalsIgnoreCase(intervalMethod)) {
            intervalMode = 2;
        }else if("Cluster".equalsIgnoreCase(intervalMethod)) {
            intervalMode = 3;
        }else{
            throw new Exception("Illegal method.");
        }
        TimestampInterval ts=new TimestampInterval(rowWindow.getRowIterator());
        ts.getInterval(intervalMode);
        collector.putLong(rowWindow.getRow(0).getTime(), ts.deltaT);
    }
}
