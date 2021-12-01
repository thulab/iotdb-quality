package cn.edu.thu.iotdb.quality.string;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFSplit implements UDTF {

    private String regex;
    private int index;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        regex = udfParameters.getString("regex");
        index = udfParameters.getIntOrDefault("index", -1);
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy());
        if (index == -1) {
            udtfConfigurations.setOutputDataType(TSDataType.INT32);
        } else {
            udtfConfigurations.setOutputDataType(TSDataType.TEXT);
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        String[] splitResult = row.getString(0).split(regex);
        if (index == -1) {
            collector.putInt(row.getTime(), splitResult.length);
        } else {
            if (index < splitResult.length) {
                collector.putString(row.getTime(), splitResult[index]);
            }
        }
    }

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.TEXT)
                .validate(regex -> ((String) regex).length() > 0,
                        "regexp has to be a valid regular expression.",
                        validator.getParameters().getStringOrDefault("regex", ""))
                .validate(index -> (int) index >= -1,
                        "index must a non-negative integer to fetch split results or -1 to get length.",
                        validator.getParameters().getIntOrDefault("index", -1));
    }
}
