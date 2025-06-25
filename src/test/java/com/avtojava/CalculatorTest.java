package com.avtojava;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

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
    //@EnabledOnOs(OS.WINDOWS)
    void testAddition() {
        assumeTrue(System.getProperty("os.name").contains("Windows"), "Runs only on Windows");
        assertEquals(5, calc.add(2, 3), "Addition should work");
    }

    @Test
    @DisplayName("Subtraction should return correct result")
    void testSubtraction() {
        assumeTrue(Integer.parseInt(System.getProperty("java.version").split("\\.")[0]) >= 11,
                "Requires Java 11 or higher");

        assertAll("Subtraction cases",
                () -> assertEquals(3, calc.subtract(5, 2), "5 - 2 should be 3"),
                () -> assertEquals(0, calc.subtract(3, 3), "3 - 3 should be 0"),
                () -> assertEquals(-2, calc.subtract(2, 4), "2 - 4 should be -2"),
                () -> assertEquals(10, calc.subtract(10, 0), "10 - 0 should be 10"),
                () -> assertEquals(-10, calc.subtract(0, 10), "0 - 10 should be -10")
        );
    }


    @Nested
    @DisplayName("Multiplication Tests in Prod")
    class MultiplicationTests {

        @Test
        @DisplayName("2 * 3 = 6")
        void multiplyTwoPositiveNumbers() {
            assumeFalse(isProdEnv(), "Skip in production");
            assertEquals(6, calc.multiply(2, 3));
        }

        @Test
        @DisplayName("0 * 100 = 0")
        void multiplyWithZero() {
            assumeFalse(isProdEnv(), "Skip in production");
            assertEquals(0, calc.multiply(0, 100));
        }

        @Test
        @DisplayName("-3 * 5 = -15")
        void multiplyNegativeAndPositive() {
            assumeFalse(isProdEnv(), "Skip in production");
            assertEquals(-15, calc.multiply(-3, 5));
        }

        @Test
        @DisplayName("-3 * -3 = 9")
        void multiplyTwoNegatives() {
            assumeFalse(isProdEnv(), "Skip in production");
            assertEquals(9, calc.multiply(-3, -3));
        }

        private boolean isProdEnv() {
            String env = System.getenv("ENV");
            return env != null && env.equalsIgnoreCase("prod");
        }
    }

    @Test
    @DisplayName("Division by zero should throw exception, skip for guest users")
    void testDivision() {
        assumeFalse(System.getProperty("user.name").equalsIgnoreCase("guest"),
                "Skip test for guest users");
        assertThrows(IllegalArgumentException.class, () -> calc.divide(10, 0));
    }
}