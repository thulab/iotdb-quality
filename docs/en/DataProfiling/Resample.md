# Resample
## Usage
This function is used to resample the input series according to a given frequency,
including up-sampling and down-sampling.
Currently, the supported up-sampling methods are 
NaN (filling with `NaN`), 
FFill (filling with previous value), 
BFill (filling with next value) and 
Linear (filling with linear interpolation).
Down-sampling relies on group aggregation, 
which supports Max, Min, First, Last, Mean and Median.


**Name:** RESAMPLE

**Input Series:** Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:**


+ `every`: The frequency of resampling, which is a positive number with an unit. The unit is 'ms' for millisecond, 's' for second, 'm' for minute, 'h' for hour and 'd' for day. This parameter cannot be lacked.
+ `interp`: The interpolation method of up-sampling, which is 'NaN', 'FFill', 'BFill' or 'Linear'. By default, NaN is used.
+ `aggr`: The aggregation method of down-sampling, which is 'Max', 'Min', 'First', 'Last', 'Mean' or 'Median'. By default, Mean is used. 

**Output Series:** Output a single series. The type is DOUBLE. It is strictly equispaced with the frequency `every`.

**Note:** `NaN` in the input series will be ignored.

## Examples

### Up-sampling
When the frequency of resampling is higher than the original frequency, up-sampling starts.

Input series:

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


SQL for query:

```sql
select resample(s1,'every'='5m','interp'='linear') from root.test.d1
```

Output series: 

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

### Down-sampling

When the frequency of resampling is lower than the original frequency, down-sampling starts.

Input series is the same as above, the SQL for query is shown below:

```sql
select resample(s1,'every'='30m','aggr'='first') from root.test.d1
```

Output series:

```
+-----------------------------+--------------------------------------------------------+
|                         Time|resample(root.test.d1.s1, "every"="30m", "aggr"="first")|
+-----------------------------+--------------------------------------------------------+
|2021-03-06T16:00:00.000+08:00|                                      3.0899999141693115|
|2021-03-06T16:30:00.000+08:00|                                                     3.5|
|2021-03-06T17:00:00.000+08:00|                                      3.4100000858306885|
+-----------------------------+--------------------------------------------------------+
```