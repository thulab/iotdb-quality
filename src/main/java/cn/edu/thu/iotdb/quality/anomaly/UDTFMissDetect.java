/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.anomaly;

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
public class UDTFMissDetect implements UDTF {

    private int minLength;
    private StreamMissDetector detector;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
                .validate(x -> (int) x >= 10,
                        "minlen should be an integer greater than or equal to 10.",
                        validator.getParameters().getIntOrDefault("minlen", 10));
    }

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.BOOLEAN);
        this.minLength = udfp.getIntOrDefault("minlen", 10);
        this.detector = new StreamMissDetector(minLength);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        detector.insert(row.getTime(), Util.getValueAsDouble(row));
        while (detector.hasNext()) {//尽早输出，以减少输出缓冲的压力
            collector.putBoolean(detector.getOutTime(), detector.getOutValue());
            detector.next();
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        detector.flush();
        while (detector.hasNext()) {//尽早输出，以减少输出缓冲的压力
            collector.putBoolean(detector.getOutTime(), detector.getOutValue());
            detector.next();
        }
    }

}
