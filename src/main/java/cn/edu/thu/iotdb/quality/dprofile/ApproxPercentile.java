package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;

public class ApproxPercentile extends ApproxMedian {
    private double rank;

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException, UDFInputSeriesDataTypeNotValidException {
        super.beforeStart(parameters, configurations);
        rank = parameters.getDouble("rank");
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(startTime, sketch.query(rank));
    }
}