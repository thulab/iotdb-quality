/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.dquality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

/**
 *
 * @author Wang Haoyu
 */
public class TimeSeriesQuality {

    public static final int WINDOWSIZE = 10;
    private int cnt = 0;//数据点总数
    private int missCnt = 0;//缺失点个数
    private int specialCnt = 0;//特殊值点个数
    private int lateCnt = 0;//延迟点个数
    private int redundancyCnt = 0;//过密点个数
    private int valueCnt = 0;//违背取值范围约束的数据点个数
    private int variationCnt = 0;//违背取值变化约束的数据点个数
    private int speedCnt = 0;//违背速度约束的数据点个数
    private int speedchangeCnt = 0;//违背速度变化约束的数据点个数
    private final double[] time, origin;

    public TimeSeriesQuality(RowIterator dataIterator) throws IOException, NoNumberException {
        ArrayList<Double> timeList = new ArrayList<>(), originList = new ArrayList<>();
        while (dataIterator.hasNextRow()) {
            Row row = dataIterator.next();
            cnt++;
            double v = Util.getValueAsDouble(row);
            if (Double.isFinite(v)) {
                double t = Long.valueOf(row.getTime()).doubleValue();
                timeList.add(t);
                originList.add(v);
            } else {//对特殊值的处理，包括NAN，INF等
                specialCnt++;
            }
        }
        time = Util.toArray(timeList);
        origin = Util.toArray(originList);
    }

    public TimeSeriesQuality(String filename) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(new File(filename));
        ArrayList<Double> timeList = new ArrayList<>(), originList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sc.useDelimiter("\\s*(,|\\r|\\n)\\s*");//设置分隔符，以逗号或回车分隔，前后可以有若干个空白符
        sc.nextLine();
        while (sc.hasNext()) {
            cnt++;
            double t = format.parse(sc.next()).getTime();
            double v = sc.nextDouble();
            if (Double.isFinite(v)) {
                timeList.add(t);
                originList.add(v);
            } else {//对特殊值的处理，包括NAN，INF等
                specialCnt++;
            }
        }
        time = Util.toArray(timeList);
        origin = Util.toArray(originList);
    }

    public void timeDetect() throws IOException, NoNumberException {
        //计算时间间隔特征
        double interval[] = Util.variation(time);
        Median median = new Median();
        double base = median.evaluate(interval);
        double gap = 0;//当前数据点与上一个点未被删除的数据点之间的时间间隔
        //寻找时间戳异常
        for (int i = 0; i < interval.length; i++) {
            double times = gap + interval[i] / base;
            if (times > 9) {//停机时段排除
                gap = 0;
                continue;
            }
            if (times <= 0.5) {//处理为过密点并删除
                gap += times;
                redundancyCnt++;
                continue;
            }
            if (times >= 2.0) {
                int temp = 0;//在后续窗口中找到的连续过密点个数
                gap = 0;
                for (int j = i + 1; j < Math.min(interval.length, i + WINDOWSIZE); j++) {
                    double times2 = gap + interval[j] / base;
                    if (times2 >= 2.0) {//发现另一个缺失点，停止搜索
                        break;
                    }
                    if (times2 <= 0.5) {//发现过密点，可能是延迟导致的
                        gap += times2;
                        temp++;
                        if (temp == (int) Math.round(times - 1)) {
                            break;//找到了足够数量的过密点
                        }
                    } else {
                        gap = 0;
                    }
                }
                lateCnt += temp;
                redundancyCnt -= temp;
                missCnt += (Math.round(times - 1) - temp);
            }
            gap = 0;
        }
    }

    public void valueDetect() throws IOException, NoNumberException {
        int k = 3;
        //原始数据异常检测
        valueCnt = findOutliers(origin, k);
        //取值变化异常检测
        double[] variation = Util.variation(origin);
        variationCnt = findOutliers(variation, k);
        //取值变化速度异常检测
        double[] speed = Util.speed(origin, time);
        speedCnt = findOutliers(speed, k);
        //取值变化加速度异常检测
        double[] speedchange = Util.variation(speed);
        speedchangeCnt = findOutliers(speedchange, k);
    }

    /**
     * 返回序列中偏离中位数超过k倍绝对中位差的点的个数
     *
     * @param value 序列
     * @param k
     * @return 偏离中位数超过k倍绝对中位差的点的个数
     */
    private int findOutliers(double[] value, double k) {
        Median median = new Median();
        double mid = median.evaluate(value);
        double sigma = Util.mad(value);
        int num = 0;
        for (int i = 0; i < value.length; i++) {
            if (Math.abs(value[i] - mid) > k * sigma) {
                num++;
            }
        }
        return num;
    }

    public double getCompleteness() {
        return 1 - (missCnt + specialCnt) * 1.0 / (cnt + missCnt);
    }

    public double getConsistency() {
        return 1 - redundancyCnt * 1.0 / cnt;
    }

    public double getTimeliness() {
        return 1 - lateCnt * 1.0 / cnt;
    }

    public double getValidity() {
        return 1 - (valueCnt + variationCnt + speedCnt + speedchangeCnt) * 0.25 / cnt;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, NoNumberException, ParseException {
        TimeSeriesQuality tsq = new TimeSeriesQuality("temp.csv");
        tsq.timeDetect();
        tsq.valueDetect();
        System.out.println(tsq.getConsistency());
    }

}
