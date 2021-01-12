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

    public static double getValueAsDouble(Row row) throws NoNumberException {
        double ans = 0;
        switch (row.getDataType(0)) {
            case BOOLEAN:
            case TEXT:
                throw new NoNumberException();
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
        }
        return ans;
    }

    public static double[] toArray(ArrayList<Double> list) {
        int len = list.size();
        double ans[] = new double[len];
        for (int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 计算序列的绝对中位差MAD
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
        return median.evaluate(d);
    }

    public static double[] variation(double origin[]) {
        int n = origin.length;
        double var[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            var[i] = origin[i + 1] - origin[i];
        }
        return var;
    }

    public static double[] speed(double origin[], double time[]) {
        int n = origin.length;
        double speed[] = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            speed[i] = (origin[i + 1] - origin[i]) / (time[i + 1] - time[i]);
        }
        return speed;
    }
}
