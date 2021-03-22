/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.drepair;

import cn.edu.thu.iotdb.quality.Util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

/**
 * 利用Screen方法进行数值修复的类
 *
 * @author Wang Haoyu
 */
public class Screen {

    private double smin, smax;
    private double w;
    private final int n;
    private final long time[];
    private final double original[];
    private final double repaired[];

    public Screen(RowIterator dataIterator) throws Exception {
        ArrayList<Long> timeList = new ArrayList<>();
        ArrayList<Double> originList = new ArrayList<>();
        while (dataIterator.hasNextRow()) {//读取数据
            Row row = dataIterator.next();
            Double v = Util.getValueAsDouble(row);
            timeList.add(row.getTime());
            if (v == null || !Double.isFinite(v)) {//对空值的处理和特殊值的处理
                originList.add(Double.NaN);
            } else {
                originList.add(v);
            }
        }
        //保存时间序列
        time = Util.toLongArray(timeList);
        original = Util.toDoubleArray(originList);
        n = time.length;
        repaired = new double[n];
        //NaN处理
        processNaN();
        //设置默认的速度阈值
        double[] speed = Util.speed(original, time);
        Median median = new Median();
        double mid = median.evaluate(speed);
        double sigma = Util.mad(speed);
        smax = mid + 3 * sigma;
        smin = mid - 3 * sigma;
        //设置默认的窗口大小
        double interval[] = Util.variation(time);
        w = 5 * median.evaluate(interval);
    }

    public Screen(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sc.useDelimiter("\\s*(,|\\r|\\n)\\s*");//设置分隔符，以逗号或回车分隔，前后可以有若干个空白符
        sc.nextLine();
        ArrayList<Long> timeList = new ArrayList<>();
        ArrayList<Double> originList = new ArrayList<>();
        while (sc.hasNext()) {//读取数据
            timeList.add(format.parse(sc.next()).getTime());
            Double v = sc.nextDouble();
            if (!Double.isFinite(v)) {//对空值的处理和特殊值的处理
                originList.add(Double.NaN);
            } else {
                originList.add(v);
            }
        }
        //保存时间序列
        time = Util.toLongArray(timeList);
        original = Util.toDoubleArray(originList);
        n = time.length;
        repaired = new double[n];
        //NaN处理
        processNaN();
        //设置默认的速度阈值
        double[] speed = Util.speed(original, time);
        Median median = new Median();
        double mid = median.evaluate(speed);
        double sigma = Util.mad(speed);
        smax = mid + 3 * sigma;
        smin = mid - 3 * sigma;
        //设置默认的窗口大小
        double interval[] = Util.variation(time);
        w = 5 * median.evaluate(interval);
    }

    /**
     * 对数据序列中的NaN进行处理，采用线性插值方法
     */
    private void processNaN() throws Exception {
        int index1 = 0, index2;//线性插值的两个基准
        //找到两个非NaN的基准
        while (index1 < n && Double.isNaN(original[index1])) {
            index1++;
        }
        index2 = index1 + 1;
        while (index2 < n && Double.isNaN(original[index2])) {
            index2++;
        }
        if (index2 >= n) {
            throw new Exception("At least two non-NaN values are needed");
        }
        //对序列开头的NaN进行插值
        for (int i = 0; i < index2; i++) {
            original[i] = original[index1] + (original[index2] - original[index1]) * (time[i] - time[index1]) / (time[index2] - time[index1]);
        }
        //对序列中间的NaN进行插值
        for (int i = index2 + 1; i < n; i++) {
            if (!Double.isNaN(original[i])) {
                index1 = index2;
                index2 = i;
                for (int j = index1 + 1; j < index2; j++) {
                    original[j] = original[index1] + (original[index2] - original[index1]) * (time[j] - time[index1]) / (time[index2] - time[index1]);
                }
            }
        }
        //对序列末尾的NaN进行插值
        for (int i = index2 + 1; i < n; i++) {
            original[i] = original[index1] + (original[index2] - original[index1]) * (time[i] - time[index1]) / (time[index2] - time[index1]);
        }
    }

    /**
     * 开始修复
     */
    public void repair() {
        //准备使用固定的window
        ArrayList<Pair<Long, Double>> ans = new ArrayList<>();
        ans.add(Pair.of(time[0], original[0]));
        int startIndex = 0;
        for (int i = 1; i < n; i++) {
            ans.add(Pair.of(time[i], original[i]));
            while (ans.get(startIndex).getLeft() + w < ans.get(i).getLeft()) {
                //对窗口进行滑动，修复前面的若干个已经移出窗口的数据点
                local(ans, startIndex);
                startIndex++;
            }
        }
        int k = 0;
        for (Pair<Long, Double> p : ans) {
            this.repaired[k] = p.getRight();
            k++;
        }
    }

    /**
     * 对给定的数据点，返回所有候选修复的中位数
     *
     * @param list 数据点序列
     * @param index 待修复的数据点索引
     * @return 所有候选修复的中位数
     */
    private double getMedian(ArrayList<Pair<Long, Double>> list, int index) {
        int m = 0;
        while (list.get(index + m + 1).getLeft() <= list.get(index).getLeft() + w) {
            m++;
        }
        double x[] = new double[2 * m + 1];
        x[0] = list.get(index).getRight();
        for (int i = 1; i <= m; i++) {
            x[i] = list.get(index + i).getRight() + smin * (list.get(index).getLeft() - list.get(index + i).getLeft());
            x[i + m] = list.get(index + i).getRight() + smax * (list.get(index).getLeft() - list.get(index + i).getLeft());
        }
        Arrays.sort(x);
        return x[m];
    }

    /**
     * 对给定的数据点，返回实际修复后的值
     *
     * @param list 数据点序列
     * @param index 待修复的数据点索引
     * @param mid 所有候选修复的中位数
     * @return 实际修复后的值
     */
    private double getRepairedValue(ArrayList<Pair<Long, Double>> list, int index, double mid) {
        double xmin = list.get(index - 1).getRight() + smin * (list.get(index).getLeft() - list.get(index - 1).getLeft());
        double xmax = list.get(index - 1).getRight() + smax * (list.get(index).getLeft() - list.get(index - 1).getLeft());
        double temp = mid;
        temp = xmax < temp ? xmax : temp;
        temp = xmin > temp ? xmin : temp;
        return temp;
    }

    /**
     * 使用论文中的local算法修复一个数据点
     *
     * @param list 数据点序列
     * @param index 待修复的数据点的索引
     */
    private void local(ArrayList<Pair<Long, Double>> list, int index) {
        double mid = getMedian(list, index);
        //计算x_k'
        if (index == 0) {
            list.set(index, Pair.of(list.get(index).getLeft(), mid));
        } else {
            double temp = getRepairedValue(list, index, mid);
            list.set(index, Pair.of(list.get(index).getLeft(), temp));
        }
    }

    /**
     * @param smin the smin to set
     */
    public void setSmin(double smin) {
        this.smin = smin;
    }

    /**
     * @param smax the smax to set
     */
    public void setSmax(double smax) {
        this.smax = smax;
    }

    /**
     * @param w the w to set
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * @return the time
     */
    public long[] getTime() {
        return time;
    }

    /**
     * @return the repaired
     */
    public double[] getRepaired() {
        return repaired;
    }

    public static void main(String[] args) throws Exception {
        Screen screen = new Screen("temp.csv");
        screen.repair();
        for (int i = 0; i < screen.n; i++) {
            System.out.println(screen.time[i] + " " + screen.repaired[i]);
        }
    }
}
