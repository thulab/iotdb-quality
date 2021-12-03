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
import org.apache.iotdb.quality.util.Util;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;

/*
   流式转换需用户提供最大值最小值，全局转换则不需要
*/
public class UDTFMinMax implements UDTF {
  ArrayList<Double> value = new ArrayList<>();
  ArrayList<Long> timestamp = new ArrayList<>();
  String method = "batch";
  double min = 0.0d;
  double max = 0.0d;
  boolean flag = true;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
            .validateInputSeriesNumber(1)
            .validateInputSeriesDataType(
                    0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
  }

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    value.clear();
    timestamp.clear();
    min = 0.0d;
    max = 0.0d;
    flag = true;
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    method = udfParameters.getStringOrDefault("method", "batch");
    if (method.equalsIgnoreCase("stream")) {
      min = udfParameters.getDouble("min");
      max = udfParameters.getDouble("max");
    }
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("stream") && max > min) {
      collector.putDouble(row.getTime(), (Util.getValueAsDouble(row) - min) / (max - min));
    } else if (method.equalsIgnoreCase("batch")) {
      double v = Util.getValueAsDouble(row);
      value.add(v);
      timestamp.add(row.getTime());
      if (flag) {
        min = v;
        max = v;
        flag = false;
      } else {
        if (v > max) {
          max = v;
        } else if (v < min) {
          min = v;
        }
      }
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (method.equalsIgnoreCase("batch") && max > min) {
      for (int i = 0; i < value.size(); i++) {
        collector.putDouble(timestamp.get(i), (value.get(i) - min) / (max - min));
      }
    }
  }
}