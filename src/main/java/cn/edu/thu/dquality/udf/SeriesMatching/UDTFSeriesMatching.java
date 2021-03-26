package cn.edu.thu.dquality.udf.SeriesMatching;


import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用于计算序列匹配的UDF
 *
 * @author Gong YiKun
 */
public class UDTFSeriesMatching implements UDTF {

    public int colSize;

    @Override
    public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
        udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(udfp.getIntOrDefault("window", Integer.MAX_VALUE)))
                .setOutputDataType(TSDataType.TEXT);
        this.colSize = udfp.getIntOrDefault("colSize", Integer.MAX_VALUE);
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        try {
            if (rowWindow.windowSize() > 1) {
                SeriesMatching seriesMatching = new SeriesMatching(rowWindow.getRowIterator(), this.colSize);
                for (int colIndex =0; colIndex< colSize ; colIndex++){
                    seriesMatching.initXAndY(colIndex);
//                    double minDex = seriesMatching.getMinDex();
//                    double minDtw = seriesMatching.getMinDtw();
//                    collector.putString(2*colIndex, Double.toString(minDex));
//                    collector.putString(2*colIndex+1, Double.toString(minDtw));
                    System.out.println(colIndex + " is prepared");
                }
                System.out.println("Start matching");
                System.out.println(seriesMatching.listMatching.size());
                int sumMatched = 0;
                for (int colIndex =0; colIndex< colSize ; colIndex++){
                    ArrayList<Integer> listMatching = seriesMatching.listMatching;
                    if (listMatching.get(listMatching.get(colIndex))==colIndex){
                        System.out.println(colIndex + "matched");
                        String output = colIndex+"->match->"+listMatching.get(colIndex);
                        System.out.println(output);
                        System.out.println(sumMatched);
                        collector.putString(sumMatched, output);
                        System.out.println("putString");
                        listMatching.set(colIndex, -1);
                        sumMatched += 1;
                    }
                    else
                        System.out.println("not matched");
                }
                if (sumMatched == 0){
                    collector.putString(0, "not matched Column");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(cn.edu.thu.dquality.udf.SeriesMatching.UDTFSeriesMatching.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
