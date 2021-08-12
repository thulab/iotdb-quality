# Comparison

## InfluxDB

[InfluxDB](https://www.influxdata.com/products/influxdb/) is a popular time series database.
InfluxQL is its query language, some of whose universal functions are related to data profiling.
The comparison is shown below. *Native* means this function has been the native function of IoTDB and *Built-in UDF* means this function has been the built-in UDF of IoTDB. 

 

| Data profiling functions of IoTDB-Quality | Univeral functions from InfluxQL |
| :---------------------------------------: | :------------------------------: |
|                 *Native*                  |             COUNT()              |
|               **Distinct**                |            DISTINCT()            |
|               **Integral**                |            INTEGRAL()            |
|                 *Native*                  |              MEAN()              |
|                **Median**                 |             MEDIAN()             |
|                 **Mode**                  |              MODE()              |
|                **Spread**                 |             SPREAD()             |
|                **Stddev**                 |             STDDEV()             |
|                 *Native*                  |              SUM()               |
|              *Built-in UDF*               |             BOTTOM()             |
|                 *Native*                  |             FIRST()              |
|                 *Native*                  |              LAST()              |
|                 *Native*                  |              MAX()               |
|                 *Native*                  |              MIN()               |
|              **Percentile**               |           PERCENTILE()           |
|                **Sample**                 |             SAMPLE()             |
|              *Built-in UDF*               |              TOP()               |
|               **Histogram**               |           HISTOGRAM()            |
|                  **Mad**                  |                                  |
|                 **Skew**                  |              SKEW()              |
|            **TimeWeightedAVG**            |        TIMEWEIGHTEDAVG()         |
|            **SelfCorrelation**            |                                  |
|           **CrossCorrelation**            |                                  |

Kapacitor offers UDF to realize user-defined anomaly detection. Python scripts can be applied to Kapacitor, and no native function for anomaly detection is offered in [InfluxDB](https://www.influxdata.com/products/influxdb/).

## MATLAB R2021a

Econometrics Toolbox offers a series of functions for time series analysis.

| Anomaly detection functions of IoTDB-Quality | Functions from Econometrics Toolbox |
| :------------------------------------------: | :---------------------------------: |
|                 **Segment**                  |      ischange(method='linear')      |
|                  **ADWIN**                   |             ischange()              |
|             **SelfCorrelation**              |             autocorr()              |
|             **CrossCorrelation**             |             crosscorr()             |
|                   **LBQ**                    |              lbqtest()              |

Signal Processing Toolbox offers a series of functions for signal analysis.

| Anomaly detection functions of IoTDB-Quality | Functions from Econometrics Toolbox |
| :------------------------------------------: | :---------------------------------: |
|                   **FFT**                    |                fft()                |
|                   **DTW**                    |                dtw()                |

## ADTK

ADTK is a python library for unsupervised or rule-based anomaly detection.

| Anomaly detection functions of IoTDB-Quality |                       Classes in ADTK                        |
| :------------------------------------------: | :----------------------------------------------------------: |
|                  **Range**                   |                    detector.ThresholdAD()                    |
|                  **KSigma**                  |               detector.GeneralizedESDTestAD()                |
|                  **ADWIN**                   |                     detector.PersistAD()                     |
|                   **LOF**                    | detector.OutlierDetector(sklearn.neighbors.LocalOutlierFactor) |

## tsfresh

Tsfresh is a python library for time series analysis.

| Anomaly detection functions of IoTDB-Qua | Classes in tsfresh |
| :--------------------------------------: | :----------------: |
|           **SelfCorrelation**            | autocorrelation()  |

