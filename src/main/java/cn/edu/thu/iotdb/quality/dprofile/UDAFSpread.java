/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.util.NoNumberException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/**
 *
 * @author Wang Haoyu
 */
public class UDAFSpread implements UDTF {

    int intMin = Integer.MAX_VALUE, intMax = Integer.MIN_VALUE;
    long longMin = Long.MAX_VALUE, longMax = Long.MIN_VALUE;
    float floatMin = Float.MAX_VALUE, floatMax = -Float.MAX_VALUE;
    double doubleMin = Double.MAX_VALUE, doubleMax = -Double.MAX_VALUE;
    TSDataType dataType;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE);
    }

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        dataType = udfp.getDataType(0);
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(dataType);
    }

    @Override
    public void transform(Row row, PointCollector pc) throws Exception {
        switch (dataType) {
            case INT32:
                transformInt(row, pc);
                break;
            case INT64:
                transformLong(row, pc);
                break;
            case FLOAT:
                transformFloat(row, pc);
                break;
            case DOUBLE:
                transformDouble(row, pc);
                break;
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        switch (dataType) {
            case INT32:
                pc.putInt(0, intMax - intMin);
                break;
            case INT64:
                pc.putLong(0, longMax - longMin);
                break;
            case FLOAT:
                pc.putFloat(0, floatMax - floatMin);
                break;
            case DOUBLE:
                pc.putDouble(0, doubleMax - doubleMin);
                break;
            default:
                throw new NoNumberException();
        }
    }

    private void transformInt(Row row, PointCollector pc) throws Exception {
        int v = row.getInt(0);
        intMin = intMin < v ? intMin : v;
        intMax = intMax > v ? intMax : v;
    }

    private void transformLong(Row row, PointCollector pc) throws Exception {
        long v = row.getLong(0);
        longMin = longMin < v ? longMin : v;
        longMax = longMax > v ? longMax : v;
    }

    private void transformFloat(Row row, PointCollector pc) throws Exception {
        float v = row.getFloat(0);
        if (Float.isFinite(v)) {
            floatMin = floatMin < v ? floatMin : v;
            floatMax = floatMax > v ? floatMax : v;
        }
    }

    private void transformDouble(Row row, PointCollector pc) throws Exception {
        double v = row.getDouble(0);
        if (Double.isFinite(v)) {
            doubleMin = doubleMin < v ? doubleMin : v;
            doubleMax = doubleMax > v ? doubleMax : v;
        }
    }

}
