package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.access.RowIterator;

public class KNNFill extends ValueFill {
    public KNNFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
    }

    public KNNFill(String filename) throws Exception {
        super(filename);
    }

    @Override
    public void fill() {

    }


}
