package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFMedianOfTimeDifference implements UDTF {

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(TSDataType.INT64);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        TimestampInterval ts=new TimestampInterval(rowWindow.getRowIterator());
        ts.getInterval(1);
        collector.putLong(rowWindow.getRow(0).getTime(), ts.deltaT);
    }
}
