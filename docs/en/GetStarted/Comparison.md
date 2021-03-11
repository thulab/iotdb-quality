# Comparison

## InfluxDB

[InfluxDB](https://www.influxdata.com/products/influxdb/) is a popular time series database.
InfluxQL is its query language, some of whose universal functions are related to data profiling.
The comparison is shown below. *Native* means this function has been the native function of IoTDB and *Built-in UDF* means this function has been the built-in UDF of IoTDB. 

 

| Data profiling functions of IoTDB-Quality | Univeral functions of InfluxQL |
| :---------------------------------------: | :----------------------------: |
|                 *Native*                  |            COUNT()             |
|               **Distinct**                |           DISTINCT()           |
|               **Integral**                |           INTEGRAL()           |
|                 **Mean**                  |             MEAN()             |
|                **Median**                 |            MEDIAN()            |
|                 **Mode**                  |             MODE()             |
|                **Spread**                 |            SPREAD()            |
|                **Stddev**                 |            STDDEV()            |
|                 *Native*                  |             SUM()              |
|              *Built-in UDF*               |            BOTTOM()            |
|                 *Native*                  |            FIRST()             |
|                 *Native*                  |             LAST()             |
|                 *Native*                  |             MAX()              |
|                 *Native*                  |             MIN()              |
|              **Percentile**               |          PERCENTILE()          |
|                **Sample**                 |            SAMPLE()            |
|              *Built-in UDF*               |             TOP()              |
|                  **Cov**                  |                                |
|               **Histogram**               |                                |
|                **Pearson**                |                                |
|                 **Skew**                  |                                |