package com.coherentsolutions.advanced.java.section08;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex01OptimizedStartup demonstrates resource management by reusing a container.
 */
@Testcontainers
public class Ex01OptimizedStartup {

    /**
     * Reusable Redis container to optimize startup times.
     * The container is shared across tests to reduce overhead.
     */
    @Container
    private static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
            .withExposedPorts(6379)
            .withReuse(true);

    @Test
    public void testRedisConnectionOne() {
        int redisPort = redisContainer.getFirstMappedPort();
        System.out.println("Test 1 - Redis is running on port: " + redisPort);

        // Perform test operations here
        assertTrue(redisContainer.isRunning(), "Redis container should be running.");
    }

    @Test
    public void testRedisConnectionTwo() {
        int redisPort = redisContainer.getFirstMappedPort();
        System.out.println("Test 2 - Redis is running on port: " + redisPort);

        // Perform test operations here
        assertTrue(redisContainer.isRunning(), "Redis container should be running.");
    }
}
