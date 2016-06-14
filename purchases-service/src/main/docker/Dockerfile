FROM java:8
MAINTAINER jpasimoes@gmail.com

ADD /build/${project.build.finalName}.jar /tmp/${project.build.finalName}.jar

RUN mkdir -p /opt/service/log

RUN	mv /tmp/${project.build.finalName}.jar /opt/service/${project.build.finalName}.jar

WORKDIR /opt/service/

VOLUME /opt/service/log

CMD java -jar ${project.build.finalName}.jar