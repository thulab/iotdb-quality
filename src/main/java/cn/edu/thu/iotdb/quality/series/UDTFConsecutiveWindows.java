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
package cn.edu.thu.iotdb.quality.series;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.apache.commons.lang3.tuple.Pair;

import cn.edu.thu.iotdb.quality.util.Util;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 该UDTF用于寻找序列中的指定长度的连续子序列
 *
 * @author Wang Haoyu
 */
public class UDTFConsecutiveWindows implements UDTF {

  private static final int MAXLEN = 128;
  private long gap, len;
  private final ArrayList<Pair<Long, Boolean>> window = new ArrayList<>(MAXLEN);
  private long first, last;
  private int count;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validate(
            x -> (long) x > 0,
            "gap should be a time period whose unit is ms, s, m, h.",
            Util.parseTime(validator.getParameters().getStringOrDefault("gap", "1ms")))
        .validate(
            x -> (long) x > 0,
            "length should be a time period whose unit is ms, s, m, h.",
            Util.parseTime(validator.getParameters().getString("length")));
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    configurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.INT32);
    gap = Util.parseTime(parameters.getStringOrDefault("gap", "0ms"));
    len = Util.parseTime(parameters.getString("length"));
    first = last = -gap;
    count = gap == 0 ? 0 : (int) (len / gap + 1);
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (gap == 0) {
      if (window.size() < MAXLEN) { // 窗口没有装满
        window.add(Pair.of(row.getTime(), check(row)));
      } else {
        gap = calculateGap();
        cleanWindow(collector);
      }
    } else {
      process(row.getTime(), check(row), collector);
    }
  }

  /**
   * 检查给定的行中是否存在空值
   *
   * @param row 给定的行
   * @return 存在空值时返回true，否则返回false
   */
  private boolean check(Row row) {
    for (int i = 0; i < row.size(); i++) {
      if (row.isNull(i)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 根据窗口内的数据计算标准间隔
   *
   * @return 标准间隔
   */
  private long calculateGap() {
    long[] time = new long[window.size() - 1];
    for (int i = 0; i < time.length; i++) {
      time[i] = window.get(i + 1).getLeft() - window.get(i).getLeft();
    }
    long gap = Util.mode(time);
    count = (int) (len / gap) + 1;
    return gap;
  }

  /**
   * 清空窗口内的数据，并处理其中的行
   *
   * @param collector 输出器
   */
  private void cleanWindow(PointCollector collector) throws IOException {
    if (window.isEmpty()) {
      return;
    }
    first = last = -gap;
    for (Pair<Long, Boolean> p : window) {
      process(p.getLeft(), p.getRight(), collector);
    }
  }

  /**
   * 对数据中的一行进行处理
   *
   * @param time 时间戳
   * @param nullExist 数据行中是否存在空值（true代表存在空值）
   * @param collector 输出器
   * @throws IOException e
   */
  void process(long time, boolean nullExist, PointCollector collector) throws IOException {
    if (nullExist) { // 数据行存在空值，连续序列结束
      for (; first + len <= last; first += gap) {
        collector.putInt(first, count);
      }
      first = last = -gap;
    } else {
      if (time == last + gap) { // 数据行非空且间隔正常，连续序列正常增长
        last = time;
      } else { // 数据行非空但间隔不正常，连续序列结束，以当前数据行为起点开启新的序列
        for (; first + len <= last; first += gap) {
          collector.putInt(first, count);
        }
        first = last = time;
      }
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (gap == 0) {
      gap = calculateGap();
      cleanWindow(collector);
    }
    for (; first + len <= last; first += gap) {
      collector.putInt(first, count);
    }
  }
}
