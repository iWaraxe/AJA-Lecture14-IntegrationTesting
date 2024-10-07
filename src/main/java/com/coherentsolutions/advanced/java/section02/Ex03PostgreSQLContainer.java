package com.coherentsolutions.advanced.java.section02;


import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex03PostgreSQLContainer {

    public static void main(String[] args) {
        // Use the PostgreSQLContainer class for convenience
        try (PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")) {

            // Start the container
            postgresContainer.start();

            // Retrieve JDBC connection URL, username, and password
            String jdbcUrl = postgresContainer.getJdbcUrl();
            String username = postgresContainer.getUsername();
            String password = postgresContainer.getPassword();

            System.out.println("PostgreSQL is running with JDBC URL: " + jdbcUrl);

            // Connect to PostgreSQL using JDBC
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(255));");
                System.out.println("Table 'users' created.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
