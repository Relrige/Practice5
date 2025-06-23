package com.avtojava;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@Tag("basic")
class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    @DisplayName("Addition should work on Windows OS only")
    void testAddition() {
        assumeTrue(System.getProperty("os.name").contains("Windows"), "Runs only on Windows");
        assertEquals(5, calc.add(2, 3), "Addition should work");
    }

    @Test
    @DisplayName("Subtraction should return correct result")
    void testSubtraction() {
        assumeTrue(Integer.parseInt(System.getProperty("java.version").split("\\.")[0]) >= 11,
                "Requires Java 11 or higher");
        assertEquals(3, calc.subtract(5, 2));
    }

    @Test
    @DisplayName("Multiplication should return correct result")
    void testMultiplication() {
        assumeFalse(System.getenv("ENV") != null && System.getenv("ENV").equalsIgnoreCase("prod"),
                "Skip multiplication test in production environment");
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    @DisplayName("Division by zero should throw exception, skip for guest users")
    void testDivision() {
        assumeFalse(System.getProperty("user.name").equalsIgnoreCase("guest"),
                "Skip test for guest users");
        assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
    }
}