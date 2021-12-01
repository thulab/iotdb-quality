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
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.quality.util.Util;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;

/*
   流式转换需用户提供均值和标准差，全局转换则不需要
*/
public class UDTFZScore implements UDTF {
  ArrayList<Double> value = new ArrayList<>();
  ArrayList<Long> timestamp = new ArrayList<>();
  String method = "batch";
  double avg = 0.0d;
  double sd = 0.0d;
  double sum = 0.0d;
  double square_sum = 0.0d;

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    value.clear();
    timestamp.clear();
    sum = 0.0d;
    square_sum = 0.0d;
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    method = udfParameters.getStringOrDefault("method", "batch");
    if (method.equalsIgnoreCase("stream")) {
      avg = udfParameters.getDouble("avg");
      sd = udfParameters.getDouble("sd");
    }
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("stream") && sd > 0) {
      collector.putDouble(row.getTime(), (Util.getValueAsDouble(row) - avg) / sd);
    } else if (method.equalsIgnoreCase("batch")) {
      double v = Util.getValueAsDouble(row);
      value.add(v);
      timestamp.add(row.getTime());
      sum += v;
      square_sum += v * v;
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("batch")) {
      avg = sum / value.size();
      sd = Math.sqrt(square_sum / value.size() - avg * avg);
      for (int i = 0; i < value.size(); i++) {
        collector.putDouble(timestamp.get(i), (value.get(i) - avg) / sd);
      }
    }
  }
}
