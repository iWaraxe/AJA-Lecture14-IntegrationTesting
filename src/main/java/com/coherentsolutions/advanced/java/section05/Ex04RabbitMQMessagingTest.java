package com.coherentsolutions.advanced.java.section05;

import com.rabbitmq.client.*;
import org.testcontainers.containers.RabbitMQContainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Ex04RabbitMQMessagingTest demonstrates testing a messaging application using RabbitMQ.
 */
public class Ex04RabbitMQMessagingTest {

    public static void main(String[] args) {
        // Create a RabbitMQ container
        RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8-management");

        // Start the container
        rabbitMQContainer.start();

        // Connection details
        String host = rabbitMQContainer.getHost();
        Integer port = rabbitMQContainer.getAmqpPort();

        // Set up connection and channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare a queue
            String queueName = "test-queue";
            channel.queueDeclare(queueName, false, false, false, null);

            // Publish a message
            String message = "Hello, RabbitMQ!";
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Message sent to RabbitMQ.");

            // Consume the message
            GetResponse response = channel.basicGet(queueName, true);
            if (response != null) {
                String receivedMessage = new String(response.getBody(), StandardCharsets.UTF_8);
                System.out.println("Message received from RabbitMQ: " + receivedMessage);
            } else {
                System.out.println("No messages in the queue.");
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            // Stop the container
            rabbitMQContainer.stop();
        }
    }
}
