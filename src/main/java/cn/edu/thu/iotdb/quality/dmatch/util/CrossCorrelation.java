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
package cn.edu.thu.iotdb.quality.dmatch.util;

import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

/**
 * @ClassName CrossCorrelation @Description util for UDTFXCorr and UDTFACF @Author thulab @Version
 * 1.0.0
 */
public class CrossCorrelation {
  public static DoubleArrayList calculateCrossCorrelation(
      DoubleArrayList valueArrayList1, DoubleArrayList valueArrayList2) {
    DoubleArrayList correlationArrayList = new DoubleArrayList();
    int length = valueArrayList1.size();
    for (int shift = 1; shift <= length; shift++) {
      double correlation = 0.0;
      for (int i = 0; i < shift; i++) {
        if (Double.isFinite(valueArrayList1.get(i))
            && Double.isFinite(valueArrayList2.get(length - shift + i))) {
          correlation += valueArrayList1.get(i) * valueArrayList2.get(length - shift + i);
        }
      }
      correlation /= shift;
      correlationArrayList.add(correlation);
    }
    for (int shift = 1; shift < length; shift++) {
      double correlation = 0.0;
      for (int i = 0; i < length - shift; i++) {
        if (Double.isFinite(valueArrayList1.get(shift + i))
            && Double.isFinite(valueArrayList2.get(i))) {
          correlation += valueArrayList1.get(shift + i) * valueArrayList2.get(i);
        }
      }
      correlation = correlation / length;
      correlationArrayList.add(correlation);
    }
    return correlationArrayList;
  }
}
