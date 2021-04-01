package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDAFSkew implements UDTF{

    private long count = 0;
    private double sum_x_3 = 0.0;
    private double sum_x_2 = 0.0;
    private double sum_x_1 = 0.0;

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        Double value = Util.getValueAsDouble(row);
        if (value != null && !Double.isNaN(value)) {
            this.count++;
            this.sum_x_1+=value;
            this.sum_x_2+=value*value;
            this.sum_x_3+=value*value*value;
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        collector.putDouble(0, (sum_x_3/count-3*sum_x_1/count*sum_x_2/count+2*Math.pow(sum_x_1/count,3))/Math.pow(sum_x_2/count-sum_x_1/count*sum_x_1/count,1.5));
    }
}
