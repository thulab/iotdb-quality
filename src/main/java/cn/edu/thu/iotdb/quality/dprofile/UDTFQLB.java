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
package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import cn.edu.thu.iotdb.quality.util.Util;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

// correlation calculation copied from SelfCorrelation
public class UDTFQLB implements UDTF {

  private DoubleArrayList valueArrayList = new DoubleArrayList();
  private int m = 0;
  private double qlb = 0.0f;

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    m = udfParameters.getIntOrDefault("shift", 0);
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    if (row.isNull(0)) {
      valueArrayList.add(Double.NaN);
    } else {
      valueArrayList.add(Util.getValueAsDouble(row, 0));
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    int n = valueArrayList.size();
    if (m <= 0 || m >= n) {
      m = n - 2;
    }
    for (int shift = 1; shift <= m; shift++) {
      double correlation = 0.0;
      for (int i = 0; i < n - shift; i++) {
        if (Double.isFinite(valueArrayList.get(shift + i))
            && Double.isFinite(valueArrayList.get(i))) {
          correlation += valueArrayList.get(shift + i) * valueArrayList.get(i);
        }
      }
      correlation = correlation / n;
      collector.putDouble(n + shift, correlation);
      qlb += correlation * correlation / (n - shift) * n * (n + 2);
      ChiSquaredDistribution qlbdist = new ChiSquaredDistribution(shift);
      double qlbprob = 1.0 - qlbdist.cumulativeProbability(qlb);
      collector.putDouble(shift, qlbprob);
    }
  }

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesNumber(1)
        .validateInputSeriesDataType(
            0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
  }

  @Override
  public void beforeDestroy() {
    valueArrayList.clear();
  }
}
