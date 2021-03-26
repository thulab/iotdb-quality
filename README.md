# Introduction

## What is IoTDB-Quality
[Apache IoTDB](https://github.com/apache/iotdb) (Internet of Things Database) is a data management system for time series data, which can provide users specific services, such as, data collection, storage and analysis. 

For applications based on time series data, data quality is vital. 
**IoTDB-Quality** is IoTDB User Defined Functions (UDF) about data quality, including data profiling, data quality evalution and data repairing. 
It effectively meets the demand for data quality in the industrial field.

## Quick Start
1. Download the JAR with all dependencies and the script of registering UDF.
2. Copy the JAR package to `ext\udf` under the directory of IoTDB system.
3. Run `sbin\start-server.bat` (for Windows) or `sbin\start-server.sh` (for Linux or MacOS) to start IoTDB server.
4. Copy the script to the directory of IoTDB system and run it to register UDF.



