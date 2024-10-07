package com.coherentsolutions.advanced.java.section05;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Ex02KafkaContainerExample demonstrates how to use a Kafka container for integration testing.
 */
public class Ex02KafkaContainerExample {

    public static void main(String[] args) {
        // Create a Kafka container
        KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.1"));

        // Start the container
        kafkaContainer.start();

        // Producer properties
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Consumer properties
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        consumerProps.put("group.id", "test-group");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset", "earliest");

        // Topic name
        String topic = "test-topic";

        // Create a producer and send a message
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps)) {
            producer.send(new ProducerRecord<>(topic, "key", "Hello, Kafka!"));
            System.out.println("Message sent to Kafka.");
        }

        // Create a consumer and read the message
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps)) {
            consumer.subscribe(Collections.singletonList(topic));
            ConsumerRecord<String, String> record = consumer.poll(Duration.ofSeconds(5)).iterator().next();
            System.out.println("Message received from Kafka: " + record.value());
        }

        // Stop the container
        kafkaContainer.stop();
    }
}
