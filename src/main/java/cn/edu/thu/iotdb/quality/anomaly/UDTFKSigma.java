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
  private double mean = 0.0;
  private double var = 0.0;
  private double sumX2 = 0.0;
  private double sumX1 = 0.0;
  private double multipleK;
  private Queue queue;
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
    this.multipleK = udfParameters.getDoubleOrDefault("k", 3);
    this.dataType = udfParameters.getDataType(0);
    this.queue = new Queue(udfParameters.getIntOrDefault("window", 10000), dataType);
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    double value = Util.getValueAsDouble(row);
    long timestamp = row.getTime();
    if (Double.isFinite(value) && !Double.isNaN(value)) {
      if (queue.isFull()) {
        double frontValue = queue.queueFrontValue();
        queue.pop();
        queue.push(timestamp, Util.getValueAsObject(row));
        this.sumX1 = this.sumX1 - frontValue + value;
        this.sumX2 = this.sumX2 - frontValue * frontValue + value * value;
        this.mean = this.sumX1 / queue.getLength();
        this.var = this.sumX2 / queue.getLength() - this.mean * this.mean;
        if (Math.abs(value - mean)
            > multipleK * Math.sqrt(this.var * queue.getLength() / (queue.getLength() - 1))) {
          Util.putValue(collector, dataType, timestamp, Util.getValueAsObject(row));
        }
      } else {
        queue.push(timestamp, Util.getValueAsObject(row));
        this.sumX1 = this.sumX1 + value;
        this.sumX2 = this.sumX2 + value * value;
        this.mean = this.sumX1 / queue.getLength();
        this.var = this.sumX2 / queue.getLength() - this.mean * this.mean;
        if (queue.isFull()) {
          double stddev = Math.sqrt(this.var * queue.getLength() / (queue.getLength() - 1));
          for (int i = 0; i < queue.getLength(); i++) {
            Object v = queue.queueithValue(i);
            timestamp = queue.queueithTime(i);
            double vdouble;
            float vfloat;
            long vlong;
            int vint;
            switch (dataType) {
              case INT32:
                vint = Integer.parseInt(v.toString());
                if (Math.abs((double) vint - mean) > multipleK * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case INT64:
                vlong = Long.parseLong(v.toString());
                if (Math.abs((double) vlong - mean) > multipleK * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case FLOAT:
                vfloat = Float.parseFloat(v.toString());
                if (Math.abs((double) vfloat - mean) > multipleK * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              case DOUBLE:
                vdouble = Double.parseDouble(v.toString());
                if (Math.abs(vdouble - mean) > multipleK * stddev) {
                  Util.putValue(collector, dataType, timestamp, v);
                }
                break;
              default:
                throw new Exception();
            }
          }
        }
      }
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (!queue.isFull() && queue.getLength() > 1) {
      double stddev = Math.sqrt(this.var * queue.getLength() / (queue.getLength() - 1));
      for (int i = 0; i < queue.getLength(); i++) {
        Object v = queue.queueithValue(i);
        long timestamp = queue.queueithTime(i);
        double vdouble;
        float vfloat;
        long vlong;
        int vint;
        switch (dataType) {
          case INT32:
            vint = Integer.parseInt(v.toString());
            if (Math.abs((double) vint - mean) > multipleK * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case INT64:
            vlong = Long.parseLong(v.toString());
            if (Math.abs((double) vlong - mean) > multipleK * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case FLOAT:
            vfloat = Float.parseFloat(v.toString());
            if (Math.abs((double) vfloat - mean) > multipleK * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          case DOUBLE:
            vdouble = Double.parseDouble(v.toString());
            if (Math.abs(vdouble - mean) > multipleK * stddev) {
              Util.putValue(collector, dataType, timestamp, v);
            }
            break;
          default:
            throw new Exception();
        }
      }
    }
  }
}
