package com.gosivgo.lesson7.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComparisonUtilsTest {
    private ComparisonUtils comparisonUtils;

    @BeforeEach
    void setUp() {
        comparisonUtils = new ComparisonUtils();
    }

    @Test
    @DisplayName("Сравнение: первое число больше второго")
    void testCompareFirstGreater() {
        assertEquals("5 > 3", comparisonUtils.compare(5, 3));
        assertEquals("-1 > -5", comparisonUtils.compare(-1, -5));
        assertEquals("0 > -10", comparisonUtils.compare(0, -10));
        assertEquals("100 > 99", comparisonUtils.compare(100, 99));
    }

    @Test
    @DisplayName("Сравнение: первое число меньше второго")
    void testCompareFirstLess() {
        assertEquals("3 < 5", comparisonUtils.compare(3, 5));
        assertEquals("-5 < -1", comparisonUtils.compare(-5, -1));
        assertEquals("-10 < 0", comparisonUtils.compare(-10, 0));
        assertEquals("99 < 100", comparisonUtils.compare(99, 100));
    }

    @Test
    @DisplayName("Сравнение: числа равны")
    void testCompareEqual() {
        assertEquals("5 = 5", comparisonUtils.compare(5, 5));
        assertEquals("0 = 0", comparisonUtils.compare(0, 0));
        assertEquals("-3 = -3", comparisonUtils.compare(-3, -3));
        assertEquals("-100 = -100", comparisonUtils.compare(-100, -100));
    }

    @Test
    @DisplayName("Сравнение: граничные значения")
    void testCompareBoundaryValues() {
        assertEquals("0 < 1", comparisonUtils.compare(0, 1));
        assertEquals("-1 < 0", comparisonUtils.compare(-1, 0));
        assertEquals(Integer.MAX_VALUE + " > " + Integer.MIN_VALUE,
                comparisonUtils.compare(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE + " < " + Integer.MAX_VALUE,
                comparisonUtils.compare(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}