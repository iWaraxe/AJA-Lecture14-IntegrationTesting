package com.coherentsolutions.advanced.java.section07.advanced;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.jdbc.DataSourceBuilder;

/**
 * Ex04SelectiveContextTest demonstrates replacing @SpringBootTest with a selective test configuration.
 */
@Testcontainers
@SpringJUnitConfig // Enables Spring's context management for the test
@Import(Ex04SelectiveContextTest.TestConfig.class) // Import the TestConfig for bean definitions
public class Ex04SelectiveContextTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public DataSource dataSource() {
            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(postgresContainer.getJdbcUrl())
                    .username(postgresContainer.getUsername())
                    .password(postgresContainer.getPassword())
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }
    }

    @Test
    public void testJdbcTemplate() {
        // Perform database operations
        jdbcTemplate.execute("CREATE TABLE products (id SERIAL PRIMARY KEY, name VARCHAR(100));");
        jdbcTemplate.update("INSERT INTO products (name) VALUES (?)", "Laptop");

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products;", Integer.class);

        assertEquals(1, count, "There should be 1 product in the database.");
    }
}
