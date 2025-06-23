package com.avtojava;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("basic")
public class SimpleTest {

    @Test
    void testAddition() {
        Assumptions.assumeTrue(System.getProperty("java.version").startsWith("11") ||
                        Integer.parseInt(System.getProperty("java.version").split("\\.")[0]) >= 11,
                "Test skipped: Java version is below 11");

        int sum = 1 + 1;
        assertEquals(sum, 2);
    }

}
