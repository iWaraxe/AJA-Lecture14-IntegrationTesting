package com.coherentsolutions.advanced.java.section04.advanced;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ex04TransactionRollbackTest demonstrates using transactions to ensure data isolation.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Ex04TransactionRollbackTest {

    private PostgreSQLContainer<?> postgresContainer;
    private Connection connection;

    @BeforeAll
    public void setUp() throws SQLException {
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
            statement.execute("CREATE TABLE products (id SERIAL PRIMARY KEY, name VARCHAR(100));");
        }
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Close the connection and stop the container
        connection.close();
        postgresContainer.stop();
    }

    @BeforeEach
    public void beginTransaction() throws SQLException {
        // Begin a transaction
        connection.setAutoCommit(false);
    }

    @AfterEach
    public void rollbackTransaction() throws SQLException {
        // Roll back the transaction to clean up
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    public void testAddProduct() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO products (name) VALUES ('Laptop');");
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM products;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(1, count, "There should be 1 product in the table.");
        }
    }

    @Test
    public void testProductIsolation() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM products;");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertEquals(0, count, "There should be 0 products in the table due to rollback.");
        }
    }
}
