* Presenter Infra

* Database: Postgresql

spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}


* Kafka: Kafka

app.kafka.brokers=${KAFKA_BROKERS}
app.kafka.username=${KAFKA_USERNAME}
app.kafka.password=${KAFKA_PASSWORD}
app.kafka.topic.prefix=${KAFKA_TOPIC_PREFIX}

* Yt-Dl: From container

* Storage

app.storage.public_endpoint=${STORAGE_PUBLIC_ENDPOINT}
app.storage.endpoint=${STORAGE_ENDPOINT}
app.storage.accesskey=${STORAGE_ACCESS_KEY}
app.storage.secretkey=${STORAGE_SECRET_KEY}
app.storage.bucket=${STORAGE_BUCKET}


* ENV
DATASOURCE_URL=jdbc:postgresql://45.147.45.253:5432/yt;
DATASOURCE_USERNAME=postgres;
DATASOURCE_PASSWORD=2204789;
KAFKA_BROKERS=rocket-01.srvs.cloudkafka.com:9094,rocket-02.srvs.cloudkafka.com:9094,rocket-03.srvs.cloudkafka.com:9094;
KAFKA_USERNAME=rr5j8p6z;
KAFKA_PASSWORD=Bn5II1ItxjggW5dNTluONl_WvrWjYnlu;
KAFKA_TOPIC_PREFIX=rr5j8p6z-;
STORAGE_PUBLIC_ENDPOINT=http://45.147.45.253:9000;
STORAGE_ENDPOINT=http://45.147.45.253:9000;
STORAGE_ACCESS_KEY=U7C7GVGZ5KILXO2I1TJQ;
STORAGE_SECRET_KEY=+orsRzDkpZojvsSHKZh4XrMj7uNxYS5gchCOdAw8;
STORAGE_BUCKET=public

