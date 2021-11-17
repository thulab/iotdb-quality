/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.frequency;

import cn.edu.thu.iotdb.quality.util.Util;
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
public class UDTFDeconv implements UDTF {

    private final DoubleArrayList list1 = new DoubleArrayList();
    private final DoubleArrayList list2 = new DoubleArrayList();
    private String result;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(2)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validateInputSeriesDataType(1, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> ((String) x).equalsIgnoreCase("quotient") || ((String) x).equalsIgnoreCase("remainder"),
                        "Result should be 'quotient' or 'remainder'.",
                        validator.getParameters().getStringOrDefault("result", "quotient"));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        this.result = parameters.getStringOrDefault("result", "quotient");
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
        if (list2.size() == 0) {//被零除异常
            throw new Exception("Divided by zero.");
        } else if (list2.size() > list1.size()) {//除数的阶高于被除数
            if (result.equalsIgnoreCase("quotient")) {//商
                collector.putDouble(0, 0);
            } else {//余数
                for (int i = 0; i < list1.size(); i++) {
                    collector.putDouble(i, list1.get(i));
                }
            }
        } else {//除数的阶低于或等于被除数
            double q[] = new double[list1.size() - list2.size() + 1];
            double r[] = list1.toArray();
            int m = list2.size() - 1;
            for (int i = q.length - 1; i >= 0; i--) {
                q[i] = r[i + m] / list2.get(m);
                r[i + m] = 0;
                for (int j = 0; j < m; j++) {
                    r[i + j] -= q[i] * list2.get(j);
                }
            }
            if (result.equalsIgnoreCase("quotient")) {//商
                for (int i = 0; i < q.length; i++) {
                    collector.putDouble(i, q[i]);
                }
            } else {//余数
                for (int i = 0; i < r.length; i++) {
                    collector.putDouble(i, r[i]);
                }
            }
        }
    }

}
