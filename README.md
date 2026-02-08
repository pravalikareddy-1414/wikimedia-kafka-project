# Wikimedia Kafka Streaming Application (KRaft)

A real-time event streaming application built using **Apache Kafka (KRaft mode)** and **Spring Boot**. The application streams live Wikimedia Recent Change events, publishes them to Kafka, and persists the consumed data using **Spring Data JPA**.

---

## Overview

- Streams live Wikimedia Recent Change events from Wikimedia EventStream API
- Kafka Producer publishes events to a Kafka topic
- Kafka Consumer consumes events and stores them in a relational database
- Multi-module Maven project
- Runs on Apache Kafka **KRaft mode (no Zookeeper)**

---

## Project Structure

```text
wikimedia-kafka-parent
├── wikimedia-producer
│   ├── WikimediaChangesProducer
│   ├── WikimediaChangesHandler
│   └── KafkaTopicConfig
├── wikimedia-consumer
│   ├── SpringBootConsumerApplication
│   ├── WikimediaData (JPA Entity)
│   └── WikimediaDataRepository
└── pom.xml
```

---

## Tech Stack

- **Java 8+**
- **Spring Boot**
- **Apache Kafka (KRaft Mode)**
- **Spring Kafka**
- **Spring Data JPA (Hibernate)**
- **Maven (Multi-Module Project)**
- **Wikimedia EventStream API**
- **OkHttp EventSource**
- **Jackson**
- **SLF4J / Logback**

---

## Prerequisites

- Java 8 or higher
- Maven 3.6+
- Apache Kafka 3.x or higher (KRaft enabled)
- MySQL / PostgreSQL / H2 (any JPA-supported database)

---

## Kafka Setup (KRaft Mode)

### 1. Generate Cluster UUID

```bash
kafka-storage.sh random-uuid
```

### 2. Format Storage Directory

```bash
kafka-storage.sh format -t <UUID> -c config/kraft/server.properties
```

### 3. Start Kafka Server

```bash
kafka-server-start.sh config/kraft/server.properties
```

### 4. Create Kafka Topic (Optional)

```bash
kafka-topics.sh --create --topic wikimedia-recent-changes \
  --bootstrap-server localhost:9092 \
  --partitions 3 \
  --replication-factor 1
```

---

## Configuration

### Application Properties (Producer)

**`wikimedia-producer/src/main/resources/application.properties`**

```properties
spring.application.name=wikimedia-producer
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Topic name
kafka.topic.name=wikimedia-recent-changes
```

### Application Properties (Consumer)

**`wikimedia-consumer/src/main/resources/application.properties`**

```properties
spring.application.name=wikimedia-consumer
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=wikimedia-consumer-group
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.auto-offset-reset=earliest

# Database configuration (example for MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/wikimedia_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Application

### 1. Build the Project

```bash
cd wikimedia-kafka-parent
mvn clean install
```

### 2. Start Kafka Producer

```bash
cd wikimedia-producer
mvn spring-boot:run
```

The producer will start streaming events from the Wikimedia API and publishing them to the Kafka topic.

### 3. Start Kafka Consumer

```bash
cd wikimedia-consumer
mvn spring-boot:run
```

The consumer will listen to the Kafka topic and persist events to the database.

---

## Data Flow

```text
Wikimedia EventStream API → Kafka Producer → Kafka Topic → Kafka Consumer → Database
```

---

## Key Features

- ✅ Kafka KRaft mode (no Zookeeper dependency)
- ✅ Real-time event streaming from Wikimedia API
- ✅ Producer–Consumer architecture
- ✅ Database persistence using Spring Data JPA
- ✅ Clean multi-module Maven structure
- ✅ Configurable topic creation
- ✅ Robust error handling and logging

---

## Monitoring & Testing

### View Kafka Topics

```bash
kafka-topics.sh --list --bootstrap-server localhost:9092
```

### Consume Messages from CLI

```bash
kafka-console-consumer.sh --topic wikimedia-recent-changes \
  --from-beginning \
  --bootstrap-server localhost:9092
```

### Check Database

Query the database to verify persisted events:

```sql
SELECT * FROM wikimedia_data ORDER BY id DESC LIMIT 10;
```

---

## Future Enhancements

- 🔄 Kafka Streams for real-time analytics
- 🐳 Docker & Docker Compose support
- 📊 Monitoring using Prometheus and Grafana
- 🔐 Security with SSL/SASL authentication
- ⚡ Schema Registry integration with Avro
- 🧪 Unit and integration tests
- 📈 Rate limiting and backpressure handling

---

## Troubleshooting

### Kafka Connection Issues

- Ensure Kafka is running: `jps` (look for `Kafka` process)
- Check `bootstrap-server` configuration matches your Kafka setup

### Database Connection Issues

- Verify database credentials in `application.properties`
- Ensure database schema exists

### Events Not Consuming

- Check consumer group offset: `kafka-consumer-groups.sh --describe --group wikimedia-consumer-group --bootstrap-server localhost:9092`
- Verify topic has messages: use `kafka-console-consumer.sh`

---

## Author

**Pravalika Reddy Muthannagari**  
Java / Backend Developer  
[GitHub](https://github.com/yourusername) | [LinkedIn](https://linkedin.com/in/yourprofile)

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Wikimedia EventStream API](https://stream.wikimedia.org/)
- [Spring Kafka Documentation](https://spring.io/projects/spring-kafka)
