/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.exception.metadata.MetadataException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.exception.UDFInputSeriesDataTypeNotValidException;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

/**
 *
 * @author Wang Haoyu
 */
public abstract class Exact implements UDTF {

    protected TSDataType dataType;
    protected FloatArrayList floatArrayList;
    protected DoubleArrayList doubleArrayList;
    protected IntArrayList intArrayList;
    protected LongArrayList longArrayList;

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws MetadataException, UDFInputSeriesDataTypeNotValidException {
        dataType = parameters.getDataType(0);
        switch (dataType) {
            case INT32:
                intArrayList = new IntArrayList();
                break;
            case INT64:
                longArrayList = new LongArrayList();
                break;
            case FLOAT:
                floatArrayList = new FloatArrayList();
                break;
            case DOUBLE:
                doubleArrayList = new DoubleArrayList();
                break;
            default:
                // This will not happen.
                throw new UDFInputSeriesDataTypeNotValidException(0, dataType, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
        }
        configurations.setAccessStrategy(new RowByRowAccessStrategy()).setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        switch (dataType) {
            case INT32:
                intArrayList.add(row.getInt(0));
                break;
            case INT64:
                longArrayList.add(row.getLong(0));
                break;
            case FLOAT:
                float vf = row.getFloat(0);
                if (Float.isFinite(vf)) { //跳过NaN
                    floatArrayList.add(vf);
                }
                break;
            case DOUBLE:
                double vd = row.getDouble(0);
                if (Double.isFinite(vd)) { //跳过NaN
                    doubleArrayList.add(vd);
                }
                break;
            default:
                // This will not happen.
                throw new UDFInputSeriesDataTypeNotValidException(0, dataType, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
        }
    }

}
