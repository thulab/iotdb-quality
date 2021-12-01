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
    流式转换需用户提供最大值最小值，全局转换则不需要
 */
public class UDTFMinMax implements UDTF{
    ArrayList<Double> value=new ArrayList<>();
    ArrayList<Long> timestamp=new ArrayList<>();
    String method="batch";
    double min=0.0d;
    double max=0.0d;
    boolean f=true;
    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        value.clear();
        timestamp.clear();
        min=0.0d;
        max=0.0d;
        f=true;
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        method = udfParameters.getStringOrDefault("method","batch");
        if(method.equalsIgnoreCase("stream")){
            min=udfParameters.getDouble("min");
            max=udfParameters.getDouble("max");
        }
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        if (method.equalsIgnoreCase("stream")&&max>min){
            collector.putDouble(row.getTime(), (Util.getValueAsDouble(row)-min)/(max-min));
        }
        else if (method.equalsIgnoreCase("batch")){
            double v=Util.getValueAsDouble(row);
            value.add(v);
            timestamp.add(row.getTime());
            if(f){
                min=v;
                max=v;
                f=false;
            }
            else{
                if(v>max){
                    max=v;
                }
                else if(v<min){
                    min=v;
                }
            }
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        if (method.equalsIgnoreCase("batch")&&max>min){
            for(int i=0; i<value.size();i++){
                collector.putDouble(timestamp.get(i),(value.get(i)-min)/(max-min));
            }
        }
    }
}
