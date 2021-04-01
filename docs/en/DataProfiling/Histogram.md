# Histogram

## Usage

This function is used to calculate the distribution histogram of a single column of numerical data.

**Name:** HISTOGRAM

**Input Series:** Only supports a single input sequence, the type is INT32 / INT64 / FLOAT / DOUBLE

**Parameters:**

+ `start`: The lower limit of the requested data range, the default value is -0x1.fffffffffffffP+1023.
+ `end`: The upper limit of the requested data range, the default value is 0x1.fffffffffffffP+1023, and the value of start must be less than or equal to end.
+ `count`: The number of buckets of the histogram, the default value is 10, and its value must be a positive integer.

**Output Series:** The value of the bucket of the histogram, where the data range represented by the i-th bucket is [start+(i-1)*((end-start)/count), start+i*((end-start)/count))

## Examples

Input series: 

```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|2020-01-01T00:00:00.000+08:00|            1.0|
|2020-01-01T00:00:01.000+08:00|            2.0|
|2020-01-01T00:00:02.000+08:00|            3.0|
|2020-01-01T00:00:03.000+08:00|            4.0|
|2020-01-01T00:00:04.000+08:00|            5.0|
|2020-01-01T00:00:05.000+08:00|            6.0|
|2020-01-01T00:00:06.000+08:00|            7.0|
|2020-01-01T00:00:07.000+08:00|            8.0|
|2020-01-01T00:00:08.000+08:00|            9.0|
|2020-01-01T00:00:09.000+08:00|           10.0|
|2020-01-01T00:00:10.000+08:00|           11.0|
|2020-01-01T00:00:11.000+08:00|           12.0|
|2020-01-01T00:00:12.000+08:00|           13.0|
|2020-01-01T00:00:13.000+08:00|           14.0|
|2020-01-01T00:00:14.000+08:00|           15.0|
|2020-01-01T00:00:15.000+08:00|           16.0|
|2020-01-01T00:00:16.000+08:00|           17.0|
|2020-01-01T00:00:17.000+08:00|           18.0|
|2020-01-01T00:00:18.000+08:00|           19.0|
|2020-01-01T00:00:19.000+08:00|           20.0|
+-----------------------------+---------------+
```

SQL for query: 

```sql
select histogram(s1,"start"="1","end"="20","count"="10") from root.test.d1
```

Output series:

```
+-----------------------------+-----------------------------------------------------------------+
|                         Time|histogram(root.test.d1.s1, "start"="1", "end"="20", "count"="10")|
+-----------------------------+-----------------------------------------------------------------+
|1970-01-01T08:00:00.000+08:00|                                                                2|
|1970-01-01T08:00:00.001+08:00|                                                                2|
|1970-01-01T08:00:00.002+08:00|                                                                2|
|1970-01-01T08:00:00.003+08:00|                                                                2|
|1970-01-01T08:00:00.004+08:00|                                                                2|
|1970-01-01T08:00:00.005+08:00|                                                                2|
|1970-01-01T08:00:00.006+08:00|                                                                2|
|1970-01-01T08:00:00.007+08:00|                                                                2|
|1970-01-01T08:00:00.008+08:00|                                                                2|
|1970-01-01T08:00:00.009+08:00|                                                                2|
+-----------------------------+-----------------------------------------------------------------+
```