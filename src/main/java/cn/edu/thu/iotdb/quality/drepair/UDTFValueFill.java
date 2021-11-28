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
package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import cn.edu.thu.iotdb.quality.drepair.util.*;

import java.util.Locale;

/** @author Zhang Xiaojian */
// This function fills NaN of input series.
public class UDTFValueFill implements UDTF {

  private String method;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator
        .validateInputSeriesDataType(
            0, TSDataType.DOUBLE, TSDataType.FLOAT, TSDataType.INT32, TSDataType.INT64)
        .validate(
            x ->
                ((String) x).equalsIgnoreCase("previous")
                    || ((String) x).equalsIgnoreCase("linear")
                    || ((String) x).equalsIgnoreCase("mean")
                    || ((String) x).equalsIgnoreCase("ar")
                    || ((String) x).equalsIgnoreCase("screen")
                    || ((String) x).equalsIgnoreCase("likelihood"),
            "Illegal method.",
            validator.getParameters().getStringOrDefault("method", "linear"));
  }

  @Override
  public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
    udtfc
        .setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
        .setOutputDataType(udfp.getDataType(0));
    method = udfp.getStringOrDefault("method", "linear");
  }

  @Override
  public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
    ValueFill vf = null;
    method = method.toLowerCase(Locale.ROOT);
    switch (method) {
      case "previous":
        vf = new PreviousFill(rowWindow.getRowIterator());
        break;
      case "linear":
        vf = new LinearFill(rowWindow.getRowIterator());
        break;
      case "mean":
        vf = new MeanFill(rowWindow.getRowIterator());
        break;
      case "ar":
        vf = new ARFill(rowWindow.getRowIterator());
        break;
      case "screen":
        vf = new ScreenFill(rowWindow.getRowIterator());
        break;
      case "likelihood":
        vf = new LikelihoodFill(rowWindow.getRowIterator());
        break;
      default:
        return;
    }
    vf.fill();
    double[] repaired = vf.getFilled();
    long[] time = vf.getTime();
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
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {}
}
