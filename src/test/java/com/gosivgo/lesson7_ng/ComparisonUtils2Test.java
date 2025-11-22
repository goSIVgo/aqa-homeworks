package com.gosivgo.lesson7_ng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ComparisonUtils2Test {
    private ComparisonUtils2 comparisonUtils2;

    @BeforeMethod
    public void setUp() {
        comparisonUtils2 = new ComparisonUtils2();
    }

    @Test
    public void testCompareFirstGreater() {
        assertEquals(comparisonUtils2.compare(5, 3), "5 > 3");
        assertEquals(comparisonUtils2.compare(-1, -5), "-1 > -5");
        assertEquals(comparisonUtils2.compare(0, -10), "0 > -10");
        assertEquals(comparisonUtils2.compare(100, 99), "100 > 99");
    }

    @Test
    public void testCompareFirstLess() {
        assertEquals(comparisonUtils2.compare(3, 5), "3 < 5");
        assertEquals(comparisonUtils2.compare(-5, -1), "-5 < -1");
        assertEquals(comparisonUtils2.compare(-10, 0), "-10 < 0");
        assertEquals(comparisonUtils2.compare(99, 100), "99 < 100");
    }

    @Test
    public void testCompareEqual() {
        assertEquals(comparisonUtils2.compare(5, 5), "5 = 5");
        assertEquals(comparisonUtils2.compare(0, 0), "0 = 0");
        assertEquals(comparisonUtils2.compare(-3, -3), "-3 = -3");
        assertEquals(comparisonUtils2.compare(-100, -100), "-100 = -100");
    }

    @Test
    public void testCompareBoundaryValues() {
        assertEquals(comparisonUtils2.compare(0, 1), "0 < 1");
        assertEquals(comparisonUtils2.compare(-1, 0), "-1 < 0");
        assertEquals(comparisonUtils2.compare(Integer.MAX_VALUE, Integer.MIN_VALUE),
                Integer.MAX_VALUE + " > " + Integer.MIN_VALUE);
        assertEquals(comparisonUtils2.compare(Integer.MIN_VALUE, Integer.MAX_VALUE),
                Integer.MIN_VALUE + " < " + Integer.MAX_VALUE);
    }
}
