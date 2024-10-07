package com.coherentsolutions.advanced.java.section01;

/**
 * Ex01UnitTestExample demonstrates a simple unit test.
 * Unit tests are used to test individual units of source code, such as methods or classes, in isolation.
 */
public class Ex01UnitTestExample {

    /**
     * A simple Calculator class to demonstrate unit testing.
     */
    public static class Calculator {

        /**
         * Adds two integers.
         * @param a the first integer
         * @param b the second integer
         * @return the sum of a and b
         */
        public int add(int a, int b) {
            return a + b;
        }

        /**
         * Subtracts the second integer from the first.
         * @param a the first integer
         * @param b the second integer
         * @return the result of a minus b
         */
        public int subtract(int a, int b) {
            return a - b;
        }
    }

    /**
     * Main method to run the unit tests.
     * In a real-world scenario, we would use a testing framework like JUnit.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Unit Test for the add method
        int sumResult = calculator.add(5, 3);
        if (sumResult == 8) {
            System.out.println("add method passed.");
        } else {
            System.out.println("add method failed.");
        }

        // Unit Test for the subtract method
        int subtractResult = calculator.subtract(5, 3);
        if (subtractResult == 2) {
            System.out.println("subtract method passed.");
        } else {
            System.out.println("subtract method failed.");
        }
    }
}

/*
 * In this example, we created a simple Calculator class with add and subtract methods.
 * We then wrote unit tests in the main method to test these methods individually.
 * Unit tests focus on individual components in isolation from the rest of the system.
 */
