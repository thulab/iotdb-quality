#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

# docker build context is the root path of the repository

FROM openjdk:11-jre-slim

ADD apache-iotdb-0.12.0-SNAPSHOT-all-bin.zip /
# add dependency here
ADD download/udf-tsclean-0.1.0-jar-with-dependencies.jar /
ADD test.sh /

RUN apt update \
  && apt install lsof procps unzip -y \
  && unzip /apache-iotdb-*-bin.zip -d / \
  && rm /apache-iotdb-*-bin.zip \
  && mv /apache-iotdb-* /iotdb \
  && apt remove unzip -y \
  && apt autoremove -y \
  && apt purge --auto-remove -y \
  && apt clean -y \
# move dependency to required location
  && mv /iotdb-quality-0.1.0-jar-with-dependencies.jar /iotdb/ext/udf/ \
# include shell command lines in test.sh
# docker will auto run test commands
# may also write all commands here
  && sh test.sh

EXPOSE 6667
EXPOSE 31999
EXPOSE 5555
EXPOSE 8181
VOLUME /iotdb/data
VOLUME /iotdb/logs
ENV PATH="/iotdb/sbin/:/iotdb/tools/:${PATH}"
ENTRYPOINT ["/iotdb/sbin/start-server.sh"]