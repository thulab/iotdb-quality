# LowPass

## Usage

This function performs low-pass filtering on the input series and extracts components below the cutoff frequency. 
The timestamps of input will be ignored and all data points will be regarded as equidistant.

**Name:** LOWPASS

**Input:** Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:**

+ `wpass`: The normalized cutoff frequency which values (0,1). This parameter cannot be lacked.

**Output:** Output a single series. The type is DOUBLE. It is the input after filtering. The length and timestamps of output are the same as the input.

**Note:** `NaN` in the input series will be ignored. 

## Examples

Input series:

```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|1970-01-01T08:00:00.000+08:00|       2.902113|
|1970-01-01T08:00:01.000+08:00|      1.1755705|
|1970-01-01T08:00:02.000+08:00|     -2.1755705|
|1970-01-01T08:00:03.000+08:00|     -1.9021131|
|1970-01-01T08:00:04.000+08:00|            1.0|
|1970-01-01T08:00:05.000+08:00|      1.9021131|
|1970-01-01T08:00:06.000+08:00|      0.1755705|
|1970-01-01T08:00:07.000+08:00|     -1.1755705|
|1970-01-01T08:00:08.000+08:00|      -0.902113|
|1970-01-01T08:00:09.000+08:00|            0.0|
|1970-01-01T08:00:10.000+08:00|       0.902113|
|1970-01-01T08:00:11.000+08:00|      1.1755705|
|1970-01-01T08:00:12.000+08:00|     -0.1755705|
|1970-01-01T08:00:13.000+08:00|     -1.9021131|
|1970-01-01T08:00:14.000+08:00|           -1.0|
|1970-01-01T08:00:15.000+08:00|      1.9021131|
|1970-01-01T08:00:16.000+08:00|      2.1755705|
|1970-01-01T08:00:17.000+08:00|     -1.1755705|
|1970-01-01T08:00:18.000+08:00|      -2.902113|
|1970-01-01T08:00:19.000+08:00|            0.0|
+-----------------------------+---------------+
```

SQL for query:

```sql
select lowpass(s1,'wpass'='0.45') from root.test.d1
```

Output series:

```
+-----------------------------+----------------------------------------+
|                         Time|lowpass(root.test.d1.s1, "wpass"="0.45")|
+-----------------------------+----------------------------------------+
|1970-01-01T08:00:00.000+08:00|                      1.9021130073323922|
|1970-01-01T08:00:01.000+08:00|                      1.1755704705132448|
|1970-01-01T08:00:02.000+08:00|                     -1.1755705286582614|
|1970-01-01T08:00:03.000+08:00|                     -1.9021130389094498|
|1970-01-01T08:00:04.000+08:00|                    7.450580419288145E-9|
|1970-01-01T08:00:05.000+08:00|                       1.902113046743454|
|1970-01-01T08:00:06.000+08:00|                      1.1755705212076808|
|1970-01-01T08:00:07.000+08:00|                     -1.1755704886020932|
|1970-01-01T08:00:08.000+08:00|                     -1.9021130222335536|
|1970-01-01T08:00:09.000+08:00|                   3.552713678800501E-16|
|1970-01-01T08:00:10.000+08:00|                      1.9021130222335536|
|1970-01-01T08:00:11.000+08:00|                      1.1755704886020932|
|1970-01-01T08:00:12.000+08:00|                     -1.1755705212076801|
|1970-01-01T08:00:13.000+08:00|                      -1.902113046743454|
|1970-01-01T08:00:14.000+08:00|                    -7.45058112983088E-9|
|1970-01-01T08:00:15.000+08:00|                      1.9021130389094498|
|1970-01-01T08:00:16.000+08:00|                      1.1755705286582616|
|1970-01-01T08:00:17.000+08:00|                     -1.1755704705132448|
|1970-01-01T08:00:18.000+08:00|                     -1.9021130073323924|
|1970-01-01T08:00:19.000+08:00|                  -2.664535259100376E-16|
+-----------------------------+----------------------------------------+
```

Note: The input is $y=sin(2\pi t/4)+2sin(2\pi t/5)$ with a length of 20. Thus, the output is $y=2sin(2\pi t/5)$ after low-pass filtering.

