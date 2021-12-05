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
package org.apache.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.quality.dprofile.util.Segment;
import org.apache.iotdb.quality.util.Util;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;

/** This function segment input series into linear parts. */
public class UDTFSegment implements UDTF {

  private int window_size;
  private double max_error;
  private String method;
  private String output;
  private static ArrayList<Long> timestamp = new ArrayList<>();
  private static ArrayList<Double> value = new ArrayList<>();

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesDataType(
            0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE)
        .validate(
            x -> (int) x > 0,
            "Window size should be a positive integer.",
            validator.getParameters().getIntOrDefault("window", 10))
        .validate(
            x -> (double) x >= 0,
            "Error bound should be no less than 0.",
            validator.getParameters().getDoubleOrDefault("error", 0.1))
        .validate(
            x ->
                ((String) x).equalsIgnoreCase("bottom-up") || ((String) x).equalsIgnoreCase("swab"),
            "Method is illegal.",
            validator.getParameters().getStringOrDefault("method", "bottom-up"))
        .validate(
            x -> ((String) x).equalsIgnoreCase("first") || ((String) x).equalsIgnoreCase("all"),
            "Output type is invalid.",
            validator.getParameters().getStringOrDefault("output", "first"));
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
      ArrayList<double[]> temp = Segment.bottom_up(v, max_error);
      seg.addAll(temp);
    } else if (method.equals("swab")) { // haven't tested yet
      seg = Segment.swab_alg(v, ts, max_error, window_size);
    }
    ArrayList<double[]> res = new ArrayList<>();
    for (double[] doubles : seg) {
      res.add(Segment.approximated_segment(doubles));
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
