/*
 * Copyright © 2021 iotdb-quality developer group (iotdb-quality@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dquality;

import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import cn.edu.thu.iotdb.quality.util.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class calculates qualities of time series.
 *    Completeness,
 *    Consistency,
 *    Timeliness,
 *    Validity.
 *    **/
public class TimeSeriesQuality {

  public static final int WINDOWSIZE = 10;
  private boolean downtime = true;
  private int cnt = 0;
  private int missCnt = 0;
  private int specialCnt = 0;
  private int lateCnt = 0;
  private int redundancyCnt = 0;
  private int valueCnt = 0;
  private int variationCnt = 0;
  private int speedCnt = 0;
  private int speedchangeCnt = 0;
  private final double[] time, origin;

  public TimeSeriesQuality(RowIterator dataIterator) throws Exception {
    ArrayList<Double> timeList = new ArrayList<>(), originList = new ArrayList<>();
    while (dataIterator.hasNextRow()) {
      Row row = dataIterator.next();
      cnt++;
      double v = Util.getValueAsDouble(row);
      double t = Long.valueOf(row.getTime()).doubleValue();
      if (Double.isFinite(v)) {
        timeList.add(t);
        originList.add(v);
      } else {
        specialCnt++;
        timeList.add(t);
        originList.add(Double.NaN);
      }
    }
    time = Util.toDoubleArray(timeList);
    origin = Util.toDoubleArray(originList);
    processNaN();
  }

  public TimeSeriesQuality(String filename) throws Exception {
    Scanner sc = new Scanner(new File(filename));
    ArrayList<Double> timeList = new ArrayList<>(), originList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sc.useDelimiter("\\s*([,\\r\\n])\\s*");
    sc.nextLine();
    while (sc.hasNext()) {
      cnt++;
      double t = format.parse(sc.next()).getTime();
      double v = sc.nextDouble();
      if (Double.isFinite(v)) {
        timeList.add(t);
        originList.add(v);
      } else {
        specialCnt++;
        timeList.add(t);
        originList.add(Double.NaN);
      }
    }
    time = Util.toDoubleArray(timeList);
    origin = Util.toDoubleArray(originList);
    processNaN();
  }

  /** process NaN in data sequence by linear interpolation method */
  private void processNaN() throws Exception {
    int n = origin.length;
    int index1 = 0, index2;
    while (index1 < n && Double.isNaN(origin[index1])) {
      index1++;
    }
    index2 = index1 + 1;
    while (index2 < n && Double.isNaN(origin[index2])) {
      index2++;
    }
    if (index2 >= n) {
      throw new Exception("At least two non-NaN values are needed");
    }
    for (int i = 0; i < index2; i++) {
      origin[i] =
          origin[index1]
              + (origin[index2] - origin[index1])
                  * (time[i] - time[index1])
                  / (time[index2] - time[index1]);
    }
    for (int i = index2 + 1; i < n; i++) {
      if (!Double.isNaN(origin[i])) {
        index1 = index2;
        index2 = i;
        for (int j = index1 + 1; j < index2; j++) {
          origin[j] =
              origin[index1]
                  + (origin[index2] - origin[index1])
                      * (time[j] - time[index1])
                      / (time[index2] - time[index1]);
        }
      }
    }
    for (int i = index2 + 1; i < n; i++) {
      origin[i] =
          origin[index1]
              + (origin[index2] - origin[index1])
                  * (time[i] - time[index1])
                  / (time[index2] - time[index1]);
    }
  }

  /** Anomaly detection is performed on the timestamp of time series to prepare for computational integrity, consistency and timeliness */
  public void timeDetect() {
    double[] interval = Util.variation(time);
    Median median = new Median();
    double base = median.evaluate(interval);
    ArrayList<Double> window = new ArrayList<>();
    int i;
    for (i = 0; i < Math.min(time.length, WINDOWSIZE); i++) {
      window.add(time[i]);
    }
    while (window.size() > 1) {
      double times = (window.get(1) - window.get(0)) / base;
      if (times <= 0.5) {
        window.remove(1);
        redundancyCnt++;
      } else if (times >= 2.0 && (!downtime || times <= 9.0)) {
        int temp = 0;
        for (int j = 2; j < window.size(); j++) {
          double times2 = (window.get(j) - window.get(j - 1)) / base;
          if (times2 >= 2.0) {
            break;
          }
          if (times2 <= 0.5) {
            temp++;
            window.remove(j);
            j--;
            if (temp == (int) Math.round(times - 1)) {
              break;
            }
          }
        }
        lateCnt += temp;
        missCnt += (Math.round(times - 1) - temp);
      }
      window.remove(0);
      while (window.size() < WINDOWSIZE && i < time.length) {
        window.add(time[i]);
        i++;
      }
    }
  }

  /** Anomaly detection is performed on the values of time series to prepare for the validity of calculation. */
  public void valueDetect() {
    int k = 3;
    valueCnt = findOutliers(origin, k);
    double[] variation = Util.variation(origin);
    variationCnt = findOutliers(variation, k);
    double[] speed = Util.speed(origin, time);
    speedCnt = findOutliers(speed, k);
    double[] speedchange = Util.variation(speed);
    speedchangeCnt = findOutliers(speedchange, k);
  }

  /** Returns the number of points in the sequence that deviate from the median by more than k times the absolute median. **/
  private int findOutliers(double[] value, double k) {
    Median median = new Median();
    double mid = median.evaluate(value);
    double sigma = Util.mad(value);
    int num = 0;
    for (double v : value) {
      if (Math.abs(v - mid) > k * sigma) {
        num++;
      }
    }
    return num;
  }

  /** Returns the integrity of the time series. */
  public double getCompleteness() {
    return 1 - (missCnt + specialCnt) * 1.0 / (cnt + missCnt);
  }

  /** Returns consistency of time series. */
  public double getConsistency() {
    return 1 - redundancyCnt * 1.0 / cnt;
  }

  /** Returns the timeliness of the time series. */
  public double getTimeliness() {
    return 1 - lateCnt * 1.0 / cnt;
  }

  /** Returns the validity of the time series. */
  public double getValidity() {
    return 1 - (valueCnt + variationCnt + speedCnt + speedchangeCnt) * 0.25 / cnt;
  }

  public static void main(String[] args) throws Exception {
    TimeSeriesQuality tsq = new TimeSeriesQuality("temp.csv");
    tsq.timeDetect();
    tsq.valueDetect();
    System.out.println(tsq.getCompleteness());
    System.out.println(tsq.getConsistency());
    System.out.println(tsq.getTimeliness());
    System.out.println(tsq.getValidity());
  }

  /** return the downtime */
  public boolean isDowntime() {
    return downtime;
  }

  /** set downtime */
  public void setDowntime(boolean downtime) {
    this.downtime = downtime;
  }
}
