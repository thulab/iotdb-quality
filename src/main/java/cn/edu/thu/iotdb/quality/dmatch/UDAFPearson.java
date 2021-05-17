/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dmatch;

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
public class UDAFPearson implements UDTF {

    private long count = 0;
    private double sum_x = 0.0;
    private double sum_y = 0.0;
    private double sum_xy = 0.0;
    private double sum_xx = 0.0;
    private double sum_yy = 0.0;

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
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (row.isNull(0) || row.isNull(1)) {//当出现null时，跳过这一行，同时确保getValue不会出错
            return;
        }
        double x = Util.getValueAsDouble(row, 0);
        double y = Util.getValueAsDouble(row, 1);
        if (Double.isFinite(x) && Double.isFinite(y)) {//当出现NaN等时，跳过这一行
            count++;
            sum_x += x;
            sum_y += y;
            sum_xy += x * y;
            sum_xx += x * x;
            sum_yy += y * y;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if (count > 0) {//只有当有效行数大于等于1时，总体协方差才有意义
            double pearson = (count * sum_xy - sum_x * sum_y) / Math.sqrt(count * sum_xx - sum_x * sum_x) / Math.sqrt(count * sum_yy - sum_y * sum_y);
            collector.putDouble(0, pearson);
        } else {
            collector.putDouble(0, Double.NaN);
        }
    }
}
