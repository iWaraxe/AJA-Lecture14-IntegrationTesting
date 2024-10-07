package com.coherentsolutions.advanced.java.section01.advanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Advanced integration test using JUnit and Mockito.
 */
public class UserServiceTest {

    /**
     * Test method for registerUser using a mock UserRepository.
     */
    @Test
    public void testRegisterUser() {
        // Create a mock UserRepository
        Ex04AdvancedIntegrationTest.UserRepository mockRepository = Mockito.mock(Ex04AdvancedIntegrationTest.UserRepository.class);

        // Define behavior for the mock
        Mockito.when(mockRepository.userExists("john_doe")).thenReturn(false);

        Ex04AdvancedIntegrationTest.UserService userService = new Ex04AdvancedIntegrationTest.UserService(mockRepository);

        // Perform the test
        boolean result = userService.registerUser("john_doe", "password123");

        // Verify the results
        Assertions.assertTrue(result);
        Mockito.verify(mockRepository).saveUser("john_doe", "password123");
    }
}

/*
 * In this advanced example, we use JUnit and Mockito to perform integration testing.
 * Mocks help us simulate dependencies, making tests more reliable and faster.
 * This addresses some of the challenges in integration testing like dependency management.
 */
