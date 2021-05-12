# !/bin/bash
# This file is used to create UDFs of IoTDB-Quality automatically.
# Created by Data Quality Group, School of Software, Tsinghua University
# 

# Parameters
host=127.0.0.1
rpcPort=6667
user=root
pass=root

# Data Profiling
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function distinct as 'cn.edu.thu.iotdb.quality.dprofile.UDTFDistinct'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function histogram as 'cn.edu.thu.iotdb.quality.dprofile.UDTFHistogram'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function integral as 'cn.edu.thu.iotdb.quality.dprofile.UDAFIntegral'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function mad as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMad'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function median as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMedian'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function mode as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMode'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function percentile as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function sample as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSample'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function skew as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSkew'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function spread as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSpread'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function stddev as 'cn.edu.thu.iotdb.quality.dprofile.UDAFStddev'"


# Data Quality
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function completeness as 'cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function consistency as 'cn.edu.thu.iotdb.quality.dquality.UDTFConsistency'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function timeliness as 'cn.edu.thu.iotdb.quality.dquality.UDTFTimeliness'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function validity as 'cn.edu.thu.iotdb.quality.dquality.UDTFValidity'"


