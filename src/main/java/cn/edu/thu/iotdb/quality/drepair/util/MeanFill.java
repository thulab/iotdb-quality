package cn.edu.thu.iotdb.quality.drepair.util;

import org.apache.iotdb.db.query.udf.api.access.RowIterator;

public class MeanFill extends ValueFill {
    public MeanFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
        calMeanAndVar();
    }

    public MeanFill(String filename) throws Exception {
        super(filename);
        calMeanAndVar();
    }

    @Override
    public void fill() {
        for(int i = 0; i < original.length; i++){
            double yt = original[i];
            if(!Double.isNaN(yt)){
                repaired[i] = yt;
            }
            else{
                repaired[i] = mean;
            }
        }
    }
}
