package com.coherentsolutions.advanced.java.section03;

import org.testcontainers.containers.GenericContainer;

/**
 * Ex01StartSimpleContainer demonstrates how to start and stop a simple container using Testcontainers.
 */
public class Ex01StartSimpleContainer {

    public static void main(String[] args) {
        // Create a generic container running Redis
        GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
                .withExposedPorts(6379);

        // Start the container
        redisContainer.start();

        // Get the mapped port
        Integer redisPort = redisContainer.getFirstMappedPort();

        System.out.println("Redis container started on port: " + redisPort);

        // Perform operations with Redis here

        // Stop the container
        redisContainer.stop();

        System.out.println("Redis container stopped.");
    }
}
