package com.coherentsolutions.advanced.java.section05;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

/**
 * Ex01RedisContainerExample demonstrates how to use a Redis container for integration testing.
 */
public class Ex01RedisContainerExample {

    public static void main(String[] args) {
        // Create a Redis container
        try (GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
                .withExposedPorts(6379)) {

            // Start the container
            redisContainer.start();

            // Get the mapped port
            Integer redisPort = redisContainer.getFirstMappedPort();

            // Create a Jedis client to interact with Redis
            try (Jedis jedis = new Jedis("localhost", redisPort)) {
                // Set a key-value pair
                jedis.set("message", "Hello, Redis!");

                // Retrieve the value
                String value = jedis.get("message");
                System.out.println("Retrieved from Redis: " + value);
            }

            // Stop the container (automatically handled by try-with-resources)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
