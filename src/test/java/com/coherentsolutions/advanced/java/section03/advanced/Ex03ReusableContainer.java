package com.coherentsolutions.advanced.java.section03.advanced;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex03ReusableContainer demonstrates how to reuse a container across multiple tests.
 */
public class Ex03ReusableContainer {

    private static MySQLContainer<?> mysqlContainer;

    @BeforeAll
    public static void setUp() {
        // Initialize and start the MySQL container
        mysqlContainer = new MySQLContainer<>("mysql:8.0.33")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        mysqlContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        // Stop the MySQL container
        mysqlContainer.stop();
    }

    @Test
    public void testFirst() {
        assertTrue(mysqlContainer.isRunning(), "MySQL container should be running");
        // First test logic here
    }

    @Test
    public void testSecond() {
        assertTrue(mysqlContainer.isRunning(), "MySQL container should still be running");
        // Second test logic here
    }
}
