package com.coherentsolutions.advanced.java.section06;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

/**
 * Ex04NetworkedContainers demonstrates linking multiple containers using a shared network.
 */
public class Ex04NetworkedContainers {

    public static void main(String[] args) {
        // Create a shared network
        Network network = Network.newNetwork();

        // Create a database container
        GenericContainer<?> dbContainer = new GenericContainer<>("mysql:8.0.33")
                .withNetwork(network)
                .withNetworkAliases("db")
                .withEnv("MYSQL_ROOT_PASSWORD", "rootpass")
                .withEnv("MYSQL_DATABASE", "testdb");

        // Create an application container that depends on the database
        GenericContainer<?> appContainer = new GenericContainer<>("nginx:latest")
                .withNetwork(network)
                .withExposedPorts(80);


        try {
            // Start the database container first
            dbContainer.start();

            // Start the application container
            appContainer.start();

            System.out.println("Application container started on port: " + appContainer.getFirstMappedPort());

            // Perform tests interacting with the application which communicates with the database
            // ...

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop containers
            appContainer.stop();
            dbContainer.stop();
            // Close network
            network.close();
        }
    }
}
