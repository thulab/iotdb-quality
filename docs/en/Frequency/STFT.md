# STFT

## Usage


This function makes a short-time Fourier Transform (STFT) for input series. The input series is divided into several fix-width windows and performed Fourier transform. The amplitude of each frequency component is calculated.

**Name:** STFT

**Input Series:** Only support a single input series whose type is INT32 / INT64 / FLOAT / DOUBLE

**Parameters:**

+ `nfft`: The window size of STFT. The size of the last window may be smaller than it. By default, all values are regarded as a window.
+ `beta`: Quantization level. $x$ is quantized to $round(x \cdot 2^{-\beta}) \cdot 2^\beta$. Its default value is 0. It only works when `T_SNR` is not given.
+ `T_SNR`: Target SNR. $\beta$ can be automatically determined with it.


**Output Series:** Ouput a single series whose type is DOUBLE. The timestamps are the same with the input. 


## Example

Input series:

```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|1970-01-01T08:00:00.000+08:00|            0.0|
|1970-01-01T08:00:01.000+08:00|            1.0|
|1970-01-01T08:00:02.000+08:00|            0.0|
|1970-01-01T08:00:03.000+08:00|           -1.0|
|1970-01-01T08:00:04.000+08:00|            0.0|
|1970-01-01T08:00:05.000+08:00|           -2.0|
|1970-01-01T08:00:06.000+08:00|            0.0|
|1970-01-01T08:00:07.000+08:00|            2.0|
+-----------------------------+---------------+
```

SQL for query:

```sql
select stft(s1,'nfft'='4') from root.test.d1
```

Output series:

```
+-----------------------------+---------------------------------+
|                         Time|stft(root.test.d1.s1, "nfft"="4")|
+-----------------------------+---------------------------------+
|1970-01-01T08:00:00.000+08:00|                              0.0|
|1970-01-01T08:00:01.000+08:00|                              1.0|
|1970-01-01T08:00:02.000+08:00|                              0.0|
|1970-01-01T08:00:03.000+08:00|                              0.0|
|1970-01-01T08:00:04.000+08:00|                              0.0|
|1970-01-01T08:00:05.000+08:00|                              2.0|
|1970-01-01T08:00:06.000+08:00|                              0.0|
|1970-01-01T08:00:07.000+08:00|                              0.0|
+-----------------------------+---------------------------------+
```

