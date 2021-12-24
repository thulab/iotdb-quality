#!/bin/bash
# delete last docker image & container.
docker stop iotdb-quality-2.0
docker rm iotdb-quality-2.0
docker rmi iotdb-quality:2.0
echo Docker image deleted.
# create docker image & container
docker build -t iotdb-quality:2.0 -f Dockerfile .
docker run --name iotdb-quality-2.0 -d -p 6667:6667 -p 31999:31999 -p 8181:8181 -p 5555:5555 iotdb-quality:2.0 /bin/bash
sleep 30s
# test scripts here
echo Test started.
sudo docker exec iotdb-quality-2.0 /bin/sh -c "cd /iotdb-0.12.3-all-bin/"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "nohup sbin/start-server.sh >/dev/null 2>&1 &"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "cd /iotdb-quality-v2.0"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "mvn spotless:apply"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "mvn test"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "mvn package -DskipTests=True"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "mv ./target/iotdb-quality-2.0.0-SNAPSHOT-jar-with-dependencies.jar /iotdb/ext/udf/"
sudo docker exec iotdb-quality-2.0 /bin/sh -c "sh ./download/register-UDF.sh"
echo Test finished.
# delete docker image & container.
docker stop iotdb-quality-2.0
docker rm iotdb-quality-2.0
docker rmi iotdb-quality:2.0
echo Docker image deleted.