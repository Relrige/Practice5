package com.avtojava;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("basic")
public class SimpleTest {

    @Test
    void testAddition() {
        Assumptions.assumeTrue(isJava11OrHigher(), "Test skipped: Java version is below 11");
        int sum = 1 + 1;
        assertEquals(2, sum);
    }

    @Test
    void testSubtraction() {
        Assumptions.assumeTrue(isJava11OrHigher(), "Test skipped: Java version is below 11");
        int result = 5 - 3;
        assertEquals(2, result);
    }

    @Test
    void testMultiplication() {
        Assumptions.assumeTrue(isJava11OrHigher(), "Test skipped: Java version is below 11");
        int product = 4 * 3;
        assertEquals(12, product);
    }

    @Test
    void testDivision() {
        Assumptions.assumeTrue(isJava11OrHigher(), "Test skipped: Java version is below 11");
        int quotient = 10 / 2;
        assertEquals(5, quotient);
    }

    @Test
    void testIntegerOverflow() {
        Assumptions.assumeTrue(isJava11OrHigher(), "Test skipped: Java version is below 11");
        int max = Integer.MAX_VALUE;
        int result = max + 1;
        assertTrue(result < 0, "Expected overflow to produce a negative number");
    }

    private boolean isJava11OrHigher() {
        String version = System.getProperty("java.version");
        return version.startsWith("11") || Integer.parseInt(version.split("\\.")[0]) >= 11;
    }
}
