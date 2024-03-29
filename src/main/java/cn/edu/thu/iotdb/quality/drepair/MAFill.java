package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.access.RowIterator;

public class MAFill extends ValueFill {
//    protected int n;
//    protected long time[];
//    protected double original[];
//    protected double repaired[];
    int window_size = 5;
    double window_sum = 0;
    int window_cnt = 0;
    int l = 0;
    int r = window_size - 1;
    public MAFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
    }

    public MAFill(String filename) throws Exception {
        super(filename);
    }

    @Override
    public void fill() {
        // 计算局部均值，进行填补
        // 找到当前缺失值窗口内的所有值进行平均 , 使用双指针
        for(int i = l; i < r && i < original.length; i++){
            if(!Double.isNaN(original[i])) {
                window_sum += original[i];
                window_cnt += 1;
            }
        }
        for(int i = 0; i < original.length; i++){
            if(!Double.isNaN(original[i])){
                repaired[i] = original[i];
            }
            else{
                repaired[i] = window_sum / window_cnt;
            }
            if(i <= (window_size - 1) / 2 || i >= original.length - (window_size - 1) / 2 - 1)
                continue;
            if(!Double.isNaN(original[r])){
                window_sum += original[r];
                window_cnt += 1;
            }
            l += 1;
            r += 1;
        }
    }
}
