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
package org.apache.iotdb.quality.drepair.util;

import org.apache.iotdb.db.query.udf.api.access.RowIterator;

public class MeanFill extends ValueFill {
  public MeanFill(RowIterator dataIterator) throws Exception {
    super(dataIterator);
    calMeanAndVar();
  }

  public MeanFill(String filename) throws Exception {
    super(filename);
    calMeanAndVar();
  }

  @Override
  public void fill() {
    for (int i = 0; i < original.length; i++) {
      double yt = original[i];
      if (!Double.isNaN(yt)) {
        repaired[i] = yt;
      } else {
        repaired[i] = mean;
      }
    }
  }
}
