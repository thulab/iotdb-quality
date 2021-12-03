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
package cn.edu.thu.iotdb.quality.frequency;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import cn.edu.thu.iotdb.quality.util.Util;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/** @author Wang Haoyu */
public class UDTFFFT implements UDTF {

  private String result;
  private boolean compressed;
  private double compressRate;
  private final DoubleArrayList list = new DoubleArrayList();

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validate(
            x ->
                ((String) x).equalsIgnoreCase("uniform")
                    || ((String) x).equalsIgnoreCase("nonuniform"),
            "Type should be 'uniform' or 'nonuniform'.",
            validator.getParameters().getStringOrDefault("type", "uniform"))
        .validate(
            x ->
                "real".equalsIgnoreCase((String) x)
                    || "imag".equalsIgnoreCase((String) x)
                    || "abs".equalsIgnoreCase((String) x)
                    || "angle".equalsIgnoreCase((String) x),
            "Result should be 'real', 'imag', 'abs' or 'angle'.",
            validator.getParameters().getStringOrDefault("result", "abs"))
        .validate(
            x -> (double) x > 0 && (double) x <= 1,
            "Compress should be within (0,1].",
            validator.getParameters().getDoubleOrDefault("compress", 1));
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    configurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    this.result = parameters.getStringOrDefault("result", "abs");
    this.compressed = parameters.hasAttribute("compress");
    this.compressRate = parameters.getDoubleOrDefault("compress", 1);
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    double v = Util.getValueAsDouble(row);
    if (Double.isFinite(v)) {
      list.add(v);
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    int n = list.size();
    DoubleFFT_1D fft = new DoubleFFT_1D(n);
    // 准备数据，每个数占用两个double
    double[] a = new double[2 * n];
    for (int i = 0; i < n; i++) {
      a[2 * i] = list.get(i);
      a[2 * i + 1] = 0;
    }
    fft.complexForward(a);
    if (compressed) {
      outputCompressed(collector, a);
    } else {
      outputUncompressed(collector, a);
    }
  }

  private void outputCompressed(PointCollector collector, double[] a) throws Exception {
    int n = a.length / 2;
    // 计算总能量
    double sum = 0;
    for (int i = 0; i < n; i++) {
      sum += a[2 * i] * a[2 * i] + a[2 * i + 1] * a[2 * i + 1];
    }
    // 压缩
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
    // 尾部标记，表示长度
    add(collector, a, n - 1);
  }

  private void add(PointCollector collector, double[] a, int i) throws Exception {
    double ans;
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

  private void outputUncompressed(PointCollector collector, double[] a) throws Exception {
    int n = a.length / 2;
    for (int i = 0; i < n; i++) {
      add(collector, a, i);
    }
  }
}
