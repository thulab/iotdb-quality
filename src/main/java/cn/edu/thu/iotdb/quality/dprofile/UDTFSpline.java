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
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import cn.edu.thu.iotdb.quality.util.Util;

import java.util.ArrayList;

public class UDTFSpline implements UDTF {
  AkimaSplineInterpolator asi;
  int sample_points; // 插值后返回的采样值个数，由于时间戳必须是整数，因此采样时会选择就近的整数
  ArrayList<Long> timestamp = new ArrayList<>();
  ArrayList<Double> yDouble = new ArrayList<>();
  ArrayList<Double> xDouble = new ArrayList<>();
  Long minimum_timestamp = -1L;
  PolynomialSplineFunction psf;

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.DOUBLE);
    sample_points = udfParameters.getInt("points");
    timestamp.clear();
    xDouble.clear();
    yDouble.clear();
  }

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    Long t = row.getTime();
    if (minimum_timestamp < 0) {
      minimum_timestamp = t;
    }
    timestamp.add(t);
    xDouble.add((Double.valueOf(Long.toString(t - minimum_timestamp))));
    yDouble.add(Util.getValueAsDouble(row));
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {
    if (yDouble.size() >= 4 && sample_points >= 2) { // 4个点以上才进行插值
      asi = new AkimaSplineInterpolator();
      double[] x = ArrayUtils.toPrimitive(xDouble.toArray(new Double[0]));
      double[] y = ArrayUtils.toPrimitive(yDouble.toArray(new Double[0]));
      psf = asi.interpolate(x, y);
      for (int i = 0; i < sample_points; i++) {
        int x_approx =
            (int)
                Math.floor(
                    (x[0] * (sample_points - 1 - i) + x[yDouble.size() - 1] * (i))
                            / (sample_points - 1)
                        + 0.5);
        double yhead = psf.value(x_approx);
        collector.putDouble(minimum_timestamp + (long) x_approx, yhead);
      }
    }
  }
}
