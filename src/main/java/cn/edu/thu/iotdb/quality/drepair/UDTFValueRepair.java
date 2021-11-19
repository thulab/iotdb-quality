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
package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

import cn.edu.thu.iotdb.quality.drepair.util.LsGreedy;
import cn.edu.thu.iotdb.quality.drepair.util.Screen;
import cn.edu.thu.iotdb.quality.drepair.util.ValueRepair;

/**
 * 用于修复时间序列异常值的UDTF：目前已支持Screen和LsGreedy
 *
 * @author Wang Haoyu
 */
public class UDTFValueRepair implements UDTF {

  String method;
  double minSpeed, maxSpeed;
  double center, sigma;

  @Override
  public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
    udtfc
        .setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
        .setOutputDataType(udfp.getDataType(0));
    method = udfp.getStringOrDefault("method", "screen");
    minSpeed = udfp.getDoubleOrDefault("minSpeed", Double.NaN);
    maxSpeed = udfp.getDoubleOrDefault("maxSpeed", Double.NaN);
    center = udfp.getDoubleOrDefault("center", 0);
    sigma = udfp.getDoubleOrDefault("sigma", Double.NaN);
  }

  @Override
  public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
    // 设置修复方法及参数
    ValueRepair vr = null;
    if ("screen".equalsIgnoreCase(method)) {
      Screen screen = new Screen(rowWindow.getRowIterator());
      if (!Double.isNaN(minSpeed)) {
        screen.setSmin(minSpeed);
      }
      if (!Double.isNaN(maxSpeed)) {
        screen.setSmax(maxSpeed);
      }
      vr = screen;
    } else if ("lsgreedy".equalsIgnoreCase(method)) {
      LsGreedy lsGreedy = new LsGreedy(rowWindow.getRowIterator());
      if (!Double.isNaN(sigma)) {
        lsGreedy.setSigma(sigma);
      }
      lsGreedy.setCenter(center);
      vr = lsGreedy;
    } else {
      throw new Exception("Illegal method.");
    }
    // 开始修复并输出结果
    vr.repair();
    double[] repaired = vr.getRepaired();
    long[] time = vr.getTime();
    switch (rowWindow.getDataType(0)) {
      case DOUBLE:
        for (int i = 0; i < time.length; i++) {
          collector.putDouble(time[i], repaired[i]);
        }
        break;
      case FLOAT:
        for (int i = 0; i < time.length; i++) {
          collector.putFloat(time[i], (float) repaired[i]);
        }
        break;
      case INT32:
        for (int i = 0; i < time.length; i++) {
          collector.putInt(time[i], (int) Math.round(repaired[i]));
        }
        break;
      case INT64:
        for (int i = 0; i < time.length; i++) {
          collector.putLong(time[i], Math.round(repaired[i]));
        }
        break;
      default:
        throw new Exception();
    }
  }
}
