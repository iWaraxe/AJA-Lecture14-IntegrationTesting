package com.coherentsolutions.advanced.java.section04;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ex03SchemaManagementTest demonstrates managing schema and test data.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Ex03SchemaManagementTest {

    private PostgreSQLContainer<?> postgresContainer;
    private Connection connection;

    @BeforeAll
    public void setUp() throws Exception {
        // Initialize and start the PostgreSQL container
        postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();

        // Establish a connection
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);

        // Initialize schema
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(100));");
        }
    }

    @AfterAll
    public void tearDown() throws Exception {
        // Close the connection and stop the container
        connection.close();
        postgresContainer.stop();
    }

    @BeforeEach
    public void cleanUp() throws Exception {
        // Clean up test data before each test
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users;");
        }
    }

    @Test
    public void testInsertUser() throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users (name) VALUES ('Charlie');");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(1, count, "There should be 1 user in the table.");
        }
    }

    @Test
    public void testMultipleUsers() throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users (name) VALUES ('Dave');");
            statement.execute("INSERT INTO users (name) VALUES ('Eve');");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(2, count, "There should be 2 users in the table.");
        }
    }
}
