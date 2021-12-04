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
package cn.edu.thu.iotdb.quality.dquality;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingTimeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import cn.edu.thu.iotdb.quality.util.Util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This class calculates Completeness of time series. */
public class UDTFCompleteness implements UDTF {

  private boolean downtime;

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
    String sizeWindowStr = parameters.getStringOrDefault("sizewindow", String.valueOf(Long.MAX_VALUE));
    long sizeWindow = Long.parseLong(sizeWindowStr);

    String timeWindowStr = parameters.getStringOrDefault("timewindow", "default");
    long timeWindow = Util.parseTime(timeWindowStr);

    if (parameters.hasAttribute("sizewindow")) {
      configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy((int) sizeWindow));
    }
    else {
      configurations.setAccessStrategy(new SlidingTimeWindowAccessStrategy(timeWindow));
    }
    downtime = parameters.getBooleanOrDefault("downtime", true);
    configurations.setOutputDataType(TSDataType.DOUBLE);
  }

  @Override
  public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
    try {
      if (rowWindow.windowSize() > TimeSeriesQuality.WINDOWSIZE) {
        TimeSeriesQuality tsq = new TimeSeriesQuality(rowWindow.getRowIterator());
        tsq.setDowntime(downtime);
        tsq.timeDetect();
        collector.putDouble(rowWindow.getRow(0).getTime(), tsq.getCompleteness());
      }
    } catch (IOException ex) {
      Logger.getLogger(UDTFCompleteness.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
