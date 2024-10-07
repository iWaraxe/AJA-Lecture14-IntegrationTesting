package com.coherentsolutions.advanced.java.section09;

import java.util.HashMap;
import java.util.Map;

/**
 * Ex01ContractTesting demonstrates a basic contract test between a consumer and a provider microservice.
 */
public class Ex01ContractTesting {

    /**
     * ServiceProvider simulates a provider microservice that offers user data.
     */
    public static class ServiceProvider {

        /**
         * Retrieves user details based on user ID.
         * @param userId the unique identifier of the user
         * @return a map containing user details
         */
        public Map<String, String> getUserDetails(String userId) {
            // Simulated response
            Map<String, String> userDetails = new HashMap<>();
            userDetails.put("id", userId);
            userDetails.put("name", "Alice Smith");
            userDetails.put("email", "alice.smith@example.com");
            return userDetails;
        }
    }

    /**
     * ServiceConsumer simulates a consumer microservice that uses data from the provider.
     */
    public static class ServiceConsumer {

        private ServiceProvider serviceProvider;

        /**
         * Constructor injecting the ServiceProvider dependency.
         * @param serviceProvider the service provider instance
         */
        public ServiceConsumer(ServiceProvider serviceProvider) {
            this.serviceProvider = serviceProvider;
        }

        /**
         * Processes user data retrieved from the provider.
         * @param userId the unique identifier of the user
         * @return a formatted string with user information
         */
        public String processUserData(String userId) {
            Map<String, String> userDetails = serviceProvider.getUserDetails(userId);
            return String.format("User [%s]: %s - %s", userDetails.get("id"),
                    userDetails.get("name"), userDetails.get("email"));
        }
    }

    /**
     * Main method to perform a simple contract test.
     */
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProvider();
        ServiceConsumer serviceConsumer = new ServiceConsumer(serviceProvider);

        String result = serviceConsumer.processUserData("001");
        System.out.println(result);

        // Contract test assertion
        String expected = "User [001]: Alice Smith - alice.smith@example.com";
        if (result.equals(expected)) {
            System.out.println("Contract Test Passed.");
        } else {
            System.out.println("Contract Test Failed.");
        }
    }
}

/*
 * In this example:
 * - The ServiceProvider defines the expected data format (the contract).
 * - The ServiceConsumer relies on this contract to process data.
 * - The contract test verifies that the interaction adheres to the agreed-upon format.
 */
