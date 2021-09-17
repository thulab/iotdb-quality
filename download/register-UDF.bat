@REM
@REM This file is used to create UDFs of IoTDB-Quality automatically.
@REM Created by Data Quality Group, School of Software, Tsinghua University
@REM 

@REM Parameters
@set host=127.0.0.1
@set rpcPort=6667
@set user=root
@set pass=root


@REM Data Profiling
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function distinct as 'cn.edu.thu.iotdb.quality.dprofile.UDTFDistinct'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function histogram as 'cn.edu.thu.iotdb.quality.dprofile.UDTFHistogram'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function integral as 'cn.edu.thu.iotdb.quality.dprofile.UDAFIntegral'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function timeweightedavg as 'cn.edu.thu.iotdb.quality.dprofile.UDAFTimeWeightedAvg'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function mad as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMad'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function median as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMedian'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function mode as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMode'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function percentile as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function period as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPeriod'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function qlb as 'cn.edu.thu.iotdb.quality.dprofile.UDTFQLB'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function re_sample as 'cn.edu.thu.iotdb.quality.dprofile.UDTFResample'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function sample as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSample'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function segment as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSegment'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function skew as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSkew'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function spread as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSpread'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function stddev as 'cn.edu.thu.iotdb.quality.dprofile.UDAFStddev'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function minmax as 'cn.edu.thu.iotdb.quality.dprofile.UDTFMinMax'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function zscore as 'cn.edu.thu.iotdb.quality.dprofile.UDTFZScore'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function spline as 'cn.edu.thu.iotdb.quality.dprofile.UDTFSpline'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function movingaverage as 'cn.edu.thu.iotdb.quality.dprofile.UDTFMovingAverage'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function pacf as 'cn.edu.thu.iotdb.quality.dprofile.UDTFPartialAutoCorrelation'"


@REM Data Quality
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function completeness as 'cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function consistency as 'cn.edu.thu.iotdb.quality.dquality.UDTFConsistency'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function timeliness as 'cn.edu.thu.iotdb.quality.dquality.UDTFTimeliness'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function validity as 'cn.edu.thu.iotdb.quality.dquality.UDTFValidity'"


@REM Data Repairing
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function timestamprepair as 'cn.edu.thu.iotdb.quality.drepair.UDTFTimestampRepair'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function valuerepair as 'cn.edu.thu.iotdb.quality.drepair.UDTFValueRepair'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function valuefill as 'cn.edu.thu.iotdb.quality.drepair.UDTFValueFill'"


@REM Data Matching
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function cov as 'cn.edu.thu.iotdb.quality.dmatch.UDAFCov'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function crosscorrelation as 'cn.edu.thu.iotdb.quality.dmatch.UDTFCrossCorrelation'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function dtw as 'cn.edu.thu.iotdb.quality.dmatch.UDAFDtw'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function patternsymmetric as 'cn.edu.thu.iotdb.quality.dmatch.UDTFPatternSymmetric'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function pearson as 'cn.edu.thu.iotdb.quality.dmatch.UDAFPearson'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function selfcorrelation as 'cn.edu.thu.iotdb.quality.dmatch.UDTFSelfCorrelation'"


@REM Anomaly Detection
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function adwin as 'cn.edu.thu.iotdb.quality.anomaly.UDTFADWIN'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function ksigma as 'cn.edu.thu.iotdb.quality.anomaly.UDTFKSigma'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function lof as 'cn.edu.thu.iotdb.quality.anomaly.UDTFLOF'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function range as 'cn.edu.thu.iotdb.quality.anomaly.UDTFRange'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function iqr as 'cn.edu.thu.iotdb.quality.anomaly.UDTFIQR'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function twosidedfilter as 'cn.edu.thu.iotdb.quality.anomaly.UDTFTwoSidedFilter'"


@REM Frequency Domain
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function FFT as 'cn.edu.thu.iotdb.quality.frequency.UDTFFFT'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function conv as 'cn.edu.thu.iotdb.quality.frequency.UDTFConv'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function deconv as 'cn.edu.thu.iotdb.quality.frequency.UDTFDeconv'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function lowpass as 'cn.edu.thu.iotdb.quality.frequency.UDTFLowPass'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function highpass as 'cn.edu.thu.iotdb.quality.frequency.UDTFHighPass'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function dwt as 'cn.edu.thu.iotdb.quality.frequency.UDTFDWT'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function idwt as 'cn.edu.thu.iotdb.quality.frequency.UDTFIDWT'"


@REM Series Discovery
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function consecutivesequences as 'cn.edu.thu.iotdb.quality.series.UDTFConsecutiveSequences'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function consecutivewindows as 'cn.edu.thu.iotdb.quality.series.UDTFConsecutiveWindows'"
