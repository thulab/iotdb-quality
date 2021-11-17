package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.util.Util;
import java.util.ArrayList;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

/*
    流式转换需用户提供均值和标准差，全局转换则不需要
 */
public class UDTFZScore implements UDTF{
    ArrayList<Double> value=new ArrayList<>();
    ArrayList<Long> timestamp=new ArrayList<>();
    String method="batch";
    double avg=0.0d;
    double sd=0.0d;
    double sum=0.0d;
    double square_sum=0.0d;
    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        value.clear();
        timestamp.clear();
        sum=0.0d;
        square_sum=0.0d;
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        method = udfParameters.getStringOrDefault("method","batch");
        if(method.equalsIgnoreCase("stream")){
            avg=udfParameters.getDouble("avg");
            sd=udfParameters.getDouble("sd");
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (method.equalsIgnoreCase("stream")&&sd>0){
            collector.putDouble(row.getTime(), (Util.getValueAsDouble(row)-avg)/sd);
        }
        else if (method.equalsIgnoreCase("batch")){
            double v=Util.getValueAsDouble(row);
            value.add(v);
            timestamp.add(row.getTime());
            sum+=v;
            square_sum+=v*v;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if (method.equalsIgnoreCase("batch")){
            avg=sum/value.size();
            sd=Math.sqrt(square_sum/value.size()-avg*avg);
            for(int i=0; i<value.size();i++){
                collector.putDouble(timestamp.get(i),(value.get(i)-avg)/sd);
            }
        }
    }
}
