FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="config-service"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/config-service-0.0.1-SNAPSHOT.jar config-service.jar

EXPOSE 8888

#HEALTHCHECK CMD curl -f http://localhost:8888/actuator/health || exit 1

ENTRYPOINT [ "/bin/sh", "-c", "java -jar config-service.jar" ]