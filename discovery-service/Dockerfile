FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="discovery-service"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar

EXPOSE 8761

#HEALTHCHECK CMD curl -f http://localhost:8761/actuator/health || exit 1

ENTRYPOINT [ "/bin/sh", "-c", "java -jar discovery-service.jar" ]