package cn.edu.thu.iotdb.quality.dmatch;

import cn.edu.thu.iotdb.quality.util.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

public class UDTFPatternSymmetric implements UDTF {

    private int window;
    private double threshold;

    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesNumber(1)
                .validateInputSeriesDataType(0,
                        TSDataType.INT32,
                        TSDataType.INT64,
                        TSDataType.FLOAT,
                        TSDataType.DOUBLE)
                .validate(x -> (int) x > 0,
                        "window has to be greater than 0.",
                        validator.getParameters().getIntOrDefault("window", 10))
                .validate(x -> (double) x >= 0,
                        "threshold has to be greater than or equal to 0.",
                        validator.getParameters().getDoubleOrDefault("threshold", Double.MAX_VALUE));
    }

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        window = parameters.getIntOrDefault("window", 10);
        configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(window, 1))
                .setOutputDataType(TSDataType.DOUBLE);
        threshold = parameters.getDoubleOrDefault("threshold", Double.MAX_VALUE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        if (rowWindow.windowSize() < window) {//序列长度太短，不予计算
            return;
        }
        DoubleArrayList a = new DoubleArrayList();
        int n = rowWindow.windowSize();
        long time = rowWindow.getRow(0).getTime();
        for (int i = 0; i < n; i++) {
            Row row = rowWindow.getRow(i);
            a.add(Util.getValueAsDouble(row, 0));
        }
        int m = a.size();
        double[][] dp = new double[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][i] = 0;
            if (i < m) {
                dp[i][i + 1] = Math.pow(Math.abs(a.get(i - 1) - a.get(i)), 2);
            }
        }
        for (int len = 3; len <= m; len++) {
            for (int i = 1, j = len; j <= m; j++) {
                dp[i][j] = Math.pow(Math.abs(a.get(i - 1) - a.get(j - 1)), 2) + Math.min(Math.min(dp[i + 1][j], dp[i][j - 1]), dp[i + 1][j - 1]);
            }
        }
        if (dp[1][m] <= threshold) {
            collector.putDouble(time, dp[1][m]);
        }
    }
}
