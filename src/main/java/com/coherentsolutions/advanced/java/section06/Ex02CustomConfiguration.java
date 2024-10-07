package com.coherentsolutions.advanced.java.section06;

import org.testcontainers.containers.GenericContainer;

/**
 * Ex02CustomConfiguration demonstrates modifying container behavior.
 */
public class Ex02CustomConfiguration {

    public static void main(String[] args) {
        // Create a container with custom environment variables and commands
        try (GenericContainer<?> customContainer = new GenericContainer<>("nginx:latest")
                .withExposedPorts(80)
                .withEnv("MY_ENV_VAR", "my_value")
                .withCommand("/bin/sh", "-c", "echo 'Custom Nginx Container' && nginx -g 'daemon off;'")) {

            // Start the container
            customContainer.start();

            System.out.println("Custom Nginx container started on port: " + customContainer.getFirstMappedPort());

            // Perform tests interacting with the Nginx server
            // For example, send HTTP requests to verify server responses

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
