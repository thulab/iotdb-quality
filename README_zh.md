# TsClean-IoTDB
语言：[English](README.md) | 中文

## 概述
本项目基于[Apache IoTDB](https://github.com/apache/iotdb)的用户自定义函数(UDF)，实现了TsClean数据质量系统的一系列重要函数，包括时序数据的质量指标计算、数值填补、数值修复等。

## 快速开始
1. 将本项目及其依赖打包成jar包
2. 将jar包复制到IoTDB程序目录的ext\udf目录下
3. 在IoTDB中使用下面的SQL语句注册UDF

```sql
create function completeness as “cn.edu.thu.dquality.udf.UDTFCompleteness”
create function consistency as “cn.edu.thu.dquality.udf.UDTFConsistency”
create function timeliness as “cn.edu.thu.dquality.udf.UDTFTimeliness”
create function validity as “cn.edu.thu.dquality.udf.UDTFValidity”
create function previousfill as “cn.edu.thu.dquality.udf.UDTFPreviousFill”
create function linearfill as “cn.edu.thu.dquality.udf.UDTFLinearFill”
```

### 项目打包
环境准备：
+ Java >= 1.8 (目前 1.8、11和13已经被验证可用。请确保环境变量路径已正确设置)。
+ Maven >= 3.1 

具体流程：
1. 修改pom.xml文件中如下的项目依赖，使其与目标IoTDB服务器版本一致（IoTDB-0.12.0版本以上支持UDF功能）；
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
2. 在项目根目录下，使用下面的命令进行打包：
```
mvn clean package -DskipTests
```
3. 执行完成后，包含依赖的jar包位于target/udf-tsclean-0.1.0-jar-with-dependencies.jar。

## 函数介绍

### 时序数据质量指标函数
对于时序数据的质量，我们制定了完整性、一致性、时效性、有效性四个指标来进行衡量。计算各个指标的UDF如下表：

|    函数名    |          输入序列类型          |                                                       属性参数                                                       | 输出序列类型 |                                                       功能描述                                                       |
| :----------: | :----------------------------: | :------------------------------------------------------------------------------------------------------------------: | :----------: | :------------------------------------------------------------------------------------------------------------------: |
| COMPLETENESS | INT32 / INT64 / FLOAT / DOUBLE | `window`：每一个窗口包含的数据点数目，最后一个窗口的数据点数目可能会不足。缺省情况下，全部输入数据都属于同一个窗口。 |    DOUBLE    | 将输入序列划分为若干个连续且不重叠的窗口，分别计算每一个窗口的完整性，并输出窗口第一个数据点的时间戳和窗口的完整性。 |
| CONSISTENCY  | INT32 / INT64 / FLOAT / DOUBLE | `window`：每一个窗口包含的数据点数目，最后一个窗口的数据点数目可能会不足。缺省情况下，全部输入数据都属于同一个窗口。 |    DOUBLE    | 将输入序列划分为若干个连续且不重叠的窗口，分别计算每一个窗口的一致性，并输出窗口第一个数据点的时间戳和窗口的一致性。 |
|  TIMELINESS  | INT32 / INT64 / FLOAT / DOUBLE | `window`：每一个窗口包含的数据点数目，最后一个窗口的数据点数目可能会不足。缺省情况下，全部输入数据都属于同一个窗口。 |    DOUBLE    | 将输入序列划分为若干个连续且不重叠的窗口，分别计算每一个窗口的时效性，并输出窗口第一个数据点的时间戳和窗口的时效性。 |
|   VALIDITY   | INT32 / INT64 / FLOAT / DOUBLE | `window`：每一个窗口包含的数据点数目，最后一个窗口的数据点数目可能会不足。缺省情况下，全部输入数据都属于同一个窗口。 |    DOUBLE    | 将输入序列划分为若干个连续且不重叠的窗口，分别计算每一个窗口的有效性，并输出窗口第一个数据点的时间戳和窗口的有效性。 |


例如：
```sql
select s1,completeness(s1),consistency(s1),timeliness(s1),validity(s1) from root.test.d1 where time <= 2020-01-01 00:00:30
```

结果：
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

### 时序数据数值填补函数
对于时序数据，我们设计了一系列数值填补方法。各方法的UDF如下表：
|    函数名    | 输入序列类型 |                                                          属性参数                                                           |       输出序列类型       |                                         功能描述                                         |
| :----------: | :----------: | :-------------------------------------------------------------------------------------------------------------------------: | :----------------------: | :--------------------------------------------------------------------------------------: |
| PREVIOUSFILL | FLOAT/DOUBLE | `beforeRange`：只有当前一个非`NaN`数据点与当前数据点的时间差不超过该值时才会进行填补，否则仍为`NaN`。在缺省情况下为无穷大。 | 与输入序列的实际类型一致 |          将输入序列中的`NaN`填补为前一个非`NaN`数据点的值，输出填补后的新序列。          |
|  LINEARFILL  | FLOAT/DOUBLE | `beforeRange`：只有当前一个非`NaN`数据点与当前数据点的时间差不超过该值时才会进行填补，否则仍为`NaN`。在缺省情况下为无穷大。<br> `afterRange`：只有当后一个非`NaN`数据点与当前数据点的时间差不超过该值时才会进行填补，否则仍为`NaN`。在缺省情况下为无穷大。  | 与输入序列的实际类型一致 | 将输入序列中的`NaN`填补为前一个和后一个非`NaN`数据点线性插值的结果，输出填补后的新序列。 |

例如：
```sql
select s2,previousfill(s2,"beforeRange"="1000"),linearfill(s2,"beforeRange"="1000","afterRange"="1000") from root.test.d1 where time <= 2020-01-01 00:00:30
```

结果：
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


## 函数实现
### 数据质量指标
对于时序数据的质量，我们制定了如下四个指标来进行衡量，每一个指标都包含了一个或多个异常：


|    完整性    | 一致性 | 时效性 |    有效性    |
| :----------: | :----: | :----: | :----------: |
| 数据丢失异常 | 过密点 | 延迟点 |   取值范围   |
|   空值异常   |        |        | 取值变化范围 |
|  特殊值异常  |        |        |   速度范围   |
|              |        |        | 速度变化范围 |

**完整性**采用如下的公式计算：

![](http://latex.codecogs.com/svg.latex?Completeness=1-\frac{N_{null}+N_{special}+N_{miss}}{N+N_{miss}})


其中，N是时间序列总的数据点数目，N<sub>null</sub>是时间序列中值为空的数据点数目，N<sub>special</sub>是时间序列中值为特殊值的数据点数目，N<sub>miss</sub>是时间序列中丢失的数据点数目。


**一致性**采用如下的公式计算：

![](http://latex.codecogs.com/svg.latex?Consistency=1-\frac{N_{redundancy}}{N})

其中，N是时间序列总的数据点数目，N<sub>redundancy</sub>是时间序列中过密的数据点数目。

**时效性**采用如下的公式计算：

![](http://latex.codecogs.com/svg.latex?Timeliness=1-\frac{N_{late}}{N})

其中，N是时间序列总的数据点数目，N<sub>late</sub>是时间序列中延迟的数据点数目。


**有效性**采用如下的公式计算：

![](http://latex.codecogs.com/svg.latex?Validity=1-\frac{N_{value}+N_{variation}+N_{speed}+N_{speedchange}}{4N})

其中，N是时间序列总的数据点数目，N<sub>value</sub>是违反取值范围约束的数据点数目，N<sub>variation</sub>是违反取值变化约束的数据点数目，N<sub>speed</sub>是违反速度约束的数据点数目，N<sub>speedchange</sub>是违反速度变化约束的数据点数目（同一个数据点可能违反多项约束）。

基于原始数据value和它的时间戳time，可以计算它的变化variation、速度speed以及速度变化speedchange：

![](http://latex.codecogs.com/svg.latex?variation_{i}=value_{i+1}-value_{i})

![](http://latex.codecogs.com/svg.latex?speed_{i}=\frac{value_{i+1}-value_{i}}{time_{i+1}-time_{i}})

![](http://latex.codecogs.com/svg.latex?speedchange_{i}=speed_{i+1}-speed_{i})


对序列x，当x<sub>i</sub>与其中位数的偏差超过了三倍绝对中位差时，称作违背约束，即

![](http://latex.codecogs.com/svg.latex?|x_i-mid(x)|>3*mad(x))
