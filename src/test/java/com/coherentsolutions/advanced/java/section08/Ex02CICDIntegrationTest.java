package com.coherentsolutions.advanced.java.section08;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex02CICDIntegrationTest demonstrates a test class that can be run in CI/CD environments.
 */
@Testcontainers
public class Ex02CICDIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("ci_db")
            .withUsername("ci_user")
            .withPassword("ci_pass");

    @Test
    public void testDatabaseRunningInCI() {
        assertTrue(postgresContainer.isRunning(), "PostgreSQL container should be running in CI environment.");

        String jdbcUrl = postgresContainer.getJdbcUrl();
        System.out.println("JDBC URL: " + jdbcUrl);

        // Additional test logic here
    }
}
