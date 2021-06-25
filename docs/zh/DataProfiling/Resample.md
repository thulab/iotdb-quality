# Resample
## 函数简介
本函数对输入序列按照指定的频率进行重采样，包括上采样和下采样。目前，本函数支持的上采样方法包括`NaN`填充法(NaN)、前值填充法(FFill)、后值填充法(BFill)以及线性插值法(Linear)；本函数支持的下采样方法为分组聚合，聚合方法包括最大值(Max)、最小值(Min)、首值(First)、末值(Last)、平均值(Mean)和中位数(Median)。

**函数名：** RESAMPLE

**输入序列：** 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。

**参数：**

+ `every`：重采样频率，是一个有单位的正数。目前支持五种单位，分别是'ms'（毫秒）、's'（秒）、'm'（分钟）、'h'（小时）和'd'（天）。该参数不允许缺省。
+ `interp`：上采样的插值方法，取值为'NaN'、'FFill'、'BFill'或'Linear'。在缺省情况下，使用`NaN`填充法。 
+ `aggr`：下采样的聚合方法，取值为'Max'、'Min'、'First'、'Last'、'Mean'或'Median'。在缺省情况下，使用平均数聚合。

**输出序列：** 输出单个序列，类型为DOUBLE。该序列按照重采样频率严格等间隔分布。

**提示：** 数据中的`NaN`将会被忽略。

## 使用示例

### 上采样

当重采样频率高于数据原始频率时，将会进行上采样。

输入序列：

```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|2021-03-06T16:00:00.000+08:00|           3.09|
|2021-03-06T16:15:00.000+08:00|           3.53|
|2021-03-06T16:30:00.000+08:00|            3.5|
|2021-03-06T16:45:00.000+08:00|           3.51|
|2021-03-06T17:00:00.000+08:00|           3.41|
+-----------------------------+---------------+
```


用于查询的SQL语句：

```sql
select resample(s1,'every'='5m','interp'='linear') from root.test.d1
```

输出序列：

```
+-----------------------------+----------------------------------------------------------+
|                         Time|resample(root.test.d1.s1, "every"="5m", "interp"="linear")|
+-----------------------------+----------------------------------------------------------+
|2021-03-06T16:00:00.000+08:00|                                        3.0899999141693115|
|2021-03-06T16:05:00.000+08:00|                                        3.2366665999094644|
|2021-03-06T16:10:00.000+08:00|                                        3.3833332856496177|
|2021-03-06T16:15:00.000+08:00|                                        3.5299999713897705|
|2021-03-06T16:20:00.000+08:00|                                        3.5199999809265137|
|2021-03-06T16:25:00.000+08:00|                                         3.509999990463257|
|2021-03-06T16:30:00.000+08:00|                                                       3.5|
|2021-03-06T16:35:00.000+08:00|                                         3.503333330154419|
|2021-03-06T16:40:00.000+08:00|                                         3.506666660308838|
|2021-03-06T16:45:00.000+08:00|                                         3.509999990463257|
|2021-03-06T16:50:00.000+08:00|                                        3.4766666889190674|
|2021-03-06T16:55:00.000+08:00|                                         3.443333387374878|
|2021-03-06T17:00:00.000+08:00|                                        3.4100000858306885|
+-----------------------------+----------------------------------------------------------+
```

### 下采样

当重采样频率低于数据原始频率时，将会进行下采样。

输入序列同上，用于查询的SQL语句如下：

```sql
select resample(s1,'every'='30m','aggr'='first') from root.test.d1
```

输出序列：

```
+-----------------------------+--------------------------------------------------------+
|                         Time|resample(root.test.d1.s1, "every"="30m", "aggr"="first")|
+-----------------------------+--------------------------------------------------------+
|2021-03-06T16:00:00.000+08:00|                                      3.0899999141693115|
|2021-03-06T16:30:00.000+08:00|                                                     3.5|
|2021-03-06T17:00:00.000+08:00|                                      3.4100000858306885|
+-----------------------------+--------------------------------------------------------+
```