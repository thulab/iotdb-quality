package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDAFStddev implements UDTF {
    private Long lastTime;
    private long count = 0;
    private double mean = 0.0;
    private double std = 0.0;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        lastTime = row.getTime();
        Double value = Util.getValueAsDouble(row);
        if(value != null && !Double.isNaN(value)){
            this.count++;
            this.std = Math.sqrt((Math.pow(this.std, 2) * (this.count - 1) + (this.count - 1) * 1.0 / this.count * Math.pow(value - this.mean, 2)) / this.count);
            this.mean += (value - this.mean) / this.count;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(lastTime, this.std);
    }
}
