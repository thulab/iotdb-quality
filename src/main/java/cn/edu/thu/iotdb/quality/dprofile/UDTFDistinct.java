/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import java.io.IOException;
import java.util.HashSet;
import org.apache.iotdb.db.exception.query.QueryProcessException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.iterator.MutableDoubleIterator;
import org.eclipse.collections.api.iterator.MutableFloatIterator;
import org.eclipse.collections.api.iterator.MutableIntIterator;
import org.eclipse.collections.api.iterator.MutableLongIterator;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.DoubleHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.FloatHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;

/**
 *
 * @author Wang Haoyu
 */
public class UDTFDistinct implements UDTF {

    private IntHashSet intSet;
    private LongHashSet longSet;
    private FloatHashSet floatSet;
    private DoubleHashSet doubleSet;
    private BooleanHashSet booleanSet;
    private HashSet<String> stringSet;
    private TSDataType dataType;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1);
    }

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(udfp.getDataType(0));
        dataType = udfp.getDataType(0);
        switch (dataType) {
            case INT32:
                intSet = new IntHashSet();
                break;
            case INT64:
                longSet = new LongHashSet();
                break;
            case FLOAT:
                floatSet = new FloatHashSet();
                break;
            case DOUBLE:
                doubleSet = new DoubleHashSet();
                break;
            case TEXT:
                stringSet = new HashSet<>();
                break;
            case BOOLEAN:
                booleanSet = new BooleanHashSet();
        }
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
            case TEXT:
                transformString(row, pc);
                break;
            case BOOLEAN:
                transformBoolean(row, pc);
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        switch (dataType) {
            case INT32:
                terminateInt(pc);
                break;
            case INT64:
                terminateLong(pc);
                break;
            case FLOAT:
                terminateFloat(pc);
                break;
            case DOUBLE:
                terminateDouble(pc);
                break;
            case TEXT:
                terminateString(pc);
                break;
            case BOOLEAN:
                terminateBoolean(pc);
        }

    }

    private void transformInt(Row row, PointCollector pc) throws Exception {
        intSet.add(row.getInt(0));
    }

    private void transformLong(Row row, PointCollector pc) throws Exception {
        longSet.add(row.getLong(0));
    }

    private void transformFloat(Row row, PointCollector pc) throws Exception {
        floatSet.add(row.getFloat(0));
    }

    private void transformDouble(Row row, PointCollector pc) throws Exception {
        doubleSet.add(row.getDouble(0));
    }

    private void transformBoolean(Row row, PointCollector pc) throws Exception {
        booleanSet.add(row.getBoolean(0));
    }

    private void transformString(Row row, PointCollector pc) throws Exception {
        stringSet.add(row.getString(0));
    }

    private void terminateInt(PointCollector pc) throws IOException {
        MutableIntIterator iterator = intSet.intIterator();
        int i = 0;
        while (iterator.hasNext()) {
            pc.putInt(i, iterator.next());
            i++;
        }
    }

    private void terminateLong(PointCollector pc) throws IOException {
        MutableLongIterator iterator = longSet.longIterator();
        int i = 0;
        while (iterator.hasNext()) {
            pc.putLong(i, iterator.next());
            i++;
        }
    }

    private void terminateFloat(PointCollector pc) throws IOException {
        MutableFloatIterator iterator = floatSet.floatIterator();
        int i = 0;
        while (iterator.hasNext()) {
            pc.putFloat(i, iterator.next());
            i++;
        }
    }

    private void terminateDouble(PointCollector pc) throws IOException {
        MutableDoubleIterator iterator = doubleSet.doubleIterator();
        int i = 0;
        while (iterator.hasNext()) {
            pc.putDouble(i, iterator.next());
            i++;
        }
    }

    private void terminateString(PointCollector pc) throws QueryProcessException, IOException {
        int i = 0;
        for (String s : stringSet) {
            pc.putString(i, s);
            i++;
        }
    }

    private void terminateBoolean(PointCollector pc) throws IOException {
        MutableBooleanIterator iterator = booleanSet.booleanIterator();
        int i = 0;
        while (iterator.hasNext()) {
            pc.putBoolean(i, iterator.next());
            i++;
        }
    }

}
