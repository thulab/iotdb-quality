# Introduction

## What is IoTDB-Quality
[Apache IoTDB](https://github.com/apache/iotdb) (Internet of Things Database) is a data management system for time series data, which can provide users specific services, such as, data collection, storage and analysis. 

For applications based on time series data, data quality is vital. 
**IoTDB-Quality** is IoTDB User Defined Functions (UDF) about data quality, including data profiling, data quality evalution and data repairing. 
It effectively meets the demand for data quality in the industrial field.

## Quick Start
1. Download the JAR with all dependencies.
2. Copy the JAR package to `ext\udf` under the directory of IoTDB server.
3. Register the UDFs with the following SQL statements in IoTDB: 

```sql
create function completeness as 'cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness'
create function consistency as 'cn.edu.thu.iotdb.quality.dquality.UDTFConsistency'
create function timeliness as 'cn.edu.thu.iotdb.quality.dquality.UDTFTimeliness'
create function validity as 'cn.edu.thu.iotdb.quality.dquality.UDTFValidity'
create function percentile as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile'
create function distinct as 'cn.edu.thu.iotdb.quality.dprofile.UDTFDistinct'
create function mode as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMode'
create function spread as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSpread'
```

