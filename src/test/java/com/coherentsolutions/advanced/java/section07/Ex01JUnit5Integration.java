package com.coherentsolutions.advanced.java.section07;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex01JUnit5Integration demonstrates the use of Testcontainers with JUnit 5 annotations and extensions.
 */
@Testcontainers
public class Ex01JUnit5Integration {

    /**
     * The PostgreSQL container is started before tests and stopped after all tests automatically.
     */
    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Test
    public void testDatabaseIsRunning() {
        // Check if the container is running
        assertTrue(postgresContainer.isRunning(), "PostgreSQL container should be running.");

        // Retrieve connection details
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        System.out.println("JDBC URL: " + jdbcUrl);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Here you can add code to connect to the database and perform assertions
    }
}
