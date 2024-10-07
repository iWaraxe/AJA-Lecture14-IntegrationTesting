package com.coherentsolutions.advanced.java.section01;

/**
 * Ex02IntegrationTestExample demonstrates a simple integration test.
 * Integration tests are used to test the interaction between different components or systems.
 */
public class Ex02IntegrationTestExample {

    /**
     * A simple UserService class that depends on UserRepository.
     */
    public static class UserService {
        private UserRepository userRepository;

        /**
         * Constructor injecting the UserRepository dependency.
         * @param userRepository the user repository
         */
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        /**
         * Registers a new user.
         * @param username the username
         * @param password the password
         * @return true if registration is successful, false otherwise
         */
        public boolean registerUser(String username, String password) {
            if (userRepository.userExists(username)) {
                return false; // User already exists
            } else {
                userRepository.saveUser(username, password);
                return true; // Registration successful
            }
        }
    }

    /**
     * A simple UserRepository class simulating database operations.
     */
    public static class UserRepository {

        /**
         * Checks if a user exists in the database.
         * @param username the username to check
         * @return true if user exists, false otherwise
         */
        public boolean userExists(String username) {
            // Simulate database check
            return false; // For simplicity, assume the user does not exist
        }

        /**
         * Saves a new user to the database.
         * @param username the username
         * @param password the password
         */
        public void saveUser(String username, String password) {
            // Simulate saving user to the database
            System.out.println("User " + username + " saved to the database.");
        }
    }

    /**
     * Main method to run the integration test.
     * This test checks the interaction between UserService and UserRepository.
     */
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        // Integration Test for the registerUser method
        boolean registrationResult = userService.registerUser("john_doe", "password123");
        if (registrationResult) {
            System.out.println("Integration test passed: User registered successfully.");
        } else {
            System.out.println("Integration test failed: User registration failed.");
        }
    }
}

/*
 * In this example, the UserService depends on UserRepository.
 * The integration test checks the interaction between these two classes.
 * Integration tests focus on the cooperation between different components.
 */
