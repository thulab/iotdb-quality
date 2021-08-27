# Wavelet

## 函数简介

本函数对输入序列进行小波变换。

**函数名：** WAVELET

**输入序列：** 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE

**参数：**

+ `method`：小波滤波的类型，提供'Haar', 'DB4', 'DB6', 'DB8'，其中DB指代Daubechies。若不设置该参数，则用户需提供小波滤波的系数。不区分大小写。
+ `coef`：小波滤波的系数。若提供该参数，请使用英文逗号','分割各项，不添加空格或其它符号。

**输出序列：** 输出单个序列，类型为DOUBLE，长度与输入相等。

**提示：** 输入序列长度必须为2的整数次幂。

## 使用示例

### Haar变换


输入序列：

```
+-----------------------------+------------+
|                         Time|root.test.s1|
+-----------------------------+------------+
|1970-01-01T08:00:00.000+08:00|         0.0|
|1970-01-01T08:00:00.100+08:00|         0.2|
|1970-01-01T08:00:00.200+08:00|         1.5|
|1970-01-01T08:00:00.300+08:00|         1.2|
|1970-01-01T08:00:00.400+08:00|         0.6|
|1970-01-01T08:00:00.500+08:00|         1.7|
|1970-01-01T08:00:00.600+08:00|         0.8|
|1970-01-01T08:00:00.700+08:00|         2.0|
|1970-01-01T08:00:00.800+08:00|         2.5|
|1970-01-01T08:00:00.900+08:00|         2.1|
|1970-01-01T08:00:01.000+08:00|         0.0|
|1970-01-01T08:00:01.100+08:00|         2.0|
|1970-01-01T08:00:01.200+08:00|         1.8|
|1970-01-01T08:00:01.300+08:00|         1.2|
|1970-01-01T08:00:01.400+08:00|         1.0|
|1970-01-01T08:00:01.500+08:00|         1.6|
+-----------------------------+------------+
```

用于查询的SQL语句：

```sql
select wavelet(s1,"method"="haar") from root.test.d1
```

输出序列：

```
+-----------------------------+--------------------------------------+
|                         Time|wavelet(root.test.s1, "method"="haar")|
+-----------------------------+--------------------------------------+
|1970-01-01T08:00:00.000+08:00|                    5.0500000156462175|
|1970-01-01T08:00:00.100+08:00|                   -1.2499999366700643|
|1970-01-01T08:00:00.200+08:00|                   0.21213200063848525|
|1970-01-01T08:00:00.300+08:00|                    -0.353553427471766|
|1970-01-01T08:00:00.400+08:00|                   -0.3499999940395355|
|1970-01-01T08:00:00.500+08:00|                    1.2000000476837156|
|1970-01-01T08:00:00.600+08:00|                    0.7999999523162842|
|1970-01-01T08:00:00.700+08:00|                 -0.049999989569187164|
|1970-01-01T08:00:00.800+08:00|                   -0.9192388134351692|
|1970-01-01T08:00:00.900+08:00|                   0.42426408557066786|
|1970-01-01T08:00:01.000+08:00|                    0.6363961283560018|
|1970-01-01T08:00:01.100+08:00|                  -0.35355339059327373|
|1970-01-01T08:00:01.200+08:00|                    1.4849241730567921|
|1970-01-01T08:00:01.300+08:00|                   0.14142138995478826|
|1970-01-01T08:00:01.400+08:00|                   0.14142138995478826|
|1970-01-01T08:00:01.500+08:00|                    1.1313708667572153|
+-----------------------------+--------------------------------------+
```
