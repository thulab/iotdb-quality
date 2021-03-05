# Completeness

## Usage
This function is used to calculate the completeness of time series.

+ Name: COMPLETENESS
+ Type of Input Series: INT32 / INT64 / FLOAT / DOUBLE
+ Type of Output Series: DOUBLE
+ Parameters: 
  + `window`: The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window.
+ Description: The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the completeness of each window will be output.


## Examples

### Example 1

```sql
select s1,completeness(s1) from root.test.d1 where time <= 2020-01-01 00:00:30
```

Result:
```
+-----------------------------+---------------+-----------------------------+
|                         Time|root.test.d1.s1|completeness(root.test.d1.s1)|
+-----------------------------+---------------+-----------------------------+
|2020-01-01T00:00:02.000+08:00|          100.0|                        0.875|
|2020-01-01T00:00:03.000+08:00|          101.0|                         null|
|2020-01-01T00:00:04.000+08:00|          102.0|                         null|
|2020-01-01T00:00:06.000+08:00|          104.0|                         null|
|2020-01-01T00:00:08.000+08:00|          126.0|                         null|
|2020-01-01T00:00:10.000+08:00|          108.0|                         null|
|2020-01-01T00:00:14.000+08:00|          112.0|                         null|
|2020-01-01T00:00:15.000+08:00|          113.0|                         null|
|2020-01-01T00:00:16.000+08:00|          114.0|                         null|
|2020-01-01T00:00:18.000+08:00|          116.0|                         null|
|2020-01-01T00:00:20.000+08:00|          118.0|                         null|
|2020-01-01T00:00:22.000+08:00|          120.0|                         null|
|2020-01-01T00:00:26.000+08:00|          124.0|                         null|
|2020-01-01T00:00:28.000+08:00|          126.0|                         null|
|2020-01-01T00:00:30.000+08:00|            NaN|                         null|
+-----------------------------+---------------+-----------------------------+
Total line number = 15
It costs 0.500s
```
