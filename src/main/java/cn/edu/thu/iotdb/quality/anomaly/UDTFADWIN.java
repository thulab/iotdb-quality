/*
 * Copyright © 2021 thulab (iotdb-quality@protonmail.com)
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

package cn.edu.thu.iotdb.quality.anomaly;

import cn.edu.thu.iotdb.quality.util.Util;
import java.util.ArrayList;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

//extends Estimator
public class UDTFADWIN implements UDTF {
  private static class List {
    protected int count;
    protected ListItem head;
    protected ListItem tail;

    //post: initializes the list to be empty.
    public List() {
      clear();
      addToHead();
    }

    /* Interface Store Methods */
    //post: returns the number of elements in the list.
    public int size() {
      return this.count;
    }

    //post: returns the number of elements in the list.
    public ListItem head() {
      return this.head;
    }

    //post: returns the number of elements in the list.
    public ListItem tail() {
      return this.tail;
    }

    //post: returns the true iff store is empty.
    public boolean isEmpty() {
      return (this.size() == 0);
    }

    //post: clears the list so that it contains no elements.
    public void clear() {
      this.head = null;
      this.tail = null;
      this.count = 0;
    }

    /* Interface List Methods
    * pre: anObject is non-null
    * post: the object is added to the beginning of the list
    */
    public void addToHead() {
      this.head = new ListItem(this.head, null);
      if (this.tail == null) {
        this.tail = this.head;
      }
      this.count++;
    }

    //pre: list is not empty
    //post: removes and returns first object from the list
    public void removeFromHead() {
      this.head = this.head.next();
      if (this.head != null) {
        this.head.setPrevious(null);
      } else {
        this.tail = null;
      }
      this.count--;
    }

    //pre: anObject is non-null
    //post: the object is added at the end of the list
    public void addToTail() {
      this.tail = new ListItem(null, this.tail);
      if (this.head == null) {
        this.head = this.tail;
      }
      this.count++;
    }

    //pre: list is not empty
    //post: the last object in the list is removed and returned
    public void removeFromTail() {
      this.tail = this.tail.previous();
      if (this.tail == null) {
        this.head = null;
      } else {
        this.tail.setNext(null);
      }
      this.count--;
    }
  }

  //protected Object data;
  private static class ListItem {
    protected ListItem next;
    protected ListItem previous;
    protected int bucketSizeRow = 0;
    protected int maxBuckets = UDTFADWIN.maxBuckets;
    protected double[] bucketTotal = new double[maxBuckets + 1];
    protected double[] bucketVariance = new double[maxBuckets + 1];

    //post: initializes the node to be a tail node
    //containing the given value.
    public ListItem() {
      this(null, null);
    }

    public void clear() {
      bucketSizeRow = 0;
      for (int k = 0; k <= maxBuckets; k++) {
        clearBucket(k);
      }
    }

    private void clearBucket(int k) {
      setTotal(0, k);
      setVariance(0, k);
    }

    //post: initializes the node to contain the given
    //object and link to the given next node.
    public ListItem(ListItem nextNode, ListItem previousNode) {
      this.next = nextNode;
      this.previous = previousNode;
      if (nextNode != null) {
        nextNode.previous = this;
      }
      if (previousNode != null) {
        previousNode.next = this;
      }
      clear();
    }

    //insert a Bucket at the end
    public void insertBucket(double value, double variance) {
      int k = bucketSizeRow;
      bucketSizeRow++;
      //Insert new bucket
      setTotal(value, k);
      setVariance(variance, k);
    }

    //Removes the first Bucket
    public void removeBucket() {
      compressBucketsRow(1);
    }

    //Delete first elements
    public void compressBucketsRow(int numberItemsDeleted) {
      for (int k = numberItemsDeleted; k <= maxBuckets; k++) {
        bucketTotal[k - numberItemsDeleted] = bucketTotal[k];
        bucketVariance[k - numberItemsDeleted] = bucketVariance[k];
      }
      for (int k = 1; k <= numberItemsDeleted; k++) {
        clearBucket(maxBuckets - k + 1);
      }
      bucketSizeRow -= numberItemsDeleted;
    }

    //post: returns the previous node.
    public ListItem previous() {
      return this.previous;
    }

    //post: sets the previous node to be the given node
    public void setPrevious(ListItem previous) {
      this.previous = previous;
    }

    //post: returns the next node.
    public ListItem next() {
      return this.next;
    }

    //post: sets the next node to be the given node
    public void setNext(ListItem next) {
      this.next = next;
    }

    //post: returns the element in this node
    public double total(int k) {
      return bucketTotal[k];
    }

    //post: returns the element in this node
    public double variance(int k) {
      return bucketVariance[k];
    }

    //post: sets the element in this node to the given object.
    public void setTotal(double value, int k) {
      bucketTotal[k] = value;
    }

    //post: sets the element in this node to the given object.
    public void setVariance(double value, int k) {
      bucketVariance[k] = value;
    }
  }

  private static int mintMinimLongitudWindow = 10; // 10
  private final double mdbldelta; // .1;
  private int number = 0;
  private int mintClock = 32;
  private double mdblWidth = 0; // Mean of Width = mdblWidth/Number of items
  // BUCKET
  public static final int maxBuckets = 5;
  private int lastBucketRow = 0;
  private double total = 0;
  private double variance = 0;
  private int width = 0;
  private int bucketNumber = 0;
  private int detect = 0;
  private int numberDetections = 0;
  private int detectTwice = 0;
  private boolean blnBucketDeleted = false;
  private int bucketNumberMax = 0;
  private int mintMinWinLength = 5;
  private List listRowBuckets;

  public boolean getChange() {
    return blnBucketDeleted;
  }

  public void resetChange() {
    blnBucketDeleted = false;
  }

  public int getBucketsUsed() {
    return bucketNumberMax;
  }

  public int getWidth() {
    return width;
  }

  public void setClock(int intClock) {
    mintClock = intClock;
  }

  public int getClock() {
    return mintClock;
  }

  public boolean getWarning() {
    return false;
  }

  public boolean getDetect() {
    return (detect == number);
  }

  public int getNumberDetections() {
    return numberDetections;
  }

  public double getTotal() {
    return total;
  }

  public double getEstimation() {
    return total / width;
  }

  public double getVariance() {
    return variance / width;
  }

  public double getWidthT() {
    return mdblWidth;
  }

  //Init buckets
  private void initBuckets() {
    listRowBuckets = new List();
    lastBucketRow = 0;
    total = 0;
    variance = 0;
    width = 0;
    bucketNumber = 0;
  }

  private void insertElement(double value) {
    width++;
    insertElementBucket(value, listRowBuckets.head());
    double incVariance = 0;
    if (width > 1) {
      incVariance =
              (width - 1) * (value - total / (width - 1)) * (value - total / (width - 1)) / width;
    }
    variance += incVariance;
    total += value;
    compressBuckets();
  }

  private void insertElementBucket(double value, ListItem node) {
    // Insert new bucket
    node.insertBucket(value, 0);
    bucketNumber++;
    if (bucketNumber > bucketNumberMax) {
      bucketNumberMax = bucketNumber;
    }
  }

  private int bucketSize(int row) {
    return (int) Math.pow(2, row);
  }

  // LIST
  // Update statistics
  public int deleteElement() {
    ListItem node;
    node = listRowBuckets.tail();
    int n1 = bucketSize(lastBucketRow);
    width -= n1;
    total -= node.total(0);
    double u1 = node.total(0) / n1;
    double incVariance =
        node.variance(0) + n1 * width * (u1 - total / width) * (u1 - total / width) / (n1 + width);
    variance -= incVariance;

    // Delete Bucket
    node.removeBucket();
    bucketNumber--;
    if (node.bucketSizeRow == 0) {
      listRowBuckets.removeFromTail();
      lastBucketRow--;
    }
    return n1;
  }

  // Traverse the list of buckets in increasing order
  public void compressBuckets() {
    int n1;
    int n2;
    double u1;
    double u2;
    double incVariance;
    ListItem cursor;
    ListItem nextNode;
    cursor = listRowBuckets.head();
    int i = 0;
    do {
      // Find the number of buckets in a row
      int k = cursor.bucketSizeRow;
      // If the row is full, merge buckets
      if (k == maxBuckets + 1) {
        nextNode = cursor.next();
        if (nextNode == null) {
          listRowBuckets.addToTail();
          nextNode = cursor.next();
          lastBucketRow++;
        }
        n1 = bucketSize(i);
        n2 = bucketSize(i);
        u1 = cursor.total(0) / n1;
        u2 = cursor.total(1) / n2;
        incVariance = n1 * n2 * (u1 - u2) * (u1 - u2) / (n1 + n2);

        nextNode.insertBucket(
            cursor.total(0) + cursor.total(1),
            cursor.variance(0) + cursor.variance(1) + incVariance);
        bucketNumber++;
        cursor.compressBucketsRow(2);
        if (nextNode.bucketSizeRow <= maxBuckets) {
          break;
        }
      } else {
        break;
      }
      cursor = cursor.next();
      i++;
    } while (cursor != null);
  }

  public int setInput(double intEntrada, double delta) {
    int returnValue = -1; // return value
    boolean blnChange = false;
    boolean blnExit = false;
    ListItem cursor;
    number++;

    // 1,2)Increment window in one element
    insertElement(intEntrada);
    blnBucketDeleted = false;
    // 3)Reduce  window
    if (getWidth() > mintMinimLongitudWindow) {
      boolean blnReduceWidth = true;
      while (blnReduceWidth) {
        blnReduceWidth = false;
        blnExit = false;
        int n0 = 0;
        int n1 = width;
        double u0 = 0;
        double u1 = getTotal();
        double v0 = 0;
        double v1 = variance;
        double n2 = 0;
        double u2 = 0;

        cursor = listRowBuckets.tail();
        int i = lastBucketRow;
        do {
          for (int k = 0; k <= (cursor.bucketSizeRow - 1); k++) {
            n2 = bucketSize(i);
            u2 = cursor.total(k);
            if (n0 > 0) {
              v0 += cursor.variance(k)
                      + (double) n0 * n2 * (u0 / n0 - u2 / n2) * (u0 / n0 - u2 / n2) / (n0 + n2);
            }
            if (n1 > 0) {
              v1 -= cursor.variance(k)
                      + (double) n1 * n2 * (u1 / n1 - u2 / n2) * (u1 / n1 - u2 / n2) / (n1 + n2);
            }
            n0 += bucketSize(i);
            n1 -= bucketSize(i);
            u0 += cursor.total(k);
            u1 -= cursor.total(k);

            if (i == 0 && k == cursor.bucketSizeRow - 1) {
              blnExit = true;
              break;
            }
            double absvalue = (double) (u0 / n0) - (u1 / n1); // n1<WIDTH-mintMinWinLength-1
            if ((n1 > mintMinWinLength + 1 && n0 > mintMinWinLength + 1) // Diference NEGATIVE
                && blnCutexpression(n0, n1, u0, u1, v0, v1, absvalue, delta)) {
              blnBucketDeleted = true;
              detect = number;
              if (detect == 0) {
                detect = number;
              } else if (detectTwice == 0) {
                detectTwice = number;
              }
              blnReduceWidth = true; // Diference
              blnChange = true;
              returnValue = n0;
              if (getWidth() > 0) { // Reduce width of the window
                int removeNumber = deleteElement();
                n0 -= removeNumber;
                blnExit = true;
                break;
              }
            } // End if
          } // Next k
          cursor = cursor.previous();
          i--;
        } while (((!blnExit && cursor != null)));
      } // End While // Diference
    } // End if

    mdblWidth += getWidth();
    if (blnChange) {
      numberDetections++;
    }
    return blnChange ? width - returnValue : -1;
  }

  private boolean blnCutexpression(
          int n0, int n1, double u0, double u1,
          double v0, double v1, double absvalue, double delta) {
    int n = getWidth();
    double dd = Math.log(2 * Math.log(n) / delta);
    // Formula Gener 2008
    double v = getVariance();
    double m = ((double) 1 / ((n0 - mintMinWinLength + 1)))
            + ((double) 1 / ((n1 - mintMinWinLength + 1)));
    double epsilon = Math.sqrt(2 * m * v * dd) + (double) 2 / 3 * dd * m;
    return (Math.abs(absvalue) > epsilon);
  }

  public UDTFADWIN() {
    mdbldelta = delta;
    initBuckets();
    detect = 0;
    numberDetections = 0;
    detectTwice = 0;
  }

  public UDTFADWIN(double d) {
    mdbldelta = d;
    initBuckets();
    detect = 0;
    numberDetections = 0;
    detectTwice = 0;
  }

  public UDTFADWIN(int cl) {
    mdbldelta = delta;
    initBuckets();
    detect = 0;
    numberDetections = 0;
    detectTwice = 0;
    mintClock = cl;
  }

  public String getEstimatorInfo() {
    return "ADWIN;;";
  }

  public void setW(int w) {}

  private double delta;
  private String output;
  private int count = -1;

  @Override
  public void validate(UDFParameterValidator validator) throws Exception {
    validator.validateInputSeriesDataType(
        0, TSDataType.INT32, TSDataType.INT64, TSDataType.FLOAT, TSDataType.DOUBLE);
  }

  @Override
  public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations)
      throws Exception {
    udtfConfigurations
        .setAccessStrategy(new RowByRowAccessStrategy())
        .setOutputDataType(TSDataType.INT32);
    this.delta = udfParameters.getDoubleOrDefault("delta", 0.01);
    // this.output=udfParameters.getStringOrDefault("output","drift");
    int windowsize = udfParameters.getIntOrDefault("windowsize", 200);
    int minmiumwindow = udfParameters.getIntOrDefault("subwindow", 50);
    mintMinimLongitudWindow = windowsize;
    mintMinWinLength = minmiumwindow - 2;
  }

  ArrayList<Long> timelist = new ArrayList<>();
  ArrayList<Integer> labellist = new ArrayList<>();

  @Override
  public void transform(Row row, PointCollector collector) throws Exception {
    timelist.add(row.getTime());
    labellist.add(0);
    count++;
    int k = setInput(Util.getValueAsDouble(row), delta);
    if (k > 0) {
      // collector.putInt(row.getTime(),1);
      if (labellist.get(count - k) == 0) {
        collector.putInt(timelist.get(count - k), 1);
      }
      labellist.set(count - k, 1);
    }
  }

  @Override
  public void terminate(PointCollector collector) throws Exception {}
}
