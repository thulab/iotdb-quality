package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.util.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFHistogram implements UDTF {

    private int[] bucket;
    private double gap;
    private int count;
    private double start;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE)
                .validate(count -> (int) count > 0,
                        "parameter $count$ should be larger than 0",
                        validator.getParameters().getIntOrDefault("count", 1))
                .validate(params -> (double) params[0] <= (double) params[1],
                        "parameter $end$ should be larger than or equal to $start$",
                        validator.getParameters().getDoubleOrDefault("start", -Double.MAX_VALUE),
                        validator.getParameters().getDoubleOrDefault("end", Double.MAX_VALUE));
    }

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.INT32);
        start = udfParameters.getDoubleOrDefault("start", -Double.MAX_VALUE);
        double end = udfParameters.getDoubleOrDefault("end", Double.MAX_VALUE);
        count = udfParameters.getIntOrDefault("count", 1);
        bucket = new int[count];
        gap = (end - start) / count;
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        double value = Util.getValueAsDouble(row);
        if (Double.isFinite(value)) {
            int id = Math.min(Math.max((int) Math.floor((value - start) / gap), 0), count - 1);
            bucket[id]++;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        for (int i = 0; i < count; i++) {
            collector.putInt(i, bucket[i]);
        }
    }
}
