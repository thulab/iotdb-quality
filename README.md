# Introduction

## What is IoTDB-Quality
TsClean is developed independently by the National Engineering Laboratory for Big Data Software, Tsinghua University. It is a data quality system of international advanced level. It focuses on data quality and provides a solid foundation for the application of industrial big data. It has been widely used in many industrial scenes and well received by users. 

IoTDB-Quality is User Defined Functions (UDF) of [Apache IoTDB](https://github.com/apache/iotdb). It implements many key functions of TsClean including data quality indicator calculation, value filling, value repairing, etc.

## Quick Start
1. Download the JAR with all dependencies.
2. Copy the JAR package to `ext\udf` under the directory of IoTDB server.
3. Register the UDFs with the following SQL statements in IoTDB: 

```sql
create function completeness as “cn.edu.thu.dquality.udf.UDTFCompleteness”
create function consistency as “cn.edu.thu.dquality.udf.UDTFConsistency”
create function timeliness as “cn.edu.thu.dquality.udf.UDTFTimeliness”
create function validity as “cn.edu.thu.dquality.udf.UDTFValidity”
create function previousfill as “cn.edu.thu.dquality.udf.UDTFPreviousFill”
create function linearfill as “cn.edu.thu.dquality.udf.UDTFLinearFill”
create function lsgreedyrepair as “cn.edu.thu.dquality.udf.UDTFLsGreedyRepair”
```

## Comparation with InfluxDB

| Data profiling functions of IoTDB-Quality | Univeral functions of InfluxQL |
| :---------------------------------------: | :----------------------------: |
|                  Native                   |            COUNT()             |
|               **Distinct**                |           DISTINCT()           |
|               **Integral**                |           INTEGRAL()           |
|                 **Mean**                  |             MEAN()             |
|                **Median**                 |            MEDIAN()            |
|                 **Mode**                  |             MODE()             |
|                **Spread**                 |            SPREAD()            |
|                **Stddev**                 |            STDDEV()            |
|                  Native                   |             SUM()              |
|               Built-in UDF                |            BOTTOM()            |
|                  Native                   |            FIRST()             |
|                  Native                   |             LAST()             |
|                  Native                   |             MAX()              |
|                  Native                   |             MIN()              |
|              **Percentile**               |          PERCENTILE()          |
|                **Sample**                 |            SAMPLE()            |
|               Built-in UDF                |             TOP()              |
|                  **Cov**                  |                                |
|               **Histogram**               |                                |
|                **Pearson**                |                                |
|                 **Skew**                  |                                |


<!-- ### How to package the project
Enverionment Requirements:
+ Java >= 1.8 (Now 1.8, 11 and 13 have been verified. Please make sure that the paths of environment variables have been set correctly).
+ Maven >= 3.1 

Detailed Instructions：
+ Modify the following dependencies in pom.xml. Please note that you must select the same dependency version as the target IoTDB server version for development.

```xml
<dependency>
    <groupId>org.apache.iotdb</groupId>
    <artifactId>iotdb-server</artifactId>
    <version>0.12.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.iotdb</groupId>
    <artifactId>tsfile</artifactId>
    <version>0.12.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

+ Package with the following command in the root directory of this project. 

```
mvn clean package -DskipTests
```

+ After exection, the JAR package with all dependencies is in `target/udf-tsclean-0.1.0-jar-with-dependencies.jar`. -->
<!-- 
## Introductions of Functions

### Functions about data quality indicators

For time series, we develop 4 data quality indicators: completeness, consistency, timeliness and validity. The UDFs to calculate these indicators are as follows:

|     Name     |      Type of Input Series      |                                                                                  Parameters                                                                                  | Type of Output Series |                                                                                 Description                                                                                 |
| :----------: | :----------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| COMPLETENESS | INT32 / INT64 / FLOAT / DOUBLE | `window`: The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window. |        DOUBLE         | The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the completeness of each window will be output. |
| CONSISTENCY  | INT32 / INT64 / FLOAT / DOUBLE | `window`: The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window. |        DOUBLE         | The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the consistency of each window will be output.  |
|  TIMELINESS  | INT32 / INT64 / FLOAT / DOUBLE | `window`: The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window. |        DOUBLE         |  The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the timeliness of each window will be output.  |
|   VALIDITY   | INT32 / INT64 / FLOAT / DOUBLE | `window`: The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window. |        DOUBLE         |   The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the validity of each window will be output.   |


Example:
```sql
select s1,completeness(s1),consistency(s1),timeliness(s1),validity(s1) from root.test.d1 where time <= 2020-01-01 00:00:30
```

Result:
```
+-----------------------------+---------------+-----------------------------+----------------------------+---------------------------+-------------------------+
|                         Time|root.test.d1.s1|completeness(root.test.d1.s1)|consistency(root.test.d1.s1)|timeliness(root.test.d1.s1)|validity(root.test.d1.s1)|
+-----------------------------+---------------+-----------------------------+----------------------------+---------------------------+-------------------------+
|2020-01-01T00:00:02.000+08:00|          100.0|                        0.875|          0.9333333333333333|         0.9333333333333333|       0.8833333333333333|
|2020-01-01T00:00:03.000+08:00|          101.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:04.000+08:00|          102.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:06.000+08:00|          104.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:08.000+08:00|          126.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:10.000+08:00|          108.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:14.000+08:00|          112.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:15.000+08:00|          113.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:16.000+08:00|          114.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:18.000+08:00|          116.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:20.000+08:00|          118.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:22.000+08:00|          120.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:26.000+08:00|          124.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:28.000+08:00|          126.0|                         null|                        null|                       null|                     null|
|2020-01-01T00:00:30.000+08:00|            NaN|                         null|                        null|                       null|                     null|
+-----------------------------+---------------+-----------------------------+----------------------------+---------------------------+-------------------------+
Total line number = 15
It costs 0.212s
```

### Functions about value filling
We develop some value filling functions for time series. Their UDFs are as follows:


|     Name     | Type of Input Series |                                                                                                                                                                                    Parameters                                                                                                                                                                                    |     Type of Output Series     |                                                                             Description                                                                             |
| :----------: | :------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| PREVIOUSFILL |    FLOAT / DOUBLE    |                                                                                             `beforeRange`: The valid range of filling method. Filling works when the time difference between previous non-`NaN` point and this point is not more than it. By default, it's infinity.                                                                                             | Same type as the input series |                 The `NaN` points in the input series will be filled with the value of the previous non-`NaN` point. The new series will be output.                  |
|  LINEARFILL  |    FLOAT / DOUBLE    | `beforeRange`: The valid range of filling method. Filling works when the time difference between previous non-`NaN` point and this point is not more than it. By default, it's infinity.<br> `afterRange`: The valid range of filling method. Filling works when the time difference between next non-`NaN` point and this point is not more than it. By default, it's infinity. | Same type as the input series | The `NaN` points in the input series will be filled with the value of the previous and next non-`NaN` point by linear interpolation. The new series will be output. |

Example:
```sql
select s2,previousfill(s2,"beforeRange"="1000"),linearfill(s2,"beforeRange"="1000","afterRange"="1000") from root.test.d1 where time <= 2020-01-01 00:00:30
```

Result:
```
+-----------------------------+---------------+---------------------------------------------------+----------------------------------------------------------------------+
|                         Time|root.test.d1.s2|previousfill(root.test.d1.s2, "beforeRange"="1000")|linearfill(root.test.d1.s2, "beforeRange"="1000", "afterRange"="1000")|
+-----------------------------+---------------+---------------------------------------------------+----------------------------------------------------------------------+
|2020-01-01T00:00:00.000+08:00|            NaN|                                                NaN|                                                                   NaN|
|2020-01-01T00:00:01.000+08:00|          120.0|                                              120.0|                                                                 120.0|
|2020-01-01T00:00:02.000+08:00|          120.0|                                              120.0|                                                                 120.0|
|2020-01-01T00:00:04.000+08:00|            NaN|                                                NaN|                                                                   NaN|
|2020-01-01T00:00:05.000+08:00|          130.0|                                              130.0|                                                                 130.0|
|2020-01-01T00:00:06.000+08:00|            NaN|                                              130.0|                                                                 135.0|
|2020-01-01T00:00:07.000+08:00|          140.0|                                              140.0|                                                                 140.0|
+-----------------------------+---------------+---------------------------------------------------+----------------------------------------------------------------------+
Total line number = 7
It costs 0.421s
```
### Functions about value repairing
There may be some outliers in time series. We develop some value repairing functions to repair these outliers. Their UDFs are as follows:


|      Name      |      Type of Input Series      |                                                                                                                                                 Parameters                                                                                                                                                  |     Type of Output Series     |                                                                                     Description                                                                                      |
| :------------: | :----------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|  SCREENREPAIR  | INT32 / INT64 / FLOAT / DOUBLE | `minSpeed`: Speed threshold. Speeds below it will be regarded as outliers. By default, it is the median minus 3 times of median absolute deviation. <br> `maxSpeed`: Speed threshold. Speeds above it will be regarded as outliers. By default, it is the median plus 3 times of median absolute deviation. | Same type as the input series |     The outliers will be repaired by Screen method based on speed constraints. The new series will be output. Before repairing, `NaN` will be filled with linear interpolation.      |
| LSGREEDYREPAIR | INT32 / INT64 / FLOAT / DOUBLE |                                            `center`: Center of the Gaussian distribution of speed changes. By default, it is 0. <br> `sigma`: Standard deviation of the Gaussian distribution of speed changes. By default, it is the median absolute deviation.                                            | Same type as the input series | The outliers will be repaired by LsGreedy method based on speed change likelihoods. The new series will be output. Before repairing, `NaN` will be filled with linear interpolation. |

Example:
```sql
select s1,screenrepair(s1),lsgreedyrepair(s1) from root.test.d1 where time <= 2020-01-01 00:00:30
```

Result:
```
+-----------------------------+---------------+-----------------------------+-------------------------------+
|                         Time|root.test.d1.s1|screenrepair(root.test.d1.s1)|lsgreedyrepair(root.test.d1.s1)|
+-----------------------------+---------------+-----------------------------+-------------------------------+
|2020-01-01T00:00:02.000+08:00|          100.0|                        100.0|                          100.0|
|2020-01-01T00:00:03.000+08:00|          101.0|                        101.0|                          101.0|
|2020-01-01T00:00:04.000+08:00|          102.0|                        102.0|                          102.0|
|2020-01-01T00:00:06.000+08:00|          104.0|                        104.0|                          104.0|
|2020-01-01T00:00:08.000+08:00|          126.0|                        106.0|                          106.0|
|2020-01-01T00:00:10.000+08:00|          108.0|                        108.0|                          108.0|
|2020-01-01T00:00:14.000+08:00|          112.0|                        112.0|                          112.0|
|2020-01-01T00:00:15.000+08:00|          113.0|                        113.0|                          113.0|
|2020-01-01T00:00:16.000+08:00|          114.0|                        114.0|                          114.0|
|2020-01-01T00:00:18.000+08:00|          116.0|                        116.0|                          116.0|
|2020-01-01T00:00:20.000+08:00|          118.0|                        118.0|                          118.0|
|2020-01-01T00:00:22.000+08:00|          120.0|                        120.0|                          120.0|
|2020-01-01T00:00:26.000+08:00|          124.0|                        124.0|                          124.0|
|2020-01-01T00:00:28.000+08:00|          126.0|                        126.0|                          126.0|
|2020-01-01T00:00:30.000+08:00|            NaN|                        128.0|                          128.0|
+-----------------------------+---------------+-----------------------------+-------------------------------+
Total line number = 15
It costs 0.766s
```

## Implementations of Functions
### Data quality indicators

For data quality of time series, we develop the following 4 indicators. Each indicator includes one or more exceptions:


|      Completeness       |     Consistency      |   Timeliness   |        Validity        |
| :---------------------: | :------------------: | :------------: | :--------------------: |
|   Data miss exception   | Redundancy exception | Late exception |    Value exception     |
|     Null exception      |                      |                |  Variation exception   |
| Special value exception |                      |                |    Speed exception     |
|                         |                      |                | Speed change exception |

**Completeness** is calculated as follows:

Completeness = 1 - (N<sub>null</sub> + N<sub>special</sub> + N<sub>miss</sub>) / (N + N<sub>miss</sub>)

where N is the total number of data points in time series, N<sub>null</sub> is the number of points with null value, N<sub>special</sub> is the number of points with specail value and N<sub>miss</sub> is the number of missing points. 


**Consistency** is calculated as follows:

Consistency = 1 - N<sub>redundancy</sub> / N

where N is the total number of data points in time series and N<sub>redundancy</sub> is the number of redundant points.

**Timeliness** is calculated as follows:

Timeliness = 1 - N<sub>late</sub> / N


where N is the total number of data points in time series and N<sub>late</sub> is the number of late points.

**Validity** is calculated as follows:

Validity = 1 - (N<sub>value</sub> + N<sub>variation</sub> + N<sub>speed</sub> + N<sub>speedchange</sub>) / (4 * N)

where N is the total number of data points in time series, N<sub>value</sub> is the number of points breaking value constraint, N<sub>variation</sub> is the number of points breaking variation constraint, N<sub>speed</sub> is the number of points breaking speed constraint and N<sub>speedchange</sub> is the number of points breaking speed change constraint. Significantly, a single data point may break multiple constraints.

Base on the original values and their timestamps, the variaiton, speed and speed change can be calculated as follows:

variation<sub>i</sub> = value<sub>i+1</sub> - value<sub>i</sub>

speed<sub>i</sub> = (value<sub>i+1</sub> - value<sub>i</sub>) / (time<sub>i+1</sub> - time<sub>i</sub>)

speedchange<sub>i</sub> = speed<sub>i+1</sub> - speed<sub>i</sub>

For series x, when the difference between x<sub>i</sub> and the median of x is more than 3 times of the median absolute deviation (MAD, a constant 1.4826 is multipled for asymptotic normality) of x, the constraint is broken, i.e. 

abs(x<sub>i</sub> - mid(x)) > 3 * mad(x)


### Data filling methods

Suppose the timestamp of the data point is t and the previous and next non-`NaN` data points are (t<sub>1</sub>,v<sub>1</sub>) and (t<sub>2</sub>,v<sub>2</sub>) respectively. 
Thus, filling with **Previous** method, the value is

v = v<sub>1</sub>

Filling with **Linear** method, the value is

v = v<sub>1</sub> + (t - t<sub>1</sub>) * (v<sub>2</sub> - v<sub>1</sub>) / (t<sub>2</sub> - t<sub>1</sub>)

### Data repairing methods

**Screen** method is based on speed constraints. Its key idea is making the whole time series meet the speed constraints while repairing as few data points as possible. 
Due to the complexity of glocal optimum, the constraints are relaxed into a sliding window with the combination of candidate range and median principle.
The detailed algorithm is shown in [SIGMOD'15-Screen](https://dl.acm.org/doi/10.1145/2723372.2723730).

**LsGreedy** method is based on speed change likelihoods. Its key idea is modeling the distribution of speed changes and maximizing the likelihood function. 
Generally, the model is Gaussian. In order to reduce the computational complexity, a greedy algorithm is used.
Decreasing the difference between the speed change and the center is equal to increasing the likelihood function.
Kept in a max heap, the data point with the maximum speed change will be modified to make the speed change closer to the center. 
The algorithm terminates when no speed change difference is larger than 3 times of standard deviation. 
The detailed algorithm is shown in [SIGMOD'16-Sequential](https://dl.acm.org/doi/10.1145/2882903.2915233). -->
