package com.coherentsolutions.advanced.java.section06.advanced;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

/**
 * Ex05MicroservicesTest demonstrates testing interaction between two microservices.
 */
public class Ex05MicroservicesTest {

    public static void main(String[] args) {
        // Create a shared network
        Network network = Network.newNetwork();

        // Service A container
        GenericContainer<?> serviceA = new GenericContainer<>("nginx:latest")
                .withNetwork(network)
                .withNetworkAliases("service-a")
                .withExposedPorts(80);

        // Service B container
        GenericContainer<?> serviceB = new GenericContainer<>("redis:latest")
                .withNetwork(network)
                .withNetworkAliases("service-b")
                .withExposedPorts(6379);

        try {
            // Start Service A
            serviceA.start();

            // Start Service B
            serviceB.start();

            System.out.println("Service A started on port: " + serviceA.getFirstMappedPort());
            System.out.println("Service B started on port: " + serviceB.getFirstMappedPort());

            // Perform tests to verify that Service B can communicate with Service A
            // For example, send HTTP requests from Service B to Service A

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop containers
            serviceB.stop();
            serviceA.stop();
            // Close network
            network.close();
        }
    }
}
