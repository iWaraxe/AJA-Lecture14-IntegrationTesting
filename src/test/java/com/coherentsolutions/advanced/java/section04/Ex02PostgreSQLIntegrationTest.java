package com.coherentsolutions.advanced.java.section04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ex02PostgreSQLIntegrationTest demonstrates integration testing with a PostgreSQL container.
 */
public class Ex02PostgreSQLIntegrationTest {

    private static PostgreSQLContainer<?> postgresContainer;

    @BeforeAll
    public static void setUp() {
        // Initialize and start the PostgreSQL container
        postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        // Stop the PostgreSQL container
        postgresContainer.stop();
    }

    @Test
    public void testInsertAndQuery() throws Exception {
        // Obtain a connection to the database
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Create a table
            statement.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(100));");

            // Insert data
            statement.execute("INSERT INTO users (name) VALUES ('Alice');");
            statement.execute("INSERT INTO users (name) VALUES ('Bob');");

            // Query data
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users;");
            resultSet.next();
            int count = resultSet.getInt(1);

            // Assert the result
            assertEquals(2, count, "There should be 2 users in the table.");
        }
    }
}
