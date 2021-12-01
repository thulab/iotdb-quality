/*
 * Copyright © 2021 thulab (iotdb-quality@protonmail.com)
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
package cn.edu.thu.iotdb.quality.anomaly;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import cn.edu.thu.iotdb.quality.util.Util;
import com.google.common.math.Quantiles;

import java.util.ArrayList;

/*
   流式转换需用户提供Q1与Q3，全局转换则不需要
*/
public class UDTFIQR implements UDTF {
  ArrayList<Double> value = new ArrayList<>();
  ArrayList<Long> timestamp = new ArrayList<>();
  String method = "batch";
  double q1 = 0.0d;
  double q3 = 0.0d;
  double iqr = 0.0d;

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    value.clear();
    timestamp.clear();
    q1 = 0.0d;
    q3 = 0.0d;
    iqr = 0.0d;
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    method = udfParameters.getStringOrDefault("method", "batch");
    if (method.equalsIgnoreCase("stream")) {
      q1 = udfParameters.getDouble("q1");
      q3 = udfParameters.getDouble("q3");
      iqr = q3 - q1;
    }
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("stream") && q3 > q1) {
      double v = Util.getValueAsDouble(row);
      if (v < q1 - 1.5 * iqr || v > q3 + 1.5 * iqr) {
        collector.putDouble(row.getTime(), v);
      }
    } else if (method.equalsIgnoreCase("batch")) {
      double v = Util.getValueAsDouble(row);
      value.add(v);
      timestamp.add(row.getTime());
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("batch")) {
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
