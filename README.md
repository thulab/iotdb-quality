# Introduction

## Notice for Website Migration
Iotdb quality has been transferred to the Apache iotdb official website. If you would like to obtain the latest content of iotdb UDF, please directly click on the following page link. Thank you!
[IoTDB-UDF Page Link](https://iotdb.apache.org/zh/UserGuide/Master/UDF-Library/Quick-Start.html)

## What is IoTDB-Quality
[Apache IoTDB](https://github.com/apache/iotdb) (Internet of Things Database) is a data management system for time series data, which can provide users specific services, such as, data collection, storage and analysis.

For applications based on time series data, data quality is vital.
**IoTDB-Quality** is IoTDB User Defined Functions (UDF) about data quality, including data profiling, data quality evalution and data repairing.
It effectively meets the demand for data quality in the industrial field.

## Quick Start
1. Download the JAR with all dependencies and the script of registering UDF.
2. Copy the JAR package to `ext\udf` under the directory of IoTDB system.
3. Run `sbin\start-server.bat` (for Windows) or `sbin\start-server.sh` (for Linux or MacOS) to start IoTDB server.
4. Copy the script to the directory of IoTDB system (under the root directory, at the same level as `sbin`), modify the parameters in the script if needed and run it to register UDF.

## Contact

+ Email: iotdb-quality@protonmail.com
