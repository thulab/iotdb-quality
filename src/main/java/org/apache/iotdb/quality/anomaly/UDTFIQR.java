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
package org.apache.iotdb.quality.anomaly;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.quality.util.Util;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import com.google.common.math.Quantiles;

import java.util.ArrayList;

/*
   流式转换需用户提供Q1与Q3，全局转换则不需要
   Stream swap require user to provide Q1 and Q3, while global swap does not.
*/
public class UDTFIQR implements UDTF {
  ArrayList<Double> value = new ArrayList<>();
  ArrayList<Long> timestamp = new ArrayList<>();
  String compute = "batch";
  double q1 = 0.0d;
  double q3 = 0.0d;
  double iqr = 0.0d;

  public void validate(UDFParameterValidator validator) throws Exception {
    validator
            .validateInputSeriesNumber(1)
            .validateInputSeriesDataType(
                    0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
          throws Exception {
    value.clear();
    timestamp.clear();
    q1 = 0.0d;
    q3 = 0.0d;
    iqr = 0.0d;
    configurations
            .setAccessStrategy(new RowByRowAccessStrategy())
            .setOutputDataType(TSDataType.DOUBLE);
    compute = parameters.getStringOrDefault("compute", "batch");
    if (compute.equalsIgnoreCase("stream")) {
      q1 = parameters.getDouble("q1");
      q3 = parameters.getDouble("q3");
      iqr = q3 - q1;
    }
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (compute.equalsIgnoreCase("stream") && q3 > q1) {
      double v = Util.getValueAsDouble(row);
      if (v < q1 - 1.5 * iqr || v > q3 + 1.5 * iqr) {
        collector.putDouble(row.getTime(), v);
      }
    } else if (compute.equalsIgnoreCase("batch")) {
      double v = Util.getValueAsDouble(row);
      value.add(v);
      timestamp.add(row.getTime());
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (compute.equalsIgnoreCase("batch")) {
      q1 = Quantiles.quartiles().index(1).compute(value);
      q3 = Quantiles.quartiles().index(3).compute(value);
      iqr = q3 - q1;
    }
    for (int i = 0; i < value.size(); i++) {
      double v = value.get(i);
      if (v < q1 - 1.5 * iqr || v > q3 + 1.5 * iqr) {
        collector.putDouble(timestamp.get(i), v);
      }
    }
  }
}