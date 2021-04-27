package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class ApproxMad implements UDTF {

    private MADSketch sketch;
    private Long startTime;
    private TSDataType dataType;
    
    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException {
        dataType = parameters.getDataType(0);
        sketch = new MADSketch(parameters.getDouble("error"));
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }
    
    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (startTime == null) {
            startTime = row.getTime();
        }
        sketch.insert(Util.getValueAsDouble(row));
    }
    
    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, sketch.getMad().result);
    }
    
}
