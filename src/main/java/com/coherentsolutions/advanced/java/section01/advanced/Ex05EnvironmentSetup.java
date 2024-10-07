package com.coherentsolutions.advanced.java.section01.advanced;

import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

/**
 * Ex05EnvironmentSetup demonstrates the use of Testcontainers for environment setup.
 * Testcontainers help in creating disposable environments for integration testing.
 */
public class Ex05EnvironmentSetup {

    /**
     * Main method demonstrating the use of Testcontainers.
     */
    public static void main(String[] args) {
        // Create a Redis container
        try (GenericContainer<?> redisContainer = new GenericContainer<>("redis:5.0.3-alpine")
                .withExposedPorts(6379)) {

            // Start the container
            redisContainer.start();

            // Get the mapped port
            Integer redisPort = redisContainer.getFirstMappedPort();

            System.out.println("Redis is running on port: " + redisPort);

            // Connect to Redis
            try (Jedis jedis = new Jedis(redisContainer.getHost(), redisPort)) {
                // Set a key-value pair
                jedis.set("greeting", "Hello, Redis!");

                // Retrieve the value
                String value = jedis.get("greeting");
                System.out.println("Retrieved value from Redis: " + value);
            }

        } catch (Exception e) {
            System.out.println("Failed to start the container: " + e.getMessage());
        }
    }
}
