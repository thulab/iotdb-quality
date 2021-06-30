/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Wang Haoyu
 */
public class UDTFResample implements UDTF {

    private Resampler resampler;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> (long) x > 0,
                        "gap should be a time period whose unit is ms, s, m, h, d.",
                        Util.parseTime(validator.getParameters().getString("every")))
                .validate(x -> "min".equals(x) || "max".equals(x) || "mean".equals(x) || "median".equals(x) || "first".equals(x) || "last".equals(x),
                        "aggr should be min, max, mean, median, first, last.",
                        validator.getParameters().getStringOrDefault("aggr", "mean").toLowerCase())
                .validate(x -> "nan".equals(x) || "ffill".equals(x) || "bfill".equals(x) || "linear".equals(x),
                        "aggr should be min, max, mean, median, first, last.",
                        validator.getParameters().getStringOrDefault("interp", "nan").toLowerCase());
    }

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        long newPeriod = Util.parseTime(udfp.getString("every"));
        String aggregator = udfp.getStringOrDefault("aggr", "mean").toLowerCase();
        String interpolator = udfp.getStringOrDefault("interp", "nan").toLowerCase();
        resampler = new Resampler(newPeriod, aggregator, interpolator);
    }

    @Override
    public void transform(Row row, PointCollector pc) throws Exception {
        resampler.insert(row.getTime(), Util.getValueAsDouble(row));
        while (resampler.hasNext()) {//尽早输出，以减少输出缓冲的压力
            pc.putDouble(resampler.getOutTime(), resampler.getOutValue());
            resampler.next();
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        resampler.flush();
        while (resampler.hasNext()) {
            pc.putDouble(resampler.getOutTime(), resampler.getOutValue());
            resampler.next();
        }
    }

}
