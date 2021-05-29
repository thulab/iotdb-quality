package cn.edu.thu.iotdb.quality.drepair;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;

public class UDTFTimeDifference implements UDTF {
    protected long time[];

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(TSDataType.INT64);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        try {
            RowIterator dataIterator = rowWindow.getRowIterator();
            ArrayList<Long> timeList = new ArrayList<>();
            ArrayList<Double> originList = new ArrayList<>();
            int count = 0;
            long last = 0;
            while (dataIterator.hasNextRow()) {//读取数据
                count += 1;
                Row row = dataIterator.next();
                Double v = Util.getValueAsDouble(row);
                timeList.add(row.getTime());
                if (v == null || !Double.isFinite(v)) {//对空值的处理和特殊值的处理
                    originList.add(Double.NaN);
                } else {
                    originList.add(v);
                }
            }
            this.time = Util.toLongArray(timeList);
            for(int i=0;i<this.time.length-2;i++){
                collector.putLong(this.time[i+1],this.time[i+1]-this.time[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
