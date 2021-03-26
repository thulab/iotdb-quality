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
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function percentile as 'cn.edu.thu.iotdb.quality.dprofile.UDAFPercentile'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function distinct as 'cn.edu.thu.iotdb.quality.dprofile.UDTFDistinct'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function mode as 'cn.edu.thu.iotdb.quality.dprofile.UDAFMode'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function spread as 'cn.edu.thu.iotdb.quality.dprofile.UDAFSpread'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function stddev as 'cn.edu.thu.iotdb.quality.dprofile.UDAFStddev'"

@REM Data Quality
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function completeness as 'cn.edu.thu.iotdb.quality.dquality.UDTFCompleteness'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function consistency as 'cn.edu.thu.iotdb.quality.dquality.UDTFConsistency'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function timeliness as 'cn.edu.thu.iotdb.quality.dquality.UDTFTimeliness'"
call sbin/start-cli.bat -h %host% -p %rpcPort% -u %user% -pw %pass% -e "create function validity as 'cn.edu.thu.iotdb.quality.dquality.UDTFValidity'"