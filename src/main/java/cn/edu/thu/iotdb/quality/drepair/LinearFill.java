/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.drepair;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.internal.$Gson$Preconditions;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

/**
 * 用于填补时间序列缺失值的UDTF：利用前一个和后一个数据点的值进行线性插值填补。
 *
 * @author Wang Haoyu
 */
public class LinearFill extends ValueFill {

//    protected int n;
//    protected long time[];
//    protected double original[];
//    protected double repaired[];
    private int prevNotNaN = -1;

    public LinearFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
    }

    public LinearFill(String filename) throws Exception {
        super(filename);
    }

    @Override
    public void fill() {
        // 默认等间隔的插值
        for(int i = 0; i < original.length; i++){
            if(!Double.isNaN(original[i])){
                double k = 0;
                if(prevNotNaN > 0){
                    k = original[i] - original[prevNotNaN];
                    k /= i - prevNotNaN;
                    //System.out.print(k);
                }
                int t = prevNotNaN + 1;
                while(t < i){
                    repaired[t] = original[i] + k * (t - i);
                    t++;
                }
                repaired[i] = original[i];
                prevNotNaN = i;
            }
        }
        if(prevNotNaN < original.length - 1 && prevNotNaN >= 0){
            int t = prevNotNaN;
            while(t <= original.length - 1){
                repaired[t] = original[prevNotNaN];
                t++;
            }
        }
    }

//    public static void main(String args[]) throws Exception {
//        ValueFill vf = new LinearFill("./temp.csv");
//        vf.fill();
//        System.out.print(vf.repaired[0]);
//    }

//    @Override
//    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
//        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
//                .setOutputDataType(udfp.getDataType(0));
//        beforeRange = udfp.getLongOrDefault("beforeRange", Long.MAX_VALUE);
//        afterRange = udfp.getLongOrDefault("afterRange", Long.MAX_VALUE);
//    }
//
//    @Override
//    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
//        RowIterator iterator = rowWindow.getRowIterator();
//        switch (rowWindow.getDataType(0)) {
//            case DOUBLE:
//                fillDouble(iterator, collector);
//                break;
//            case FLOAT:
//                fillFloat(iterator, collector);
//                break;
//            default:
//                throw new Exception();
//        }
//    }
//
//    private void fillDouble(RowIterator iterator, PointCollector collector) throws IOException {
//        ArrayList<Long> fillList = new ArrayList<>();
//        double previousValue = Double.NaN, nextValue = Double.NaN;
//        long previousTime = -1, nextTime = -1;
//        while (iterator.hasNextRow()) {
//            Row row = iterator.next();
//            long t = row.getTime();
//            double v = row.getDouble(0);
//            if (Double.isNaN(v)) {
//                fillList.add(t);
//            } else {
//                previousTime = nextTime;
//                previousValue = nextValue;
//                nextTime = t;
//                nextValue = v;
//                for (Long ft : fillList) {
//                    if (ft - previousTime <= beforeRange && nextTime - ft <= afterRange) {//线性插值
//                        double x = previousValue + (nextValue - previousValue) * (ft - previousTime) / (nextTime - previousTime);
//                        collector.putDouble(ft, x);
//                    } else {
//                        collector.putDouble(ft, Double.NaN);
//                    }
//                }
//                fillList.clear();
//                collector.putDouble(t, v);
//            }
//        }
//        for (Long ft : fillList) {//最后的NaN，没有后续的非NaN点，无法填充
//            collector.putDouble(ft, Double.NaN);
//        }
//    }
//
//    private void fillFloat(RowIterator iterator, PointCollector collector) throws IOException {
//        ArrayList<Long> fillList = new ArrayList<>();
//        float previousValue = Float.NaN, nextValue = Float.NaN;
//        long previousTime = -1, nextTime = -1;
//        while (iterator.hasNextRow()) {
//            Row row = iterator.next();
//            long t = row.getTime();
//            float v = row.getFloat(0);
//            if (Float.isNaN(v)) {
//                fillList.add(t);
//            } else {
//                previousTime = nextTime;
//                previousValue = nextValue;
//                nextTime = t;
//                nextValue = v;
//                for (Long ft : fillList) {
//                    if (ft - previousTime <= beforeRange && nextTime - ft <= afterRange) {//线性插值
//                        float x = previousValue + (nextValue - previousValue) * (ft - previousTime) / (nextTime - previousTime);
//                        collector.putFloat(ft, x);
//                    } else {
//                        collector.putFloat(ft, Float.NaN);
//                    }
//                }
//                fillList.clear();
//                collector.putFloat(t, v);
//            }
//        }
//        for (Long ft : fillList) {//最后的NaN，没有后续的非NaN点，无法填充
//            collector.putFloat(ft, Float.NaN);
//        }
//    }
}
