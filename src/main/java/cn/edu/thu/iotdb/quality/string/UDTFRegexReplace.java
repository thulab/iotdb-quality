package cn.edu.thu.iotdb.quality.string;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UDTFRegexReplace implements UDTF {

    private String regex;
    private Pattern pattern;
    private String replace;
    private int limit;
    private int offset;
    private boolean reverse;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        regex = udfParameters.getString("regex");
        pattern = Pattern.compile(regex);
        replace = udfParameters.getString("replace");
        limit = udfParameters.getIntOrDefault("limit", -1);
        offset = udfParameters.getIntOrDefault("offset", 0);
        reverse = udfParameters.getBooleanOrDefault("reverse", false);
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.TEXT);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        String origin = row.getString(0);
        Matcher matcher = pattern.matcher(origin);
        String result;
        if (reverse) {
            IntArrayList endIndexList = new IntArrayList();
            while (matcher.find()) {
                endIndexList.add(matcher.end());
            }
            String suffix;
            if (endIndexList.size() < offset + 1) {
                suffix = origin;
            } else {
                suffix = origin.substring(endIndexList.get(endIndexList.size() - offset - 1));
            }
            String prefix;
            if (limit == -1 || endIndexList.size() < limit + offset + 1) {
                prefix = "";
            } else {
                prefix = origin.substring(0, endIndexList.get(endIndexList.size() - limit - offset - 1));
            }
            result = prefix.concat(origin.substring(prefix.length(), origin.length() - suffix.length()).replaceAll(regex, replace)).concat(suffix);
        } else {
            IntArrayList fromIndexList = new IntArrayList();
            while (matcher.find() && fromIndexList.size() < limit + offset + 2) {
                fromIndexList.add(matcher.start());
            }
            String prefix;
            if (fromIndexList.size() < offset + 1) {
                prefix = origin;
            } else {
                prefix = origin.substring(0, fromIndexList.get(offset));
            }
            String suffix;
            if (limit == -1 || fromIndexList.size() < limit + offset + 1) {
                // replace all left matches
                suffix = "";
            } else {
                suffix = origin.substring(fromIndexList.get(limit + offset));
            }
            result = prefix.concat(origin.substring(prefix.length(), origin.length() - suffix.length()).replaceAll(regex, replace)).concat(suffix);
        }
        collector.putString(row.getTime(), result);
    }

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.TEXT)
                .validate(regex -> ((String) regex).length() > 0,
                        "regex should not be empty",
                        validator.getParameters().getString("regex"))
                .validate(limit -> (int) limit >= -1,
                        "limit has to be -1 for replacing all matches or non-negative integers for limited times.",
                        validator.getParameters().getIntOrDefault("limit", -1))
                .validate(offset -> (int) offset >= 0,
                        "offset has to be non-negative to skip first several matches.",
                        validator.getParameters().getIntOrDefault("offset", 0));
    }
}
