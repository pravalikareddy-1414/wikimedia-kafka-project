# Wikimedia Kafka Streaming Application (KRaft)

A real-time event streaming application built using **Apache Kafka (KRaft mode)** and **Spring Boot**.  
The application streams live Wikimedia Recent Change events, publishes them to Kafka, and persists the consumed data using **Spring Data JPA**.

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
│
├── wikimedia-consumer
│   ├── SpringBootConsumerApplication
│   ├── WikimediaData (JPA Entity)
│   └── WikimediaDataRepository
│
└── pom.xml

## Tech Stack
- Java
- Spring Boot
- Apache Kafka (KRaft Mode)
- Spring Kafka
- Spring Data JPA (Hibernate)
- Maven (Multi-Module Project)
- Wikimedia EventStream API
- OkHttp EventSource
- Jackson
- SLF4J / Logback

---

## Prerequisites
- Java 8 or higher
- Maven
- Apache Kafka 3.x or higher (KRaft enabled)
- MySQL / PostgreSQL / H2 (any JPA-supported database)

---

## Kafka Setup (KRaft Mode)

kafka-storage.sh random-uuid
kafka-storage.sh format -t <UUID> -c config/kraft/server.properties
kafka-server-start.sh config/kraft/server.properties

## Running the Application

### Start Kafka Producer

cd wikimedia-producer
mvn spring-boot:run

### Start Kafka Consumer

cd wikimedia-consumer
mvn spring-boot:run

Data Flow
Wikimedia API → Kafka Producer → Kafka Topic → Kafka Consumer → Database

### Key Features

Kafka KRaft mode (no Zookeeper dependency)

Real-time event streaming

Producer–Consumer architecture

Database persistence using Spring Data JPA

Clean multi-module Maven structure

### Future Enhancements

Kafka Streams for real-time analytics

Docker & Docker Compose support

Monitoring using Prometheus and Grafana

##Author
Pravalika Reddy Muthannagari
Java / Backend Developer



