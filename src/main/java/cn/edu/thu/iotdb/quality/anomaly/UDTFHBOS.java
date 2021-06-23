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
public class UDTFHBOS implements UDTF {
    private int[][] bucket;
    private double[] score;
    private double[] gap;
    private int count;
    private double[] start;
    private double[] end;
    private int dim;
    @Override
    public void validate(UDFParameterValidator validator) throws Exception {

    }
    //改用window读取
    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.INT32);
        dim=udfParameters.getPaths().size();
        String[] start_s=udfParameters.getString("start").split(",");
        String[] end_s=udfParameters.getString("end").split(",");
        count = udfParameters.getIntOrDefault("count", 1);
        start=new double[dim];
        end=new double[dim];
        for(int i=0;i<dim;i++){
            start[i]=Double.parseDouble(start_s[i]);
            end[i]=Double.parseDouble(end_s[i]);
            gap[i]=(end[i]-start[i])/count;
        }
        bucket = new int[dim][count];
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        for (int i=0;i<dim;i++){

        }
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
