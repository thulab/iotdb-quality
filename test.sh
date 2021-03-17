#!/bin/sh
# create docker image & container
docker build -t iotdb-quality:cpy -f Dockerfile .
docker run --name iotdb-quality-cpy -d iotdb-quality:cpy /bin/bash
# test scripts here
echo Test started.
docker exec iotdb-quality-cpy /bin/sh -c "mv /udf-tsclean-0.1.0-jar-with-dependencies.jar /iotdb/ext/udf/"
docker exec iotdb-quality-cpy /bin/bash start-cli.sh“create function completeness as 'cn.edu.thu.dquality.udf.UDTFCompleteness'”
docker exec iotdb-quality-cpy /bin/bash “create function completeness as 'cn.edu.thu.dquality.udf.UDTFCompleteness'”
#create function consistency as 'cn.edu.thu.dquality.udf.UDTFConsistency'
#create function timeliness as 'cn.edu.thu.dquality.udf.UDTFTimeliness'
#create function validity as 'cn.edu.thu.dquality.udf.UDTFValidity'
#create function percentile as 'cn.edu.thu.dquality.udf.UDAFPercentile'
exit
echo Test finished.
# delete docker image & container.
docker stop iotdb-quality-cpy
docker rm iotdb-quality-cpy
docker rmi iotdb-quality:cpy
echo Docker image deleted.