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

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import cn.edu.thu.iotdb.quality.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/** @author Wang Haoyu */
// This function output inverse FFT for an input series.
public class UDTFIFFT implements UDTF {

  private final ArrayList<Double> real = new ArrayList<>();
  private final ArrayList<Double> imag = new ArrayList<>();
  private final ArrayList<Integer> time = new ArrayList<>();
  private long start;
  private long interval;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(2)
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validate(
            x -> (long) x > 0,
            "interval should be a time period whose unit is ms, s, m, h, d.",
            Util.parseTime(validator.getParameters().getStringOrDefault("interval", "1s")));
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (validator.getParameters().hasAttribute("start")) {
      validator.validate(
          x -> (long) x > 0,
          "start should conform to the format yyyy-MM-dd HH:mm:ss.",
          format.parse(validator.getParameters().getString("start")).getTime());
    }
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    configurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    this.interval = Util.parseTime(parameters.getStringOrDefault("interval", "1s"));
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.start = 0;
    if (parameters.hasAttribute("start")) {
      this.start = format.parse(parameters.getString("start")).getTime();
    }
    real.clear();
    imag.clear();
    time.clear();
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (!row.isNull(0)
        && !row.isNull(1)
        && Double.isFinite(Util.getValueAsDouble(row, 0))
        && Double.isFinite(Util.getValueAsDouble(row, 1))) {
      time.add((int) row.getTime());
      real.add(Util.getValueAsDouble(row, 0));
      imag.add(Util.getValueAsDouble(row, 1));
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    int length = real.size();
    Complex[] input = new Complex[length];
    for (int i = 0; i < length; i++) {
      input[i] = new Complex(real.get(i), imag.get(i));
    }
    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
    Complex[] result = fft.transform(input, TransformType.INVERSE);
    int n = time.get(time.size() - 1) + 1;
    for (int i = 0; i < n; i++) {
      collector.putDouble(start + i * interval, result[i].getReal());
    }
  }
}
