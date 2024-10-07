package com.coherentsolutions.advanced.java.section08.advanced;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ex03TroubleshootingTest demonstrates handling network and resource constraints.
 */
@Testcontainers
public class Ex03TroubleshootingTest {

    private static final Network network = Network.newNetwork();

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withNetwork(network)
            .withNetworkAliases("postgres")
            .withExposedPorts(5432)
            .withReuse(true)
            .waitingFor(Wait.forListeningPort());

    @Test
    public void testNetworkConfiguration() {
        assertTrue(postgresContainer.isRunning(), "PostgreSQL container should be running.");

        String address = postgresContainer.getHost();
        Integer port = postgresContainer.getFirstMappedPort();
        System.out.println("PostgreSQL is running at " + address + ":" + port);

        // Additional test logic here, ensuring network configurations are handled properly
    }
}
