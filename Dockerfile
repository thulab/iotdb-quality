# docker build context is the root path of the repository

FROM openjdk:11-jre-slim

ADD apache-iotdb-0.12.0-SNAPSHOT-all-bin.zip /

# ADD jdk-8u221-linux-x64.tar.gz /usr/local/
# add dependency here
ADD download/udf-tsclean-0.1.0-jar-with-dependencies.jar /


RUN apt update \
  && apt install lsof procps unzip -y \
  && unzip /apache-iotdb-*-bin.zip -d / \
  && rm /apache-iotdb-*-bin.zip \
  && mv /apache-iotdb-* /iotdb \
  && apt remove unzip -y \
  && apt autoremove -y \
  && apt purge --auto-remove -y \
  && apt clean -y

EXPOSE 6667
EXPOSE 31999
EXPOSE 5555
EXPOSE 8181
VOLUME /iotdb/data
VOLUME /iotdb/logs
VOLUME /iotdb/ext/udf/
# ENV JAVA_HOME /usr/local/jdk1.8.0_221
# ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH="$JAVA_HOME/bin:/iotdb/sbin/:/iotdb/tools/:${PATH}"
# ENV PATH="$JAVA_HOME/bin:${PATH}"
ENTRYPOINT ["/iotdb/sbin/start-server.sh"]