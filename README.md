# TsClean-IoTDB
Language: English | [中文](README_zh.md)
## Introduction
This project is User Defined Functions (UDF) of [Apache IoTDB](https://github.com/apache/iotdb). It implements many key functions of TsClean, a data quality system of time series, including data quality indicator  calculation, value filling, value repairing, etc.

## Quick Start
1. Package this project into a JAR with all of its dependencies.
2. Copy the JAR package to `ext\udf` under the directory of IoTDB server.
3. Register the UDFs with the following SQL statements in IoTDB: 

```sql
create function completeness as “cn.edu.thu.dquality.udf.UDTFCompleteness”
create function consistency as “cn.edu.thu.dquality.udf.UDTFConsistency”
create function timeliness as “cn.edu.thu.dquality.udf.UDTFTimeliness”
create function validity as “cn.edu.thu.dquality.udf.UDTFValidity”
create function previousfill as “cn.edu.thu.dquality.udf.UDTFPreviousFill”
create function linearfill as “cn.edu.thu.dquality.udf.UDTFLinearFill”
```

### How to package the project
Enverionment Requirements:
+ Java >= 1.8 (Now 1.8, 11 and 13 have been verified. Please make sure that the paths of environment variables have been set correctly).
+ Maven >= 3.1 

Detailed Instructions：
1. Modify the following dependencies in pom.xml. Please note that you must select the same dependency version as the target IoTDB server version for development.
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
2. Package with the following command in the root directory of this project. 
```
mvn clean package -DskipTests
```
3. After exection, the JAR package with all dependencies is in target/udf-tsclean-0.1.0-jar-with-dependencies.jar.

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
| PREVIOUSFILL |     FLOAT/DOUBLE     |                                                                                             `beforeRange`: The valid range of filling method. Filling works when the time difference between previous non-`NaN` point and this point is not more than it. By default, it's infinity.                                                                                             | Same type as the input series |                 The `NaN` points in the input series will be filled with the value of the previous non-`NaN` point. The new series will be output.                  |
|  LINEARFILL  |     FLOAT/DOUBLE     | `beforeRange`: The valid range of filling method. Filling works when the time difference between previous non-`NaN` point and this point is not more than it. By default, it's infinity.<br> `afterRange`: The valid range of filling method. Filling works when the time difference between next non-`NaN` point and this point is not more than it. By default, it's infinity. | Same type as the input series | The `NaN` points in the input series will be filled with the value of the previous and next non-`NaN` point by linear interpolation. The new series will be output. |

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

![](http://latex.codecogs.com/svg.latex?Completeness=1-\frac{N_{null}+N_{special}+N_{miss}}{N+N_{miss}})

where N is the total number of data points in time series, N<sub>null</sub> is the number of points with null value, N<sub>special</sub> is the number of points with specail value and N<sub>miss</sub> is the number of missing points. 


**Consistency** is calculated as follows:

![](http://latex.codecogs.com/svg.latex?Consistency=1-\frac{N_{redundancy}}{N})


where N is the total number of data points in time series and N<sub>redundancy</sub> is the number of redundant points.

**Timeliness** is calculated as follows:

![](http://latex.codecogs.com/svg.latex?Timeliness=1-\frac{N_{late}}{N})

where N is the total number of data points in time series and N<sub>late</sub> is the number of late points.

**Validity** is calculated as follows:

![](http://latex.codecogs.com/svg.latex?Validity=1-\frac{N_{value}+N_{variation}+N_{speed}+N_{speedchange}}{4N})

where N is the total number of data points in time series, N<sub>value</sub> is the number of points breaking value constraint, N<sub>variation</sub> is the number of points breaking variation constraint, N<sub>speed</sub> is the number of points breaking speed constraint and N<sub>speedchange</sub> is the number of points breaking speed change constraint. Significantly, a single data point may break multiple constraints.

Base on the original values and their timestamps, the variaiton, speed and speed change can be calculated as follows:

![](http://latex.codecogs.com/svg.latex?variation_{i}=value_{i+1}-value_{i})

![](http://latex.codecogs.com/svg.latex?speed_{i}=\frac{value_{i+1}-value_{i}}{time_{i+1}-time_{i}})

![](http://latex.codecogs.com/svg.latex?speedchange_{i}=speed_{i+1}-speed_{i})


For series x, when the difference between x<sub>i</sub> and the median of x is more than 3 times of the median absolute deviation (MAD) of x, the constraint is broken, i.e. 

![](http://latex.codecogs.com/svg.latex?|x_i-mid(x)|>3*mad(x))
