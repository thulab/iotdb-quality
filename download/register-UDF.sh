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
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function timeweightedavg as 'cn.edu.thu.iotdb.quality.dprofile.UDAFTimeWeightedAvg'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function mad as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMad'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function median as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMedian'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function mode as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMode'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function percentile as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function period as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPeriod'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function qlb as 'cn.edu.thu.iotdb.quality.dprofile.UDTFQLB'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function re_sample as 'cn.edu.thu.iotdb.quality.dprofile.UDTFResample'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function sample as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSample'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function segment as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSegment'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function skew as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSkew'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function spread as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSpread'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function stddev as 'cn.edu.thu.iotdb.quality.dprofile.UDAFStddev'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function minmax as 'cn.edu.thu.iotdb.quality.dprofile.UDTFMinMax'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function zscore as 'cn.edu.thu.iotdb.quality.dprofile.UDTFZScore'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function spline as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSpline'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function movingaverage as 'cn.edu.thu.iotdb.quality.dprofile.UDTFMovingAverage'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function pacf as 'cn.edu.thu.iotdb.quality.dprofile.UDTFPartialAutoCorrelation'"


# Data Quality
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function completeness as 'cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function consistency as 'cn.edu.thu.iotdb.quality.dquality.UDTFConsistency'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function timeliness as 'cn.edu.thu.iotdb.quality.dquality.UDTFTimeliness'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function validity as 'cn.edu.thu.iotdb.quality.dquality.UDTFValidity'"


# Data Repairing
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function timestamprepair as 'cn.edu.thu.iotdb.quality.drepair.UDTFTimestampRepair'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function valuerepair as 'cn.edu.thu.iotdb.quality.drepair.UDTFValueRepair'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function valuefill as 'cn.edu.thu.iotdb.quality.drepair.UDTFValueFill'"


# Data Matching
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function cov as 'cn.edu.thu.iotdb.quality.dmatch.UDAFCov'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function crosscorrelation as 'cn.edu.thu.iotdb.quality.dmatch.UDTFCrossCorrelation'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function dtw as 'cn.edu.thu.iotdb.quality.dmatch.UDAFDtw'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function patternsymmetric as 'cn.edu.thu.iotdb.quality.dmatch.UDTFPatternSymmetric'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function pearson as 'cn.edu.thu.iotdb.quality.dmatch.UDAFPearson'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function selfcorrelation as 'cn.edu.thu.iotdb.quality.dmatch.UDTFSelfCorrelation'"


# Anomaly Detection
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function ADWIN as 'cn.edu.thu.iotdb.quality.anomaly.UDTFADWIN'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function ksigma as 'cn.edu.thu.iotdb.quality.anomaly.UDTFKSigma'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function LOF as 'cn.edu.thu.iotdb.quality.anomaly.UDTFLOF'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function range as 'cn.edu.thu.iotdb.quality.anomaly.UDTFRange'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function twosidedfilter as 'cn.edu.thu.iotdb.quality.anomaly.UDTFTwoSidedFilter'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function iqr as 'cn.edu.thu.iotdb.quality.anomaly.UDTFIQR'"

# Frequency Domain
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function FFT as 'cn.edu.thu.iotdb.quality.frequency.UDTFFFT'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function conv as 'cn.edu.thu.iotdb.quality.frequency.UDTFConv'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function deconv as 'cn.edu.thu.iotdb.quality.frequency.UDTFDeconv'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function lowpass as 'cn.edu.thu.iotdb.quality.frequency.UDTFLowPass'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function highpass as 'cn.edu.thu.iotdb.quality.frequency.UDTFHighPass'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function dwt as 'cn.edu.thu.iotdb.quality.frequency.UDTFDWT'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function idwt as 'cn.edu.thu.iotdb.quality.frequency.UDTFIDWT'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function ifft as 'cn.edu.thu.iotdb.quality.frequency.UDTFIFFT'"

# Series Discovery
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function consecutivesequences as 'cn.edu.thu.iotdb.quality.series.UDTFConsecutiveSequences'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function consecutivewindows as 'cn.edu.thu.iotdb.quality.series.UDTFConsecutiveWindows'"

# String Processing
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function split as 'cn.edu.thu.iotdb.quality.string.UDTFSplit'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function regexmatch as 'cn.edu.thu.iotdb.quality.string.UDTFRegexMatch'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function replace as 'cn.edu.thu.iotdb.quality.string.UDTFReplace'"
./sbin/start-cli.sh -h $host -p $rpcPort -u $user -pw $pass -e "create function regexreplace as 'cn.edu.thu.iotdb.quality.string.UDTFRegexReplace'"

