FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="catalog-service"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/catalog-service-0.0.1-SNAPSHOT.jar catalog-service.jar

EXPOSE 8081

ENTRYPOINT [ "/bin/sh", "-c", "java -jar catalog-service.jar" ]