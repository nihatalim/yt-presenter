FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY ./target/presenter.jar ./presenter.jar

ENTRYPOINT [ "java", "-jar", "/app/presenter.jar"]