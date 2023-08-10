FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="cart-service"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/cart-service-0.0.1-SNAPSHOT.jar cart-service.jar

EXPOSE 8082

ENTRYPOINT [ "/bin/sh", "-c", "java -jar cart-service.jar" ]