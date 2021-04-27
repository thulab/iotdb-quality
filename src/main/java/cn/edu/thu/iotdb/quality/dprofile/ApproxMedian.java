package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class ApproxMedian implements UDTF {

    protected GKArray sketch;
    protected TSDataType dataType;

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException, UDFInputSeriesDataTypeNotValidException {
        sketch = new GKArray(parameters.getDouble("error"));
        dataType = parameters.getDataType(0);
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        sketch.insert(Util.getValueAsDouble(row));
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, sketch.query(0.5));
    }
}
