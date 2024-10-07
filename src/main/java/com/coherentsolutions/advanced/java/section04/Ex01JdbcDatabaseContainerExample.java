package com.coherentsolutions.advanced.java.section04;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

/**
 * Ex01JdbcDatabaseContainerExample demonstrates the use of JdbcDatabaseContainer with MySQL.
 */
public class Ex01JdbcDatabaseContainerExample {

    public static void main(String[] args) {
        // Create a MySQL container using JdbcDatabaseContainer
        try (JdbcDatabaseContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.33")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass")) {

            // Start the container
            mysqlContainer.start();

            // Retrieve JDBC connection details
            String jdbcUrl = mysqlContainer.getJdbcUrl();
            String username = mysqlContainer.getUsername();
            String password = mysqlContainer.getPassword();

            System.out.println("Database is running with the following details:");
            System.out.println("JDBC URL: " + jdbcUrl);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            // Here you can use the JDBC URL and credentials to connect to the database and perform tests

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
