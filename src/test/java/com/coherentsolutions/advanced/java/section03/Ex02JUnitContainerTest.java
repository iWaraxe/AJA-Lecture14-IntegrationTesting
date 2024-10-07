package com.coherentsolutions.advanced.java.section03;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex02JUnitContainerTest demonstrates using Testcontainers with JUnit 5 for automatic lifecycle management.
 */
public class Ex02JUnitContainerTest {

    /**
     * Initialize a PostgreSQL container.
     */
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        // Start the container before running tests
        postgresContainer.start();
    }

    @Test
    public void testDatabaseConnection() {
        // Retrieve JDBC URL and credentials
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        System.out.println("JDBC URL: " + jdbcUrl);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Here, you would connect to the database and perform tests

        // Assert that the container is running
        assertTrue(postgresContainer.isRunning(), "PostgreSQL container should be running");
    }
}
