/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.CircularQueue;
import cn.edu.thu.iotdb.quality.DoubleCircularQueue;
import cn.edu.thu.iotdb.quality.LongCircularQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

/**
 * 用于时间序列重采样（上采样、下采样）的类
 *
 * @author Wang Haoyu
 */
public class Resampler {

    private final LongArrayList timeWindow = new LongArrayList();//存放当前窗口内的数据点的时间戳
    private final DoubleArrayList valueWindow = new DoubleArrayList();//存放当前窗口内的数据点的值
    private final LongCircularQueue waitList = new LongCircularQueue();//存放待插值的时间戳
    private final CircularQueue<SourceDataPoint> source = new CircularQueue<>();//存放用于插值的源数据点
    private final LongCircularQueue timeBuffer = new LongCircularQueue();//缓存输出数据点的时间戳
    private final DoubleCircularQueue valueBuffer = new DoubleCircularQueue();//缓存输出数据点的值
    private final long newPeriod;//重采样的周期
    private final String aggregator;//用于聚合的算法名称
    private final String interpolator;//用于插值的算法名称
    private long currentTime;//当前窗口的起始时间，窗口左闭右开
    private boolean first = true;//是否是第一次处理waitList

    public Resampler(long newPeriod, String aggregator, String interpolator) {
        this.newPeriod = newPeriod;
        this.aggregator = aggregator;
        this.interpolator = interpolator;
        this.currentTime = -1;
    }

    /**
     * 加入新的数据点
     *
     * @param time 时间戳
     * @param value 值
     */
    public void insert(long time, double value) {
        if (Double.isNaN(value)) {//跳过值为NAN的数据
            return;
        }
        if (currentTime < 0) {//初始化窗口
            currentTime = time;
        }
        while (time >= currentTime + newPeriod) {
            downSample();//窗口已满，对其进行清理
            upSample();
            currentTime += newPeriod;
        }
        //向窗口填充新的数据
        timeWindow.add(time);
        valueWindow.add(value);
    }

    /**
     * 从文件中批量加入数据点
     *
     * @param filename 文件名
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public void insert(String filename) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(new File(filename));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sc.useDelimiter("\\s*(,|\\r|\\n)\\s*");//设置分隔符，以逗号或回车分隔，前后可以有若干个空白符
        sc.nextLine();
        while (sc.hasNext()) {
            insert(format.parse(sc.next()).getTime(), sc.nextDouble());
        }
    }

    /**
     * 强制对所有缓存数据进行处理
     */
    public void flush() {
        if (currentTime > 0) {//处理输入0个数据点的情况
            downSample();
            upSample();
            //输出source中最后一个元素
            int n = source.getSize();
            if (n > 0) {
                SourceDataPoint p = source.get(n - 1);
                if (p.out) {
                    timeBuffer.push(p.time);
                    valueBuffer.push(p.value);
                }
            }
        }
    }

    /**
     * 上采样，利用插值算法处理值为空的点，并将处理完成的点输出
     */
    private void upSample() {
        if (source.getSize() > 2 || (first && source.getSize() == 2)) {//常态
            first = false;
            if (source.getSize() > 2) {
                source.pop();
            }
            //将source的第0个数据点输出 
            SourceDataPoint p = source.get(0);
            if (p.out) {
                timeBuffer.push(p.time);
                valueBuffer.push(p.value);
            }
            //对waitList中的点进行内插
            while (!waitList.isEmpty()) {
                long t = waitList.getHead();
                if (source.get(1).time < t) {//内插法需要的后面的数据点不足，留待之后处理
                    break;
                }
                timeBuffer.push(t);
                valueBuffer.push(interpolate(t));//使用内插法进行填补
                waitList.pop();
            }
        }
    }

    /**
     * 下采样，将窗口内的数据点进行聚合，并为上采样做准备
     */
    private void downSample() {
        if (timeWindow.size() >= 2) {
            //聚合，将结果和时间戳加入source
            double result = aggregate();
            source.push(new SourceDataPoint(currentTime, result, true));
            timeWindow.clear();
            valueWindow.clear();
        } else if (timeWindow.size() == 1) {
            if (timeWindow.get(0) == currentTime) {
                //如果窗口内唯一点正好位于窗口起始时刻，则该点需要被输出
                source.push(new SourceDataPoint(timeWindow.get(0), valueWindow.get(0), true));
            } else {
                source.push(new SourceDataPoint(timeWindow.get(0), valueWindow.get(0), false));
                waitList.push(currentTime);
            }
            timeWindow.clear();
            valueWindow.clear();
        } else {
            waitList.push(currentTime);
        }
    }

    /**
     * 聚合。根据聚合算法，将窗口内的数据点聚合为一个值
     *
     * @return 聚合结果
     */
    private double aggregate() {
        double ret = Double.NaN;
        switch (aggregator) {
            case "min":
                ret = valueWindow.min();
                break;
            case "max":
                ret = valueWindow.max();
                break;
            case "mean":
                ret = valueWindow.average();
                break;
            case "median":
                ret = valueWindow.median();
                break;
            case "first":
                ret = valueWindow.get(0);
                break;
            case "last":
                ret = valueWindow.get(valueWindow.size() - 1);
                break;
            default:
                throw new IllegalArgumentException("Error: Illegal Aggregation Algorithm.");
        }
        return ret;
    }

    /**
     * 插值。根据插值算法，计算给定时间戳对应的值
     *
     * @param t 时间戳
     * @return 对应的值
     */
    private double interpolate(long t) {
        double ret = Double.NaN;
        switch (interpolator) {
            case "nan":
                ret = Double.NaN;
                break;
            case "ffill":
                ret = source.get(0).value;
                break;
            case "bfill":
                ret = source.get(1).value;
                break;
            case "linear":
                ret = source.get(0).value * (source.get(1).time - t) + source.get(1).value * (t - source.get(0).time);
                ret = ret / (source.get(1).time - source.get(0).time);
                break;
            default:
                throw new IllegalArgumentException("Error: Illegal Interpolation Algorithm.");
        }
        return ret;
    }

    /**
     * 输出缓冲中是否存在下一个数据点
     *
     * @return 存在下一个数据点返回true，否则返回false
     */
    public boolean hasNext() {
        return !timeBuffer.isEmpty();
    }

    /**
     * 返回输出缓冲中当前数据点的时间戳
     *
     * @return 时间戳
     */
    public long getOutTime() {
        return timeBuffer.getHead();
    }

    /**
     * 返回输出缓冲中当前数据点的值
     *
     * @return 值
     */
    public double getOutValue() {
        return valueBuffer.getHead();
    }

    /**
     * 在输出缓冲中移动到下一个数据点
     */
    public void next() {
        timeBuffer.pop();
        valueBuffer.pop();
    }

    private class SourceDataPoint {

        long time;//时间戳
        double value;//值
        boolean out;//是否需要被输出

        public SourceDataPoint(long time, double value, boolean out) {
            this.time = time;
            this.value = value;
            this.out = out;
        }

    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Resampler resampler = new Resampler(60000, "mean", "linear");
        resampler.insert("resample.csv");
        resampler.flush();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (resampler.hasNext()) {
            System.out.print(format.format(new Date(resampler.getOutTime())));
            System.out.println(" , " + resampler.getOutValue());
            resampler.next();
        }
    }

}
