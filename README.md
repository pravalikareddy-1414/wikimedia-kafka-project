Wikimedia Kafka Parent – Multi-Module Project (KRaft Mode)

📌 Project Overview

This project is a real-time event streaming application built using Apache Kafka (KRaft mode) and Spring Boot.

It consumes live Wikimedia Recent Change events, publishes them to a Kafka topic using a Kafka Producer, and stores the consumed events in a relational database using Spring Data JPA.

The project is structured as a multi-module Maven application with separate Producer and Consumer services.

🧱 Project Structure
wikimedia-kafka-parent
│
├── wikimedia-producer
│   ├── WikimediaChangesProducer
│   ├── WikimediaChangesHandler
│   └── KafkaTopicConfig
│
├── wikimedia-consumer
│   ├── SpringBootConsumerApplication
│   ├── WikimediaData (JPA Entity)
│   └── WikimediaDataRepository
│
└── pom.xml (Parent Maven POM)

🚀 Technologies Used

Java

Spring Boot

Apache Kafka (KRaft – No Zookeeper)

Spring Kafka

Spring Data JPA

Hibernate

Maven (Multi-Module)

Wikimedia EventStream API

OkHttp EventSource

Jackson

SLF4J / Logback

⚙️ Prerequisites

Java 8+

Apache Kafka 3.x+ (KRaft mode)

Maven

MySQL / PostgreSQL / H2 (any JPA-supported DB)

🛠️ Kafka Setup (KRaft Mode)
1️⃣ Generate Cluster UUID

kafka-storage.sh random-uuid

2️⃣ Format Kafka Storage

kafka-storage.sh format \
-t <UUID> \
-c config/kraft/server.properties

3️⃣ Start Kafka Broker

kafka-server-start.sh config/kraft/server.properties

📌 Kafka Topic

The topic is auto-created using Spring Kafka:

TopicBuilder.name("wikimedia_recentchange").build();

▶️ Running the Applications
Run Producer Service
cd wikimedia-producer
mvn spring-boot:run


Producer Responsibilities

Connects to Wikimedia EventStream API

Reads real-time change events

Publishes messages to Kafka topic wikimedia_recentchange

Run Consumer Service
cd wikimedia-consumer
mvn spring-boot:run


Consumer Responsibilities

Listens to Kafka topic

Consumes Wikimedia events

Persists event data into database using JPA

🗄️ Database Model
@Entity
@Table(name = "wikimedia_recentChange")
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;
}

🔄 Data Flow

Producer connects to Wikimedia EventStream

Streams real-time events

Sends events to Kafka topic

Consumer reads Kafka messages

Consumer stores events in database

✨ Key Features

Kafka KRaft mode (no Zookeeper)

Real-time event streaming

Kafka producer & consumer

Database persistence using JPA

Multi-module Maven architecture

Clean separation of concerns

🧪 Future Enhancements

Add Kafka Streams for analytics

Implement batch processing

Dockerize Kafka & services

Add monitoring with Prometheus & Grafana

👩‍💻 Author

Pravalika Reddy Muthannagari
Java / Backend Developer


