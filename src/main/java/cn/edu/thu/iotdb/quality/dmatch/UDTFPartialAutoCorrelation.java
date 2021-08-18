package cn.edu.thu.iotdb.quality.dmatch;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;

public class UDTFPartialAutoCorrelation implements UDTF {

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {

    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        UDTF.super.transform(row, collector);
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        UDTF.super.terminate(collector);
    }
}
