package org.apache.iotdb.quality.frequency.util;

import org.apache.iotdb.db.query.udf.api.collector.PointCollector;

/** Util for UDFFFT */
public class FFTUtil {
  private final String result;
  private final double compressRate;

  public FFTUtil(String res, double cmprate) {
    this.result = res;
    this.compressRate = cmprate;
  }

  public void outputCompressed(PointCollector collector, double a[]) throws Exception {
    int n = a.length / 2;
    // calculate total energy
    double sum = 0;
    for (int i = 0; i < n; i++) {
      sum += a[2 * i] * a[2 * i] + a[2 * i + 1] * a[2 * i + 1];
    }
    // compress
    double temp = a[0] * a[0] + a[1] * a[1];
    add(collector, a, 0);
    for (int i = 1; i <= n / 2; i++) {
      add(collector, a, i);
      temp += (a[2 * i] * a[2 * i] + a[2 * i + 1] * a[2 * i + 1]) * 2;
      if (temp > compressRate * sum) {
        System.out.println(i);
        break;
      }
    }
    // sign at the tail for length
    add(collector, a, n - 1);
  }

  public void add(PointCollector collector, double a[], int i) throws Exception {
    double ans = 0;
    switch (result) {
      case "real":
        ans = a[i * 2];
        break;
      case "imag":
        ans = a[i * 2 + 1];
        break;
      case "abs":
        ans = Math.sqrt(a[i * 2] * a[i * 2] + a[2 * i + 1] * a[2 * i + 1]);
        break;
      case "angle":
        ans = Math.atan2(a[2 * i + 1], a[2 * i]);
        break;
      default:
        throw new Exception("It's impossible");
    }
    collector.putDouble(i, ans);
  }

  public void outputUncompressed(PointCollector collector, double a[]) throws Exception {
    int n = a.length / 2;
    for (int i = 0; i < n; i++) {
      add(collector, a, i);
    }
  }
}
