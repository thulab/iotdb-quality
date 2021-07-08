package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFTimestampRepair implements UDTF {

    String intervalMethod;
    String startPointMethod;
    int interval;
    int intervalMode;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> (Integer) x >= 0,
                        "Interval should be a positive integer.",
                        validator.getParameters().getIntOrDefault("interval", 0));
    }

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(udfp.getDataType(0));
        intervalMethod = udfp.getStringOrDefault("method", "Median");
        interval = udfp.getIntOrDefault("interval", 0);
        if (interval > 0) {
            intervalMode = interval;
        } else if ("Median".equalsIgnoreCase(intervalMethod)) {
            intervalMode = -1;
        } else if ("Mode".equalsIgnoreCase(intervalMethod)) {
            intervalMode = -2;
        } else if ("Cluster".equalsIgnoreCase(intervalMethod)) {
            intervalMode = -3;
        } else {
            throw new Exception("Illegal method.");
        }
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        //设置修复方法及参数
        TimestampRepair ts = new TimestampRepair(rowWindow.getRowIterator(), intervalMode, 2);
        ts.dpRepair();
        switch (rowWindow.getDataType(0)) {
            case DOUBLE:
                for (int i = 0; i < ts.repaired.length; i++) {
                    collector.putDouble(ts.repaired[i], ts.repairedValue[i]);
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
