FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY ./target/presenter.jar ./presenter.jar

VOLUME /app/logs

ENTRYPOINT [ "java", "-jar", "/app/presenter.jar"]