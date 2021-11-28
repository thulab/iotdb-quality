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
// This function output FFT for an input series.
public class UDTFFFT implements UDTF {

  private String result;
  private boolean compressed;
  private double compressRate;
  private final ArrayList<Double> list = new ArrayList<>();

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
            validator.getParameters().getStringOrDefault("method", "uniform"))
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
    list.clear();
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
    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
    Complex[] result =
        fft.transform(list.stream().mapToDouble(Double::valueOf).toArray(), TransformType.FORWARD);
    if (compressed) { // compress the result
      double sum = 0;
      for (Complex complex : result) {
        sum +=
            complex.getReal() * complex.getReal() + complex.getImaginary() * complex.getImaginary();
      }
      double temp =
          result[0].getReal() * result[0].getReal()
              + result[0].getImaginary() * result[0].getImaginary();
      collector.putDouble(0, transformToOutput(collector, result[0]));
      for (int i = 1; i < result.length; i++) {
        collector.putDouble(i, transformToOutput(collector, result[i]));
        temp +=
            (result[i].getReal() * result[i].getReal()
                    + result[i].getImaginary() * result[i].getImaginary())
                * 2;
        if (temp > compressRate * sum) {
          System.out.println(i);
          if (i < result.length - 1) {
            // put the last number to show series length
            collector.putDouble(
                result.length - 1, transformToOutput(collector, result[result.length - 1]));
          }
          break;
        }
      }
    } else {
      for (int i = 0; i < result.length; i++) {
        collector.putDouble(i, transformToOutput(collector, result[i]));
      }
    }
  }

  private double transformToOutput(PointCollector collector, Complex a) throws Exception {
    double ans = 0;
    switch (result) {
      case "real":
        return a.getReal();
      case "imag":
        return a.getImaginary();
      case "abs":
        return a.abs();
      case "angle":
        return a.getArgument();
      default:
        throw new Exception("It's impossible");
    }
  }
}
