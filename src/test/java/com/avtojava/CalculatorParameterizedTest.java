package com.avtojava;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@Tag("param")
class CalculatorParameterizedTest {

    static Calculator calc;
    static Dotenv dotenv;

    @BeforeAll
    static void init() {
        calc = new Calculator();
        dotenv = Dotenv.configure().load();
    }

    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvSource({
            "1, 2, 3",
            "5, 5, 10",
            "-2, 3, 1"
    })
    @DisplayName("Addition Test with multiple parameters")
    void testAddition(int a, int b, int expected) {
        assumeTrue("dev".equals(dotenv.get("ENV")), "Only run in dev environment");
        assertEquals(expected, calc.add(a, b));
    }

    @ParameterizedTest(name = "subtract({0}, {1}) = {2}")
    @CsvSource({
            "5, 2, 3",
            "5, 5, 0",
            "-3, 2, -5"
    })
    @DisplayName("Subtraction Test with multiple parameters")
    void testSubtraction(int a, int b, int expected) {
        assumeTrue("dev".equals(dotenv.get("ENV")), "Only run in dev environment");
        assertEquals(expected, calc.subtract(a, b));
    }

    @ParameterizedTest(name = "multiply({0}, {1}) = {2}")
    @CsvSource({
            "1, 2, 2",
            "0, 0, 0",
            "-3, 5, -15"
    })
    @DisplayName("Multiplication( Test with multiple parameters")
    void testMultiplication(int a, int b, int expected) {
        assumeTrue("dev".equals(dotenv.get("ENV")), "Only run in dev environment");
        assertEquals(expected, calc.multiply(a, b));
    }

    @ParameterizedTest(name = "divide({0}, {1}) = {2}")
    @CsvSource({
            "10, 2, 5",
            "9, 3, 3",
            "6, 1, 6"
    })
    @DisplayName("Division Test with multiple parameters")
    void testDivision(int a, int b, int expected) {
        String env = dotenv.get("ENV");
        assumeFalse(env.equals("prod"), "Only run in prod environment");
        assertEquals(expected, calc.divide(a, b));
    }

    @ParameterizedTest(name = "divide({0}, {1}) throws Exception")
    @CsvSource({
            "1, 0",
            "-5, 0",
            "0, 0"
    })
    @DisplayName("Division by zero should throw exception")
    void testDivisionByZero(int a, int b) {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(a, b));
    }
}
