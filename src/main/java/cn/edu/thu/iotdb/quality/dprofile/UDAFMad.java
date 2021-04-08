package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;


/**
 * calculate approximate median absolute deviation (mad) the function has two
 * parameters: $error$ is the relative error, e.g., an approxMAD with $error$=0.01
 * satisfies 0.99 * MAD <= approxMAD <= 1.01 * MAD
 * @register CREATE FUNCTION mad AS "cn.edu.thu.iotdb.quality.dprofile.UDAFMad"
 * @usage: select mad(s0, "error"="0.01") from root.test;
 */
public class UDAFMad implements UDTF {
    private MADSketch madSketch;
    private Long startTime;
    private double result;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) {
        udtfConfigurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(udfParameters.getIntOrDefault("window", Integer.MAX_VALUE)))
                .setOutputDataType(TSDataType.DOUBLE);
        double error = udfParameters.getDoubleOrDefault("error", 0);
        if (error < 0 || error > 1) {
            throw new IllegalArgumentException("parameter $error$ should be within [0,1]");
        }
//        int bucketNum = udfParameters.getIntOrDefault("bucketNum", 2048);
//        if (bucketNum <= 0) {
//            throw new IllegalArgumentException("parameter $error$ should be positive)");
//        }
        madSketch = new MADSketch(error);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        if (rowWindow.windowSize() > 0) {
            startTime = rowWindow.getRow(0).getTime();
            if (madSketch.getAlpha() > 0) {
                RowIterator iterator = rowWindow.getRowIterator();
                while (iterator.hasNextRow()) {
                    Double value = Util.getValueAsDouble(iterator.next());
                    if (value != null && !Double.isNaN(value)) {
                        madSketch.insert(value);
                    }
                }
                Mad mad = madSketch.getMad();
                if (mad.error_bound <= madSketch.getAlpha()) {
                    result = mad.error_bound;
                    return;
                }

                if (madSketch.isAppropriate()) {
                    double[] bounds = madSketch.getValid_range();
                    madSketch = new MADSketch(madSketch.getBeta() * madSketch.getAlpha());
                    iterator = rowWindow.getRowIterator();
                    while (iterator.hasNextRow()) {
                        Double value = Util.getValueAsDouble(iterator.next());
                        if (value != null && !Double.isNaN(value)) {
                            madSketch.insert(value, bounds);
                        }
                    }
                    result = madSketch.getMad().result;
                } else {
                    result = ExactMAD.mad(rowWindow.getRowIterator(), (int) madSketch.total_count());
                }
            } else {
                result = ExactMAD.mad(rowWindow.getRowIterator());
            }
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, result);//所有UDAF函数的时间戳都默认为0
    }
}