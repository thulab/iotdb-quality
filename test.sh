#!/bin/sh
# create docker image & container
docker build -t iotdb-quality:cpy -f Dockerfile .
docker run --name iotdb-quality-cpy -d iotdb-quality:cpy /bin/bash
# test scripts here
echo Test started.
docker exec iotdb-quality-cpy /bin/sh -c "pwd"
docker exec iotdb-quality-cpy /bin/sh -c "cp /udf-tsclean-0.1.0-jar-with-dependencies.jar /iotdb/ext/udf/"
echo Test finished.
# delete docker image & container.
docker stop iotdb-quality-cpy
docker rm iotdb-quality-cpy
docker rmi iotdb-quality:cpy
echo Docker image deleted.