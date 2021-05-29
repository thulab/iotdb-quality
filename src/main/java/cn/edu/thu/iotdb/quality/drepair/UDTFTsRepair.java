package cn.edu.thu.iotdb.quality.drepair;

import cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;


public class UDTFTsRepair implements UDTF {

    String intervalMethod;
    String startPointMethod;
    int interval;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(udfp.getDataType(0));
        intervalMethod = udfp.getStringOrDefault("interval_method", "Median");
        startPointMethod = udfp.getStringOrDefault("start_point_method", "Mode");
        interval = udfp.getIntOrDefault("interval", 0);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        //设置修复方法及参数
        int intervalMode;
        int startMode;
        if(interval>=1000){
            intervalMode=interval;
        }else if("Median".equalsIgnoreCase(intervalMethod)){
            intervalMode=1;
        } else if("Mode".equalsIgnoreCase(intervalMethod)) {
            intervalMode = 2;
        }else if("Cluster".equalsIgnoreCase(intervalMethod)) {
            intervalMode = 3;
        }else{
            throw new Exception("Illegal method.");
        }
        if("Linear".equalsIgnoreCase(startPointMethod)){
            startMode=1;
        } else if("Mode".equalsIgnoreCase(startPointMethod)) {
            startMode = 2;
        }else{
            throw new Exception("Illegal method.");
        }
        TimestampRepair ts=new TimestampRepair(rowWindow.getRowIterator(),intervalMode,startMode);
        ts.dpRepair();
        switch (rowWindow.getDataType(0)) {
            case DOUBLE:
                for (int i = 0; i < ts.repaired.length; i++) {
                    if(ts.repairedValue[i]==Double.NaN){
                        collector.putString(ts.repaired[i], "NAN");
                    }else {
                        collector.putDouble(ts.repaired[i], ts.repairedValue[i]);
                    }
                }
                break;
            case FLOAT:
                for (int i = 0; i < ts.repaired.length; i++) {
                    collector.putFloat(ts.repaired[i], (float) ts.repairedValue[i]);
                }
                break;
            case INT32:
                for (int i = 0; i < ts.repaired.length; i++) {
                    collector.putInt(ts.repaired[i], (int) ts.repairedValue[i]);
                }
                break;
            case INT64:
                for (int i = 0; i < ts.repaired.length; i++) {
                    collector.putLong(ts.repaired[i], (long) ts.repairedValue[i]);
                }
                break;
            default:
                throw new Exception();
        }
    }

}
