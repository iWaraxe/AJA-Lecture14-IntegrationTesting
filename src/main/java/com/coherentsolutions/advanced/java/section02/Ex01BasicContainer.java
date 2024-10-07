package com.coherentsolutions.advanced.java.section02;

import org.testcontainers.containers.GenericContainer;

/**
 * Ex01BasicContainer demonstrates how to start a simple container using Testcontainers.
 */
public class Ex01BasicContainer {

    /**
     * Main method to start a generic container.
     */
    public static void main(String[] args) {
        // Create a generic container running Alpine Linux
        try (GenericContainer<?> alpineContainer = new GenericContainer<>("alpine:latest")
                .withCommand("echo", "Hello from Alpine")) {

            // Start the container
            alpineContainer.start();

            System.out.println("Container started with ID: " + alpineContainer.getContainerId());

            // The container will automatically stop when the try-with-resources block ends
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
