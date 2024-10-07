package com.coherentsolutions.advanced.java.section07;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ex02DynamicContainer demonstrates using a non-static container that starts and stops with each test method.
 */
@Testcontainers
public class Ex02DynamicContainer {

    /**
     * The Redis container will start and stop for each test method.
     */
    @Container
    private final GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
            .withExposedPorts(6379);

    @Test
    public void testRedisConnection() {
        int redisPort = redisContainer.getFirstMappedPort();
        System.out.println("Redis is running on port: " + redisPort);

        // Here you can add code to interact with Redis and perform assertions

        // Example assertion
        assertEquals(true, redisContainer.isRunning(), "Redis container should be running.");
    }
}
