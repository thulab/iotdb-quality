package cn.edu.thu.iotdb.quality.dmatch;

import cn.edu.thu.iotdb.quality.Util;
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

public class UDAFDtw implements UDTF {

    private double[][] dp;
    private int m;
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
        configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                .setOutputDataType(parameters.getDataType(0));
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        DoubleArrayList a = new DoubleArrayList(),b = new DoubleArrayList();
        int n = rowWindow.windowSize();
        for(int i=0;i<n;i++){
            Row row = rowWindow.getRow(i);
            if (row.isNull(0) || row.isNull(1)) {//当出现null时，跳过这一行，同时确保getValue不会出错
                continue;
            }
            a.add(Util.getValueAsDouble(row, 0));
            b.add(Util.getValueAsDouble(row, 1));
        }
        m = a.size();
        dp = new double[m+1][m+1];
        for (int i=1;i<=m;i++)
            dp[0][i]=dp[i][0]=Double.MAX_VALUE;
        dp[0][0] = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=m;j++){
                dp[i][j] = Math.abs(a.get(i)-b.get(j))+Math.min(Math.min(dp[i][j-1],dp[i+1][j]),dp[i+1][j-1]);
            }
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, dp[m][m]);
    }
}
