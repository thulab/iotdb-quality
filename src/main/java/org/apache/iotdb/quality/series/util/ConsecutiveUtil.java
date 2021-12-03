package org.apache.iotdb.quality.series.util;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.quality.util.Util;

import java.io.IOException;
import java.util.ArrayList;

/** Util for ConsecutiveSequences and ConsecutiveWindows */
public class ConsecutiveUtil {
    private static final int maxLen = 128;
    private long first;
    private long last;
    private long gap;
    private int count = 0;
    private final ArrayList<Pair<Long, Boolean>> window = new ArrayList<>(maxLen);
    public ConsecutiveUtil(long first, long last, long gap){
        this.first = first;
        this.last = last;
        this.gap = gap;
    }

    public ArrayList<Pair<Long, Boolean>> getWindow(){
        return window;
    }

    public long getGap(){return gap;}

    public void setGap(long gap){this.gap = gap;}

    public int getMaxLen(){return maxLen;}

    public int getCount(){return count;}

    public void setCount(int count){this.count = count;}

    public long getFirst(){return first;}

    public void setFirst(long first){this.first = first;}

    public long getLast(){return last;}

    /**
     * 检查给定的行中是否存在空值
     * check Null values
     *
     * @return if no null value, return true，else return false
     */
    public boolean check(Row row) {
        for (int i = 0; i < row.size(); i++) {
            if (row.isNull(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据窗口内的数据计算标准间隔
     * calculate standard timestamp gap in given window.
     *
     */
    public void calculateGap() {
        long[] time = new long[window.size() - 1];
        for (int i = 0; i < time.length; i++) {
            time[i] = window.get(i + 1).getLeft() - window.get(i).getLeft();
        }
        gap = Util.mode(time);
    }

    /**
     * 清空窗口内的数据，并处理其中的行
     * clear data points in the window
     */
    public void cleanWindow(PointCollector collector) throws IOException {
        if (window.isEmpty()) {
            return;
        }
        first = last = -gap;
        for (Pair<Long, Boolean> p : window) {
            process(p.getLeft(), p.getRight(), collector);
        }
    }

    /**
     * 对数据中的一行进行处理
     * process one row
     *
     * @param time timestamp
     * @param nullExist if there is null in input series（true stands for there is）
     * @throws IOException 异常
     */
    public void process(long time, boolean nullExist, PointCollector collector) throws IOException {
        if (nullExist) { // consecutive subsequence ends with null
            if (count > 1) {
                collector.putInt(first, count);
            }
            first = last = -gap;
            count = 0;
        } else {
            if (time == last + gap) { // correct gap and not null value, subsequence grows
                last = time;
                count++;
            } else { // incorrect gap and not null value, subsequence ends, and new subsequence starts
                if (count > 1) {
                    collector.putInt(first, count);
                }
                first = last = time;
                count = 1;
            }
        }
    }
}
