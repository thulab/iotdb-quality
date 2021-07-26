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

public class UDTFSelfCorrelation implements UDTF {
    
    private DoubleArrayList valueArrayList = new DoubleArrayList();

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (row.isNull(0)) {
            valueArrayList.add(Double.NaN);
        } else {
            valueArrayList.add(Util.getValueAsDouble(row, 0));
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        DoubleArrayList correlationArrayList = UDTFCrossCorrelation.calculateCrossCorrelation(valueArrayList, valueArrayList);
        for (int i = 0; i < correlationArrayList.size(); i++) {
            collector.putDouble(i, correlationArrayList.get(i));
        }
    }

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
    public void beforeDestroy() {
        valueArrayList.clear();
    }
}
