/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.dquality;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.iotdb.db.query.udf.api.access.Row;

/**
 *
 * @author Wang Haoyu
 */
public class Util {

    /**
     * 从Row中取出第一个值，并转化为Double类型
     *
     * @param row
     * @return Row中的第一个值
     * @throws NoNumberException Row的第一个值是非数值类型
     */
    public static Double getValueAsDouble(Row row) throws NoNumberException {
        double ans = 0;
        if (row.isNull(0)) {
            return null;
        }
        switch (row.getDataType(0)) {
            case INT32:
                ans = row.getInt(0);
                break;
            case INT64:
                ans = row.getLong(0);
                break;
            case FLOAT:
                ans = row.getFloat(0);
                break;
            case DOUBLE:
                ans = row.getDouble(0);
                break;
            default:
                throw new NoNumberException();
        }
        return ans;
    }

    /**
     * 将{@code ArrayList<Double>}转化为长度相同的{@code double[]}。
     * <p>
     * 用户需要保证{@code ArrayList<Double>}中没有空值{@code  null}
     *
     * @param list 待转化的{@code ArrayList<Double>}
     * @return 转化后的{@code double[]}
     */
    public static double[] toDoubleArray(ArrayList<Double> list) {
        int len = list.size();
        double ans[] = new double[len];
        for (int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 将{@code ArrayList<Long>}转化为长度相同的{@code long[]}。
     * <p>
     * 用户需要保证{@code ArrayList<Long>}中没有空值{@code  null}
     *
     * @param list 待转化的{@code ArrayList<Long>}
     * @return 转化后的{@code long[]}
     */
    public static long[] toLongArray(ArrayList<Long> list) {
        int len = list.size();
        long ans[] = new long[len];
        for (int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 计算序列的绝对中位差MAD。为了达到渐进正态性，乘上比例因子1.4826。
     * <br>
     * 备注: 1.4826 = 1/qnorm(3/4)
     *
     * @param value 序列
     * @return 绝对中位差MAD
     */
    public static double mad(double[] value) {
        Median median = new Median();
        double mid = median.evaluate(value);
        double d[] = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            d[i] = Math.abs(value[i] - mid);
        }
        return 1.4826 * median.evaluate(d);
    }

    /**
     * 计算序列的取值变化
     *
     * @param origin 原始序列
     * @return 取值变化序列
     */
    public static double[] variation(double origin[]) {
        int n = origin.length;
        double var[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            var[i] = origin[i + 1] - origin[i];
        }
        return var;
    }

    /**
     * 计算序列的取值变化
     *
     * @param origin 原始序列
     * @return 取值变化序列
     */
    public static double[] variation(long origin[]) {
        int n = origin.length;
        double var[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            var[i] = origin[i + 1] - origin[i];
        }
        return var;
    }

    /**
     * 计算时间序列的速度
     *
     * @param origin 值序列
     * @param time 时间戳序列
     * @return 速度序列
     */
    public static double[] speed(double origin[], double time[]) {
        int n = origin.length;
        double speed[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            speed[i] = (origin[i + 1] - origin[i]) / (time[i + 1] - time[i]);
        }
        return speed;
    }

    /**
     * 计算时间序列的速度
     *
     * @param origin 值序列
     * @param time 时间戳序列
     * @return 速度序列
     */
    public static double[] speed(double origin[], long time[]) {
        int n = origin.length;
        double speed[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            speed[i] = (origin[i + 1] - origin[i]) / (time[i + 1] - time[i]);
        }
        return speed;
    }
}