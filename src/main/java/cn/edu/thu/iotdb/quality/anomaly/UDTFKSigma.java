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

import cn.edu.thu.iotdb.quality.util.Queue;
import cn.edu.thu.iotdb.quality.util.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFKSigma implements UDTF {
  // private long count = 0;
  private double mean = 0.0;
  private double var = 0.0;
  private double sum_x_2 = 0.0;
  private double sum_x_1 = 0.0;
  private double k;
  private Queue q;
  private TSDataType dataType;

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
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(udfParameters.getDataType(0));
    this.k = udfParameters.getDoubleOrDefault("k", 3);
    this.dataType = udfParameters.getDataType(0);
    this.q = new Queue(udfParameters.getIntOrDefault("window", 10000), dataType);
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    double value = Util.getValueAsDouble(row);
    long timestamp = row.getTime();
    if (Double.isFinite(value) && !Double.isNaN(value)) {
      if (q.isFull()) {
        double frontValue = q.queueFrontValue();
        q.pop();
        q.push(timestamp, Util.getValueAsObject(row));
        this.sum_x_1 = this.sum_x_1 - frontValue + value;
        this.sum_x_2 = this.sum_x_2 - frontValue * frontValue + value * value;
        this.mean = this.sum_x_1 / q.getLength();
        this.var = this.sum_x_2 / q.getLength() - this.mean * this.mean;
        if (Math.abs(value - mean)
            > k * Math.sqrt(this.var * q.getLength() / (q.getLength() - 1))) {
          Util.putValue(collector, dataType, timestamp, Util.getValueAsObject(row));
        }
      } else {
        q.push(timestamp, Util.getValueAsObject(row));
        this.sum_x_1 = this.sum_x_1 + value;
        this.sum_x_2 = this.sum_x_2 + value * value;
        this.mean = this.sum_x_1 / q.getLength();
        this.var = this.sum_x_2 / q.getLength() - this.mean * this.mean;
        if (q.isFull()) {
          double stddev = Math.sqrt(this.var * q.getLength() / (q.getLength() - 1));
          for (int i = 0; i < q.getLength(); i++) {
            Object v = q.queueithValue(i);
            timestamp = q.queueithTime(i);
            double vdouble;
            float vfloat;
            long vlong;
            int vint;
            switch (dataType) {
              case INT32:
                vint = Integer.parseInt(v.toString());
                if (Math.abs((double) vint - mean) > k * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case INT64:
                vlong = Long.parseLong(v.toString());
                if (Math.abs((double) vlong - mean) > k * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case FLOAT:
                vfloat = Float.parseFloat(v.toString());
                if (Math.abs((double) vfloat - mean) > k * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case DOUBLE:
                vdouble = Double.parseDouble(v.toString());
                if (Math.abs(vdouble - mean) > k * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
            }
          }
        }
      }
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (!q.isFull() && q.getLength() > 1) {
      double stddev = Math.sqrt(this.var * q.getLength() / (q.getLength() - 1));
      for (int i = 0; i < q.getLength(); i++) {
        Object v = q.queueithValue(i);
        long timestamp = q.queueithTime(i);
        double vdouble;
        float vfloat;
        long vlong;
        int vint;
        switch (dataType) {
          case INT32:
            vint = Integer.parseInt(v.toString());
            if (Math.abs((double) vint - mean) > k * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case INT64:
            vlong = Long.parseLong(v.toString());
            if (Math.abs((double) vlong - mean) > k * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case FLOAT:
            vfloat = Float.parseFloat(v.toString());
            if (Math.abs((double) vfloat - mean) > k * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case DOUBLE:
            vdouble = Double.parseDouble(v.toString());
            if (Math.abs(vdouble - mean) > k * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
        }
      }
    }
  }
}
