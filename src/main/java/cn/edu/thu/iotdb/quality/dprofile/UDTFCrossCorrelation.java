package cn.edu.thu.iotdb.quality.dprofile;

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

public class UDTFCrossCorrelation implements UDTF {
    
    private DoubleArrayList valueArrayList0 = new DoubleArrayList();
    private DoubleArrayList valueArrayList1 = new DoubleArrayList();

    static public DoubleArrayList calculateCrossCorrelation(DoubleArrayList valueArrayList1, DoubleArrayList valueArrayList2) {
        DoubleArrayList correlationArrayList = new DoubleArrayList();
        int length = valueArrayList1.size();
        for (int shift = 1; shift <= length; shift++) {
            double correlation = 0.0;
            for (int i = 0; i < shift; i++) {
                if (Double.isFinite(valueArrayList1.get(i)) && Double.isFinite(valueArrayList2.get(length - shift + i))) {
                    correlation += valueArrayList1.get(i) * valueArrayList2.get(length - shift + i);
                }
            }
            correlation /= shift;
            correlationArrayList.add(correlation);
        }
        for (int shift = 1; shift < length; shift++) {
            double correlation = 0.0;
            for (int i = 0; i < length - shift; i++) {
                if (Double.isFinite(valueArrayList1.get(shift + i)) && Double.isFinite(valueArrayList2.get(i))) {
                    correlation += valueArrayList1.get(shift + i) * valueArrayList2.get(i);
                }
            }
            correlation = correlation / length;
            correlationArrayList.add(correlation);
        }
        return correlationArrayList;
    }

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (row.isNull(0) || row.isNull(1)) {
            valueArrayList0.add(Double.NaN);
            valueArrayList1.add(Double.NaN);
        } else {
            valueArrayList0.add(Util.getValueAsDouble(row, 0));
            valueArrayList1.add(Util.getValueAsDouble(row, 1));
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        DoubleArrayList correlationArrayList = calculateCrossCorrelation(valueArrayList0, valueArrayList1);
        for (int i = 0; i < correlationArrayList.size(); i++) {
            collector.putDouble(i, correlationArrayList.get(i));
        }
    }

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(2)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE)
                .validateInputSeriesDataType(1,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE);
    }

    @Override
    public void beforeDestroy() {
        valueArrayList0.clear();
        valueArrayList1.clear();
    }
}
