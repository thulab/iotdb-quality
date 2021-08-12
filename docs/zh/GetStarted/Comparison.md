# 系统对标

## InfluxDB v2.0
[InfluxDB](https://www.influxdata.com/products/influxdb/)是一个流行的时序数据库。InfluxQL是它的查询语言，其部分通用函数与数据画像相关。这些函数与IoTDB-Quality数据画像函数的对比如下（*Native*指该函数已经作为IoTDB的Native函数实现，*Built-in UDF*指该函数已经作为IoTDB的内建UDF函数实现）：       


| IoTDB-Quality的数据画像函数 | InfluxQL的通用函数 |
| :-------------------------: | :----------------: |
|          *Native*           |      COUNT()       |
|        **Distinct**         |     DISTINCT()     |
|        **Integral**         |     INTEGRAL()     |
|          *Native*           |       MEAN()       |
|         **Median**          |      MEDIAN()      |
|          **Mode**           |       MODE()       |
|         **Spread**          |      SPREAD()      |
|         **Stddev**          |      STDDEV()      |
|          *Native*           |       SUM()        |
|       *Built-in UDF*        |      BOTTOM()      |
|          *Native*           |      FIRST()       |
|          *Native*           |       LAST()       |
|          *Native*           |       MAX()        |
|          *Native*           |       MIN()        |
|       **Percentile**        |    PERCENTILE()    |
|         **Sample**          |      SAMPLE()      |
|       *Built-in UDF*        |       TOP()        |
|        **Histogram**        |    HISTOGRAM()     |
|           **Mad**           |                    |
|          **Skew**           |       SKEW()       |
|     **TimeWeightedAVG**     | TIMEWEIGHTEDAVG()  |
|     **SelfCorrelation**     |                    |
|    **CrossCorrelation**     |                    |

[InfluxDB](https://www.influxdata.com/products/influxdb/)可使用Kapacitor提供的UDF功能实现自定义异常检测。由于Kapacitor可以使用python脚本，因此缺乏可用于异常检测的原生函数。

## MATLAB R2021a

MATLAB中的计量经济学工具箱(Econometrics Toolbox)提供一系列时间序列分析工具。

| IoTDB-Quality的异常检测函数 | Econometrics Toolbox中的函数 |
| :-------------------------: | :--------------------------: |
|         **Segment**         |  ischange(method='linear')   |
|          **ADWIN**          |          ischange()          |
|     **SelfCorrelation**     |          autocorr()          |
|    **CrossCorrelation**     |         crosscorr()          |
|           **LBQ**           |          lbqtest()           |

MATLAB中的信号处理工具箱(Signal Processing Toolbox)提供一系列信号分析工具。

| IoTDB-Quality的异常检测函数 | Econometrics Toolbox中的函数 |
| :-------------------------: | :--------------------------: |
|           **FFT**           |            fft()             |
|           **DTW**           |            dtw()             |

## ADTK

ADTK是一个用于无监督或基于规则的异常检测python库。

| IoTDB-Quality的异常检测函数 |                          ADTK中的类                          |
| :-------------------------: | :----------------------------------------------------------: |
|          **Range**          |                    detector.ThresholdAD()                    |
|         **KSigma**          |               detector.GeneralizedESDTestAD()                |
|          **ADWIN**          |                     detector.PersistAD()                     |
|           **LOF**           | detector.OutlierDetector(sklearn.neighbors.LocalOutlierFactor) |

## tsfresh

tsfresh是一个用于时间序列分析的python库。

| IoTDB-Quality的异常检测函数 |   tsfresh中的类   |
| :-------------------------: | :---------------: |
|     **SelfCorrelation**     | autocorrelation() |

