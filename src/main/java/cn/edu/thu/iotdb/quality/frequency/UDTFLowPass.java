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

import java.util.ArrayList;

/** @author Wang Haoyu */
// This function offers a low-pass filter.
public class UDTFLowPass implements UDTF {

  private double wpass;
  private final ArrayList<Double> valueList = new ArrayList<>();
  private final ArrayList<Long> timeList = new ArrayList<>();

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validateRequiredAttribute("wpass")
        .validate(
            x -> (double) x > 0 && (double) x < 1,
            "Wpass should be within (0,1).",
            validator.getParameters().getDouble("wpass"));
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    configurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    this.wpass = parameters.getDouble("wpass");
    valueList.clear();
    timeList.clear();
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    double v = Util.getValueAsDouble(row);
    if (Double.isFinite(v)) {
      valueList.add(v);
      timeList.add(row.getTime());
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    int n = valueList.size();
    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
    Complex[] trans =
        fft.transform(
            valueList.stream().mapToDouble(Double::valueOf).toArray(), TransformType.FORWARD);
    // discard high-frequency component
    int m = (int) Math.ceil(wpass * n / 2);
    for (int i = m; i <= n - m; i++) {
      trans[i] = new Complex(0, 0);
    }
    Complex[] result = fft.transform(trans, TransformType.INVERSE);
    // output
    for (int i = 0; i < n; i++) {
      collector.putDouble(timeList.get(i), result[i].getReal());
    }
  }
}
