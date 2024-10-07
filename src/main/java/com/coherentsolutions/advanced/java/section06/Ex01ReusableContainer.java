package com.coherentsolutions.advanced.java.section06;

import org.testcontainers.containers.GenericContainer;

/**
 * Ex01ReusableContainer demonstrates how to reuse a container to improve test performance.
 */
public class Ex01ReusableContainer {

    // Declare a static container to reuse across tests
    private static final GenericContainer<?> redisContainer;

    static {
        // Initialize and start the container only once
        redisContainer = new GenericContainer<>("redis:6.2.6")
                .withExposedPorts(6379)
                .withReuse(true); // Enable reuse
        redisContainer.start();
    }

    public static void main(String[] args) {
        // Check if the container is running
        if (redisContainer.isRunning()) {
            System.out.println("Reusable Redis container is running on port: " + redisContainer.getFirstMappedPort());
            // Perform operations using the container
            // For example, connect to Redis and perform caching operations
        } else {
            System.out.println("Redis container is not running.");
        }

        // Note: We do not stop the container here to allow reuse
    }
}
