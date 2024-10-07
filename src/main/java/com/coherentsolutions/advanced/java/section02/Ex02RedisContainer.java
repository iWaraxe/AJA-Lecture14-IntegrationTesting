package com.coherentsolutions.advanced.java.section02;

import org.testcontainers.containers.GenericContainer;

/**
 * Ex02RedisContainer demonstrates starting a Redis container for testing.
 */
public class Ex02RedisContainer {

    /**
     * Main method to start a Redis container.
     */
    public static void main(String[] args) {
        // Create a container running Redis
        try (GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
                .withExposedPorts(6379)) {

            // Start the container
            redisContainer.start();

            // Get the mapped port for Redis
            Integer redisPort = redisContainer.getFirstMappedPort();

            System.out.println("Redis is running on port: " + redisPort);

            // Here you can add code to interact with Redis using the mapped port

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
