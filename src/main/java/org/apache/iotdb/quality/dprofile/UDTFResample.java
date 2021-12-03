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

import java.text.SimpleDateFormat;

/** @author Wang Haoyu */
public class UDTFResample implements UDTF {

  private Resampler resampler;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validate(
            x -> (long) x > 0,
            "gap should be a time period whose unit is ms, s, m, h, d.",
            Util.parseTime(validator.getParameters().getString("every")))
        .validate(
            x ->
                "min".equals(x)
                    || "max".equals(x)
                    || "mean".equals(x)
                    || "median".equals(x)
                    || "first".equals(x)
                    || "last".equals(x),
            "aggr should be min, max, mean, median, first, last.",
            validator.getParameters().getStringOrDefault("aggr", "mean").toLowerCase())
        .validate(
            x -> "nan".equals(x) || "ffill".equals(x) || "bfill".equals(x) || "linear".equals(x),
            "aggr should be min, max, mean, median, first, last.",
            validator.getParameters().getStringOrDefault("interp", "nan").toLowerCase());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (validator.getParameters().hasAttribute("start")) {
      validator.validate(
          x -> (long) x > 0,
          "start should conform to the format yyyy-MM-dd HH:mm:ss.",
          format.parse(validator.getParameters().getString("start")).getTime());
    }
    if (validator.getParameters().hasAttribute("end")) {
      validator.validate(
          x -> (long) x > 0,
          "end should conform to the format yyyy-MM-dd HH:mm:ss.",
          format.parse(validator.getParameters().getString("end")).getTime());
    }
  }

  @Override
  public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
    udtfc.setAccessStrategy(new RowByRowAccessStrategy()).setOutputDataType(TSDataType.DOUBLE);
    long newPeriod = Util.parseTime(udfp.getString("every"));
    String aggregator = udfp.getStringOrDefault("aggr", "mean").toLowerCase();
    String interpolator = udfp.getStringOrDefault("interp", "nan").toLowerCase();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long startTime = -1, endTime = -1;
    if (udfp.hasAttribute("start")) {
      startTime = format.parse(udfp.getString("start")).getTime();
    }
    if (udfp.hasAttribute("end")) {
      endTime = format.parse(udfp.getString("end")).getTime();
    }
    resampler = new Resampler(newPeriod, aggregator, interpolator, startTime, endTime);
  }

  @Override
  public void transform(Row row, PointCollector pc) throws Exception {
    resampler.insert(row.getTime(), Util.getValueAsDouble(row));
    while (resampler.hasNext()) { // 尽早输出，以减少输出缓冲的压力
      pc.putDouble(resampler.getOutTime(), resampler.getOutValue());
      resampler.next();
    }
  }

  @Override
  public void terminate(PointCollector pc) throws Exception {
    resampler.flush();
    while (resampler.hasNext()) {
      pc.putDouble(resampler.getOutTime(), resampler.getOutValue());
      resampler.next();
    }
  }
}
