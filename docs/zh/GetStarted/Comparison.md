# 系统对标

## InfluxDB
[InfluxDB](https://www.influxdata.com/products/influxdb/)是一个流行的时序数据库。InfluxQL是它的查询语言，其部分通用函数与数据画像相关。这些函数与IoTDB-Quality数据画像函数的对比如下（*Native*指该函数已经作为IoTDB的Native函数实现，*Built-in UDF*指该函数已经作为IoTDB的内建UDF函数实现）：       


| IoTDB-Quality的数据画像函数 | InfluxQL的通用函数 |
| :-------------------------: | :----------------: |
|          *Native*           |      COUNT()       |
|        **Distinct**         |     DISTINCT()     |
|        **Integral**         |     INTEGRAL()     |
|          **Mean**           |       MEAN()       |
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
|           **Cov**           |                    |
|        **Histogram**        |                    |
|         **Pearson**         |                    |
|          **Skew**           |                    |