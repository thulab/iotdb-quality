# 系统对标

## InfluxDB

| IoTDB-Quality的数据画像函数 | InfluxQL的通用函数 |
| :-------------------------: | :----------------: |
|           Native            |      COUNT()       |
|        **Distinct**         |     DISTINCT()     |
|        **Integral**         |     INTEGRAL()     |
|          **Mean**           |       MEAN()       |
|         **Median**          |      MEDIAN()      |
|          **Mode**           |       MODE()       |
|         **Spread**          |      SPREAD()      |
|         **Stddev**          |      STDDEV()      |
|           Native            |       SUM()        |
|        Built-in UDF         |      BOTTOM()      |
|           Native            |      FIRST()       |
|           Native            |       LAST()       |
|           Native            |       MAX()        |
|           Native            |       MIN()        |
|       **Percentile**        |    PERCENTILE()    |
|         **Sample**          |      SAMPLE()      |
|        Built-in UDF         |       TOP()        |
|           **Cov**           |                    |
|        **Histogram**        |                    |
|         **Pearson**         |                    |
|          **Skew**           |                    |