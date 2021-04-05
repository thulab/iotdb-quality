# Integral

## Usage

This function is used to calculate the integration of time series, 
which equals to the area under the curve with time as X-axis and values as Y-axis.

**Name:** INTEGRAL

**Input Series:** Only support a single input numeric series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:**

+ `unit`: The unit of time used when computing the integral. 
  The value should be chosen from "1S", "1s", "1m", "1H", "1d"(case-sensitive), 
  and each represents taking one millisecond / second / minute / hour / day as 1.0 while calculating the area and integral.

**Output Series:** Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the integration.

**Note:**

+ The integral value equals to the sum of the areas of right-angled trapezoids consisting of each two adjacent points and the time-axis. 
Choosing different `unit` implies different scaling of time axis, thus making it apparent to convert the value among those results with constant coefficient.
  
+ The calculation ignores missing points and null values. The curve or trapezoids will skip these points and use the next valid point.

## Examples

Input series:
```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|2020-01-01T00:00:01.000+08:00|              1|
|2020-01-01T00:00:02.000+08:00|              2|
|2020-01-01T00:00:03.000+08:00|              3|
|2020-01-01T00:00:03.000+08:00|              4|
|2020-01-01T00:00:03.000+08:00|              5|
|2020-01-01T00:00:04.000+08:00|              6|
|2020-01-01T00:00:05.000+08:00|              7|
|2020-01-01T00:00:08.000+08:00|              8|
|2020-01-01T00:00:09.000+08:00|            NaN|
|2020-01-01T00:00:10.000+08:00|             10|
+-----------------------------+---------------+
```

### Default Parameters

With default parameters, this function will take one second as 1.0. SQL for query:

```sql
select integral(s1) from root.test.d1 where time <= 2020-01-01 00:00:10
```

Output series:
```
+-----------------------------+-------------------------+
|                         Time|integral(root.test.d1.s1)|
+-----------------------------+-------------------------+
|1970-01-01T08:00:00.000+08:00|                     56.5|
+-----------------------------+-------------------------+
```

Calculation expression: (1+2)/2.0 * 1 + (2+3)/2.0 * 1 + (5+6)/2.0 * 1 + (6+7)/2.0 * 1 + (7+8)/2.0 * 3 + (8+10)/2.0 * 2 = 56.5

### Specific time unit

With time unit specified as "1m", this function will take one minute as 1.0. SQL for query:

```sql
select integral(s1, "unit"="1m") from root.test.d1 where time <= 2020-01-01 00:00:10
```

Output series:
```
+-----------------------------+-------------------------+
|                         Time|integral(root.test.d1.s1)|
+-----------------------------+-------------------------+
|1970-01-01T08:00:00.000+08:00|                    0.942|
+-----------------------------+-------------------------+
```

Calculation expression: (1+2)/2.0*(1.0/60) + (2+3)/2.0*(1.0/60) + (5+6)/2.0*(1.0/60) + (6+7)/2.0*(1.0/60) + (7+8)/2.0*(3.0/60) + (8+10)/2.0*(2.0/60) = 0.941667

