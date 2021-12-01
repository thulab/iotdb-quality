package cn.edu.thu.iotdb.quality.string;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UDTFRegexMatch implements UDTF {
    private Pattern pattern;
    private int group;
    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        pattern = Pattern.compile(udfParameters.getString("regex"));
        group = udfParameters.getIntOrDefault("group", 0);
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.TEXT);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        Matcher matcher = pattern.matcher(row.getString(0));
        if (matcher.find() && matcher.groupCount() >= group) {
            collector.putString(row.getTime(), matcher.group(group));
        }
    }

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.TEXT)
                .validate(regex -> ((String) regex).length() > 0,
                        "regexp has to be a valid regular expression.",
                        validator.getParameters().getStringOrDefault("regex", ""))
                .validate(group -> (int) group >= 0,
                        "group index has to be a non-negative integer.",
                        validator.getParameters().getIntOrDefault("group", 0));
    }
}
