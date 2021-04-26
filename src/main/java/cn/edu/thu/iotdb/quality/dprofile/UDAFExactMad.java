package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 * calculate the exact median absolute deviation (mad)
 * @register CREATE FUNCTION mad AS "cn.edu.thu.iotdb.quality.dprofile.UDAFExactMad"
 * @usage: select mad(s0) from root.test;
 */
public class UDAFExactMad extends UDAFExactMedian {
    @Override
    public void terminate(PointCollector collector) throws Exception {
        switch (dataType) {
            case INT32:
                collector.putDouble(startTime, ExactOrderStatistics.getMad(intArrayList));
                break;
            case INT64:
                collector.putDouble(startTime, ExactOrderStatistics.getMad(longArrayList));
                break;
            case FLOAT:
                collector.putDouble(startTime, ExactOrderStatistics.getMad(floatArrayList));
                break;
            case DOUBLE:
                collector.putDouble(startTime, ExactOrderStatistics.getMad(doubleArrayList));
                break;
            default:
                throw new UDFInputSeriesDataTypeNotValidException(
                        0,
                        dataType,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE);
        }
    }
}