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

package org.apache.iotdb.quality.frequency;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.quality.util.Util;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.jtransforms.fft.DoubleFFT_1D;

/** This function does Short Time Fourier Transform for input series. */
public class UDTFSTFT implements UDTF {

  private int nfft;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validate(
            x -> (int) x > 0,
            "Nfft should be a positive integer.",
            validator.getParameters().getIntOrDefault("nfft", Integer.MAX_VALUE));
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    this.nfft = parameters.getIntOrDefault("nfft", Integer.MAX_VALUE);
    configurations
        .setAccessStrategy(new SlidingSizeWindowAccessStrategy(nfft))
        .setOutputDataType(TSDataType.DOUBLE);
  }

  @Override
  public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
    int n = rowWindow.windowSize();
    DoubleFFT_1D fft = new DoubleFFT_1D(n);
    double[] a = new double[2 * n];
    for (int i = 0; i < n; i++) {
      a[2 * i] = Util.getValueAsDouble(rowWindow.getRow(i));
      a[2 * i + 1] = 0;
    }
    fft.complexForward(a);
    long t0 = rowWindow.getRow(0).getTime();
    for (int i = 0; i < n; i++) {
      collector.putDouble(t0 + i, Math.sqrt(a[2 * i] * a[2 * i] + a[2 * i + 1] * a[2 * i + 1]));
    }
  }
}
