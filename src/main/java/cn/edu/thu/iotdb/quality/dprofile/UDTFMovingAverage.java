package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.util.Queue;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
public class UDTFMovingAverage implements UDTF{
    int n=-1;
    TSDataType dataType;
    Queue v;
    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {

        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        dataType = udfParameters.getDataType(0);
        n=udfParameters.getInt("n");
        v=new Queue(n,dataType);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        long t=row.getTime();
        if(v.isFull()){
            v.pop();
        }
        switch (dataType){
            case FLOAT:
                v.push(t, row.getFloat(0));
                break;
            case DOUBLE:
                v.push(t, row.getDouble(0));
                break;
            case INT32:
                v.push(t, row.getInt(0));
                break;
            case INT64:
                v.push(t, row.getLong(0));
                break;
            default:
                break;
        }
        if(v.isFull()){
            collector.putDouble(t,v.getSum()/(double)n);
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {

    }
}
