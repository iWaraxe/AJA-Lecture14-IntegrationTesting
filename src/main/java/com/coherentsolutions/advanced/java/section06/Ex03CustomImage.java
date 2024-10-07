package com.coherentsolutions.advanced.java.section06;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

/**
 * Ex03CustomImage demonstrates using a custom Dockerfile to create a container.
 */
public class Ex03CustomImage {

    public static void main(String[] args) {
        // Build an image from a Dockerfile
        ImageFromDockerfile customImage = new ImageFromDockerfile()
                .withDockerfileFromBuilder(builder -> builder
                        .from("alpine:latest")
                        .run("apk add --no-cache curl")
                        .cmd("sleep", "3000")
                        .build());

        // Create a container using the custom image (without exposing ports)
        try (GenericContainer<?> customContainer = new GenericContainer<>(customImage)) {

            // Start the container
            customContainer.start();

            System.out.println("Custom container started with image built from Dockerfile.");

            // Perform tests interacting with the custom application
            // ...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
