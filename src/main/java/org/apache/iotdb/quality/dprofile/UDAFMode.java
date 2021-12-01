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

import org.apache.iotdb.db.exception.query.QueryProcessException;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.eclipse.collections.impl.map.mutable.primitive.DoubleIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.FloatIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongIntHashMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** @author Wang Haoyu */
public class UDAFMode implements UDTF {

  private IntIntHashMap intMap;
  private LongIntHashMap longMap;
  private FloatIntHashMap floatMap;
  private DoubleIntHashMap doubleMap;
  private int booleanCnt;
  private HashMap<String, Integer> stringMap;
  private TSDataType dataType;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator.validateInputSeriesNumber(1);
  }

  @Override
  public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
    udtfc.setAccessStrategy(new RowByRowAccessStrategy()).setOutputDataType(udfp.getDataType(0));
    dataType = udfp.getDataType(0);
    switch (dataType) {
      case INT32:
        intMap = new IntIntHashMap();
        break;
      case INT64:
        longMap = new LongIntHashMap();
        break;
      case FLOAT:
        floatMap = new FloatIntHashMap();
        break;
      case DOUBLE:
        doubleMap = new DoubleIntHashMap();
        break;
      case TEXT:
        stringMap = new HashMap<>();
        break;
      case BOOLEAN:
        booleanCnt = 0;
    }
  }

  @Override
  public void transform(Row row, PointCollector pc) throws Exception {
    switch (dataType) {
      case INT32:
        transformInt(row, pc);
        break;
      case INT64:
        transformLong(row, pc);
        break;
      case FLOAT:
        transformFloat(row, pc);
        break;
      case DOUBLE:
        transformDouble(row, pc);
        break;
      case TEXT:
        transformString(row, pc);
        break;
      case BOOLEAN:
        transformBoolean(row, pc);
    }
  }

  @Override
  public void terminate(PointCollector pc) throws Exception {
    switch (dataType) {
      case INT32:
        terminateInt(pc);
        break;
      case INT64:
        terminateLong(pc);
        break;
      case FLOAT:
        terminateFloat(pc);
        break;
      case DOUBLE:
        terminateDouble(pc);
        break;
      case TEXT:
        terminateString(pc);
        break;
      case BOOLEAN:
        terminateBoolean(pc);
    }
  }

  private void transformInt(Row row, PointCollector pc) throws Exception {
    int v = row.getInt(0);
    intMap.addToValue(v, 1);
  }

  private void transformLong(Row row, PointCollector pc) throws Exception {
    long v = row.getLong(0);
    longMap.addToValue(v, 1);
  }

  private void transformFloat(Row row, PointCollector pc) throws Exception {
    float v = row.getFloat(0);
    floatMap.addToValue(v, 1);
  }

  private void transformDouble(Row row, PointCollector pc) throws Exception {
    double v = row.getDouble(0);
    doubleMap.addToValue(v, 1);
  }

  private void transformBoolean(Row row, PointCollector pc) throws Exception {
    boolean v = row.getBoolean(0);
    booleanCnt = v ? booleanCnt + 1 : booleanCnt - 1;
  }

  private void transformString(Row row, PointCollector pc) throws Exception {
    String v = row.getString(0);
    stringMap.put(v, stringMap.getOrDefault(v, 0) + 1);
  }

  private void terminateInt(PointCollector pc) throws IOException {
    MaxSelector max = new MaxSelector();
    intMap.forEachKeyValue(
        (k, v) -> {
          max.insert(k, v);
        });
    pc.putInt(0, max.getInt());
  }

  private void terminateLong(PointCollector pc) throws IOException {
    MaxSelector max = new MaxSelector();
    longMap.forEachKeyValue(
        (k, v) -> {
          max.insert(k, v);
        });
    pc.putLong(0, max.getLong());
  }

  private void terminateFloat(PointCollector pc) throws IOException {
    MaxSelector max = new MaxSelector();
    floatMap.forEachKeyValue(
        (k, v) -> {
          max.insert(k, v);
        });
    pc.putFloat(0, max.getFloat());
  }

  private void terminateDouble(PointCollector pc) throws IOException {
    MaxSelector max = new MaxSelector();
    doubleMap.forEachKeyValue(
        (k, v) -> {
          max.insert(k, v);
        });
    pc.putDouble(0, max.getDouble());
  }

  private void terminateString(PointCollector pc) throws QueryProcessException, IOException {
    int maxTimes = 0;
    String s = null;
    for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
      String key = entry.getKey();
      Integer value = entry.getValue();
      if (value > maxTimes) {
        maxTimes = value;
        s = key;
      }
    }
    pc.putString(0, s);
  }

  private void terminateBoolean(PointCollector pc) throws IOException {
    pc.putBoolean(0, booleanCnt > 0);
  }

  private class MaxSelector {

    int times;
    int intValue;
    long longValue;
    float floatValue;
    double doubleValue;

    public MaxSelector() {
      times = 0;
    }

    public void insert(int item, int cnt) {
      if (cnt > times) {
        times = cnt;
        intValue = item;
      }
    }

    public void insert(long item, int cnt) {
      if (cnt > times) {
        times = cnt;
        longValue = item;
      }
    }

    public void insert(float item, int cnt) {
      if (cnt > times) {
        times = cnt;
        floatValue = item;
      }
    }

    public void insert(double item, int cnt) {
      if (cnt > times) {
        times = cnt;
        doubleValue = item;
      }
    }

    public int getInt() {
      return intValue;
    }

    public long getLong() {
      return longValue;
    }

    public float getFloat() {
      return floatValue;
    }

    public double getDouble() {
      return doubleValue;
    }
  }
}
