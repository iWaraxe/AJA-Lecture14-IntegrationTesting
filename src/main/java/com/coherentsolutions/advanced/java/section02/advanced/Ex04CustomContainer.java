package com.coherentsolutions.advanced.java.section02.advanced;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Ex04CustomContainer demonstrates creating a custom container configuration.
 */
public class Ex04CustomContainer {

    /**
     * Main method to start a custom container.
     */
    public static void main(String[] args) {
        // Create a container with custom configurations
        DockerImageName imageName = DockerImageName.parse("nginx:latest");
        try (GenericContainer<?> nginxContainer = new GenericContainer<>(imageName)
                .withExposedPorts(80)
                .withEnv("ENV_VAR", "value")
                .withCommand("/bin/bash", "-c", "echo 'Custom Nginx Container' && nginx -g 'daemon off;'")) {

            // Start the container
            nginxContainer.start();

            Integer nginxPort = nginxContainer.getFirstMappedPort();

            System.out.println("Nginx is running on port: " + nginxPort);

            // Here you can send HTTP requests to Nginx using the mapped port

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
