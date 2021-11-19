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
package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.apache.commons.lang3.ArrayUtils;

import cn.edu.thu.iotdb.quality.util.LinearRegression;
import cn.edu.thu.iotdb.quality.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UDTFSegment implements UDTF {

  private double calculate_error(double[] y1, double[] y2) {
    double[] y = ArrayUtils.addAll(y1, y2);
    int l = y.length;
    double[] x = new double[l];
    for (int i = 0; i < l; i++) {
      x[i] = i;
    }
    LinearRegression linearFit = new LinearRegression(x, y);
    return linearFit.getMAbsE();
  }

  private ArrayList<double[]> bottom_up(double[] value, double max_error) {
    ArrayList<double[]> seg_ts = new ArrayList<>();
    if (value.length <= 3) {
      ArrayList<double[]> ret = new ArrayList<>();
      ret.add(value);
      return ret;
    }
    if (value.length % 2 == 0) {
      for (int i = 0; i < value.length; i += 2) {
        seg_ts.add(Arrays.copyOfRange(value, i, i + 2));
      }
    } else {
      for (int i = 0; i < value.length - 3; i += 2) {
        seg_ts.add(Arrays.copyOfRange(value, i, i + 2));
      }
      seg_ts.add(Arrays.copyOfRange(value, value.length - 3, value.length));
    }
    ArrayList<Double> merge_cost = new ArrayList<>();
    for (int i = 0; i < seg_ts.size() - 1; i++) {
      merge_cost.add(calculate_error(seg_ts.get(i), seg_ts.get(i + 1)));
    }
    while (Collections.min(merge_cost) < max_error) {
      int index = merge_cost.indexOf(Collections.min(merge_cost));
      seg_ts.set(index, ArrayUtils.addAll(seg_ts.get(index), seg_ts.get(index + 1)));
      seg_ts.remove(index + 1);
      merge_cost.remove(index);
      if (seg_ts.size() == 1) {
        break;
      }
      if (index + 1 < seg_ts.size()) {
        merge_cost.set(index, calculate_error(seg_ts.get(index), seg_ts.get(index + 1)));
      }
      if (index > 0) {
        merge_cost.set(index - 1, calculate_error(seg_ts.get(index - 1), seg_ts.get(index)));
      }
    }
    return seg_ts;
  }

  private double[] best_line(
      double max_error, double[] input_df, double[] w, int start_idx, double upper_bound) {
    double error = 0.0;
    int idx = start_idx + w.length;
    double[] S_prev = w.clone();
    if (idx >= input_df.length) {
      return S_prev;
    }
    while (error <= max_error) {
      double[] S = ArrayUtils.addAll(S_prev, input_df[idx]);
      idx++;
      double[] times = new double[S.length];
      for (int i = 0; i < times.length; i++) {
        times[i] = i;
      }
      error = new LinearRegression(times, S).getMAbsE();
      if (error <= max_error) {
        S_prev = S.clone();
      }
      if (S_prev.length > upper_bound || idx >= input_df.length) {
        break;
      }
    }
    return S_prev;
  }

  private double[] approximated_segment(double[] in_seq) {
    if (in_seq.length <= 2) {
      return in_seq;
    }
    double[] times = new double[in_seq.length];
    for (int i = 0; i < times.length; i++) {
      times[i] = i;
    }
    return new LinearRegression(times, in_seq).getYhead();
    /*
    double[] new_seg=new double[2];
    new_seg[0]=approximated_values[0];
    new_seg[1]=approximated_values[approximated_values.length-2];
    return  new_seg;
    */

  }

  private ArrayList<double[]> swab(
      double[] input_df, double max_error, int seg_num, int in_window_size) throws Exception {
    int cur_nr = 0;
    int w_nr = cur_nr + in_window_size;
    int tot_size = input_df.length;
    double[] w = Arrays.copyOfRange(input_df, cur_nr, w_nr);
    int lower_bound = w_nr / 2;
    int upper_bound = 2 * w_nr;
    ArrayList<double[]> seg_ts = new ArrayList<>();
    boolean last_run = false;
    while (true) {
      ArrayList<double[]> T = bottom_up(w, max_error);
      seg_ts.add(T.get(0));
      if (cur_nr >= tot_size || last_run) {
        if (T.size() > 1) {
          seg_ts.add(approximated_segment(T.get(1)));
        }
        break;
      }
      cur_nr += T.get(0).length - 1;
      w_nr = cur_nr + in_window_size;
      if (input_df.length <= w_nr) {
        w_nr = -1;
        last_run = true;
        w = Arrays.copyOfRange(input_df, cur_nr, input_df.length);
      } else {
        w = Arrays.copyOfRange(input_df, cur_nr, w_nr);
      }
      Arrays.sort(w);
      w = best_line(max_error, input_df, w, cur_nr, upper_bound);
      if (w.length > upper_bound) {
        w = Arrays.copyOfRange(w, 0, upper_bound);
      }
    }
    return seg_ts;
  }

  private ArrayList<double[]> swab_alg(
      double[] df, long[] timestamp, double max_error, int windowsize) throws Exception {
    // double
    // error_bound=(Arrays.stream(timestamp).max().getAsLong()-Arrays.stream(timestamp).min().getAsLong())*max_error;
    ArrayList<double[]> res_df1 = swab(df, max_error, 10, windowsize);
    return res_df1;
  }

  private int window_size;
  private double max_error;
  private String method;
  private String output;
  private static ArrayList<Long> timestamp = new ArrayList<>();
  private static ArrayList<Double> value = new ArrayList<>();

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator.validateInputSeriesDataType(
        0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
  }

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    timestamp.clear();
    value.clear();
    this.window_size = udfParameters.getIntOrDefault("window", 10);
    this.max_error = udfParameters.getDoubleOrDefault("error", 0.1);
    this.method = udfParameters.getStringOrDefault("method", "bottom-up");
    this.method = this.method.toLowerCase();
    this.output = udfParameters.getStringOrDefault("output", "first");
    this.output = this.output.toLowerCase();
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    timestamp.add(row.getTime());
    value.add(Util.getValueAsDouble(row));
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    long[] ts = timestamp.stream().mapToLong(Long::valueOf).toArray();
    double[] v = value.stream().mapToDouble(Double::valueOf).toArray();
    ArrayList<double[]> seg = new ArrayList<>();
    if (method.equals("bottom-up")) {
      ArrayList<double[]> temp = bottom_up(v, max_error);
      seg.addAll(temp);
    } else if (method.equals("swab")) { // 未经测试
      seg = swab_alg(v, ts, max_error, window_size);
    }
    ArrayList<double[]> res = new ArrayList<>();
    for (double[] doubles : seg) {
      res.add(approximated_segment(doubles));
    }
    int index = 0;
    if (output.equals("first")) {
      for (double[] doubles : res) {
        collector.putDouble(ts[index], doubles[0]);
        index += doubles.length;
      }
    } else if (output.equals("all")) {
      for (double[] doubles : res) {
        for (double aDouble : doubles) {
          collector.putDouble(ts[index], aDouble);
          index++;
        }
      }
    }
  }
}
