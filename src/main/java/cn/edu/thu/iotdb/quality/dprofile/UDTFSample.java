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
package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.apache.commons.lang3.tuple.Pair;

import cn.edu.thu.iotdb.quality.util.Util;

import java.util.Arrays;
import java.util.Random;

/**
 * 使用蓄水池采样算法进行数据采样的UDTF
 *
 * @author Wang Haoyu
 */
public class UDTFSample implements UDTF {

  private int k; // 采样数

  // 下列变量在蓄水池采样法中使用
  private Pair<Long, Object>[] samples; // 采样得到的样本
  private int num = 0; // 计数器，记录当前一共有多少个元素
  private Random random;
  private TSDataType dataType;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validate(
            k -> (int) k > 0,
            "k should be a positive integer.",
            validator.getParameters().getIntOrDefault("k", 1))
        .validate(
            method ->
                "isometric".equalsIgnoreCase((String) method)
                    || "reservoir".equalsIgnoreCase((String) method),
            "Illegal sampling method.",
            validator.getParameters().getStringOrDefault("method", "reservoir"));
  }

  @Override
  public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations)
      throws Exception {
    // 参数
    this.k = parameters.getIntOrDefault("k", 1);
    this.dataType = parameters.getDataType(0);
    // 算法设置
    String method = parameters.getStringOrDefault("method", "reservoir");
    if ("isometric".equalsIgnoreCase(method)) {
      configurations
          .setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
          .setOutputDataType(parameters.getDataType(0));
    } else {
      configurations
          .setAccessStrategy(new RowByRowAccessStrategy())
          .setOutputDataType(parameters.getDataType(0));
      this.samples = new Pair[this.k];
      this.random = new Random();
    }
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    // 蓄水池采样法
    int x;
    if (this.num < this.k) {
      x = this.num;
    } else {
      x = random.nextInt(num + 1);
    }
    if (x < this.k) {
      Object v = Util.getValueAsObject(row);
      Long t = row.getTime();
      this.samples[x] = Pair.of(t, v);
    }
    this.num++;
  }

  @Override
  public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
    // 等距采样法
    int n = rowWindow.windowSize();
    if (this.k < n) {
      for (long i = 0; i < this.k; i++) {
        long j = Math.floorDiv(i * (long) n, k); // 防止中间数据超过int类型范围
        Row row = rowWindow.getRow((int) j);
        Util.putValue(collector, dataType, row.getTime(), Util.getValueAsObject(row));
      }
    } else { // 采样数大于等于输入序列长度，输出所有元素
      RowIterator iterator = rowWindow.getRowIterator();
      while (iterator.hasNextRow()) {
        Row row = iterator.next();
        Util.putValue(collector, dataType, row.getTime(), Util.getValueAsObject(row));
      }
    }
  }

  @Override
  public void terminate(PointCollector pc) throws Exception {
    if (samples != null) { // 仅用于蓄水池采样
      int m = Math.min(num, k);
      Arrays.sort(samples, 0, m);
      for (int i = 0; i < m; i++) {
        Pair<Long, Object> p = samples[i];
        Util.putValue(pc, dataType, p.getLeft(), p.getRight());
      }
    }
  }
}
