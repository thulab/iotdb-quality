# 概述

## 什么是IoTDB-Quality
[Apache IoTDB](https://github.com/apache/iotdb) (Internet of Things Database) 是一个时序数据的数据管理系统，可以为用户提供数据收集、存储和分析等特定的服务。

对基于时序数据的应用而言，数据质量至关重要。**IoTDB-Quality**基于用户自定义函数(UDF)，实现了一系列关于数据质量的函数，包括数据画像、数据质量评估与修复等，有效满足了工业领域对数据质量的需求。

## 快速开始
1. 下载包含全部依赖的jar包
2. 将jar包复制到IoTDB程序目录的`ext\udf`目录下
3. 在IoTDB中使用下面的SQL语句注册UDF

```sql
create function completeness as 'cn.edu.thu.dquality.udf.UDTFCompleteness'
create function consistency as 'cn.edu.thu.dquality.udf.UDTFConsistency'
create function timeliness as 'cn.edu.thu.dquality.udf.UDTFTimeliness'
create function validity as 'cn.edu.thu.dquality.udf.UDTFValidity'
```
