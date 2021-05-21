/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;

/**
 * 用于填补时间序列缺失值的UDTF：利用前一个数据点的值进行填补。
 *
 * @author Wang Haoyu & Zhang Xiaojian
 */
public class PreviousFill extends ValueFill {

    private long previousTime = -1;
    private double previousValue;

    public PreviousFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
    }

    public PreviousFill(String filename) throws Exception {
        super(filename);
    }

    @Override
    public void fill() {
        // 如果开头是NaN，则直接忽略不考虑；-> 这个规则参照pd.Dataframe.fillna函数的定义
        for(int i = 0; i < original.length; i++){
            if(Double.isNaN(original[i])){
                if(previousTime == -1) { // 序列初始为空，直接pass
                    repaired[i] = original[i];
                }
                else{
                    repaired[i] = previousValue;
                }
            }
            else{
                previousTime = time[i];
                previousValue = original[i];
                repaired[i] = original[i];
            }
        }
    }
//    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
//        udtfc.setAccessStrategy(new RowByRowAccessStrategy())
//                .setOutputDataType(udfp.getDataType(0));
//        beforeRange = udfp.getLongOrDefault("beforeRange", Long.MAX_VALUE);
//    }
//
//    public void transform(Row row, PointCollector pc) throws Exception {
//        switch (row.getDataType(0)) {
//            case DOUBLE:
//                if (row.isNull(0) || Double.isNaN(row.getDouble(0))) {//需要进行填补
//                    if (previousTime > 0 && row.getTime() - previousTime <= beforeRange) {//可以进行填补
//                        pc.putDouble(row.getTime(), previousValue1);
//                    } else {
//                        pc.putDouble(row.getTime(), Double.NaN);
//                    }
//                } else {//不需要进行填补
//                    previousTime = row.getTime();
//                    previousValue1 = row.getDouble(0);
//                    pc.putDouble(previousTime, previousValue1);
//                }
//                break;
//            case FLOAT:
//                if (row.isNull(0) || Float.isNaN(row.getFloat(0))) {//需要进行填补
//                    if (previousTime > 0 && row.getTime() - previousTime <= beforeRange) {//可以进行填补
//                        pc.putFloat(row.getTime(), previousValue2);
//                    } else {
//                        pc.putFloat(row.getTime(), Float.NaN);
//                    }
//                } else {//不需要进行填补
//                    previousTime = row.getTime();
//                    previousValue2 = row.getFloat(0);
//                    pc.putFloat(previousTime, previousValue2);
//                }
//                break;
//            default:
//                throw new Exception();
//        }
//    }
}
