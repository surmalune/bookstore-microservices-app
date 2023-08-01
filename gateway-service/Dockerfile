FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="gateway-service"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar

EXPOSE 8000

#HEALTHCHECK CMD curl -f http://localhost:8000/actuator/health || exit 1

ENTRYPOINT [ "/bin/sh", "-c", "java -jar gateway-service.jar" ]