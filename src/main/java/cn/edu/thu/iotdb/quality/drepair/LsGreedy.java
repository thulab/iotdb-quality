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
import java.util.PriorityQueue;
import java.util.Scanner;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

/**
 * 利用LsGreedy方法进行数值修复的类
 *
 * @author Wang Haoyu
 */
public class LsGreedy {

    private final int n;
    private final long time[];
    private final double original[];
    private double repaired[];
    private double center = 0, sigma;

    public LsGreedy(RowIterator dataIterator) throws Exception {
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
        //NaN处理
        processNaN();
        //设置默认参数
        double[] speed = Util.speed(original, time);
        double[] speedchange = Util.variation(speed);
        sigma = Util.mad(speedchange);
    }

    public LsGreedy(String filename) throws Exception {
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
        //设置默认参数
        double[] speed = Util.speed(original, time);
        double[] speedchange = Util.variation(speed);
        sigma = Util.mad(speedchange);
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

    public void repair() {
        repaired = original.clone();
        RepairNode table[] = new RepairNode[n];
        PriorityQueue<RepairNode> heap = new PriorityQueue<>();
        for (int i = 1; i < n - 1; i++) {
            RepairNode node = new RepairNode(i);
            table[i] = node;
            if (Math.abs(node.getU() - center) > 3 * sigma) {
                heap.add(node);
            }
        }//初始化，填充堆
        while (true) {
            RepairNode top = heap.peek();
            if (top == null || Math.abs(top.getU() - center) < 3 * sigma) {
                break;
            }//堆是空的，或者所有的速度变化都在center±3sigma范围内，停止贪心
            top.modify();
            for (int i = Math.max(1, top.getIndex() - 1); i <= Math.min(n - 2, top.getIndex() + 1); i++) {
                heap.remove(table[i]);
                RepairNode temp = new RepairNode(i);
                table[i] = temp;
                if (Math.abs(temp.getU() - center) > 3 * sigma) {
                    heap.add(temp);
                }
            }
        }//贪婪地寻找u与center差距最大的点并进行修复
    }

    class RepairNode implements Comparable<RepairNode> {

        private final int index;//被修复的数据点的编号
        private final double u;//当前的速度变化

        public RepairNode(int index) {
            this.index = index;
            double v1 = repaired[index + 1] - repaired[index];
            v1 = v1 / (time[index + 1] - time[index]);
            double v2 = repaired[index] - repaired[index - 1];
            v2 = v2 / (time[index] - time[index - 1]);
            this.u = v1 - v2;
        }

        /**
         * 调整被修复的数据点的值，使得其速度变化与center的差距下降一个sigma
         */
        public void modify() {
            double temp;
            if (sigma < 1e-10) {
                temp = Math.abs(u - center);
            } else {
                temp = Math.max(sigma, Math.abs(u - center) / 3);
            }
            temp *= (time[index + 1] - time[index]) * (time[index] - time[index - 1]) / (time[index + 1] - time[index - 1]);
            if (this.u > center) {
                repaired[index] += temp;
            } else {
                repaired[index] -= temp;
            }
        }

        @Override
        public int compareTo(RepairNode o) {
            double u1 = Math.abs(this.u - center);
            double u2 = Math.abs(o.u - center);
            if (u1 > u2) {
                return -1;
            } else if (u1 == u2) {
                return 0;
            } else {
                return 1;
            }
        }

        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * @return the u
         */
        public double getU() {
            return u;
        }

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

    /**
     * @param center the center to set
     */
    public void setCenter(double center) {
        this.center = center;
    }

    /**
     * @param sigma the sigma to set
     */
    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public static void main(String[] args) throws Exception {
        LsGreedy lsGreedy = new LsGreedy("temp.csv");
        lsGreedy.repair();
        for (int i = 0; i < lsGreedy.n; i++) {
            System.out.println(lsGreedy.time[i] + " " + lsGreedy.repaired[i]);
        }
    }

}
