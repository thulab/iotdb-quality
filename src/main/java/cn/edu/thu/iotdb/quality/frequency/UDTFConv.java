/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.frequency;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/**
 *
 * @author Wang Haoyu
 */
public class UDTFConv implements UDTF {

    private final DoubleArrayList list1 = new DoubleArrayList();
    private final DoubleArrayList list2 = new DoubleArrayList();

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(2)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validateInputSeriesDataType(1, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64);
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (!row.isNull(0) && Double.isFinite(Util.getValueAsDouble(row, 0))) {
            list1.add(Util.getValueAsDouble(row, 0));
        }
        if (!row.isNull(1) && Double.isFinite(Util.getValueAsDouble(row, 1))) {
            list2.add(Util.getValueAsDouble(row, 1));
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        double ans[] = new double[list1.size() + list2.size() - 1];
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                ans[i + j] += list1.get(i) * list2.get(j);
            }
        }
        for (int i = 0; i < ans.length; i++) {
            collector.putDouble(i, ans[i]);
        }
    }

}
