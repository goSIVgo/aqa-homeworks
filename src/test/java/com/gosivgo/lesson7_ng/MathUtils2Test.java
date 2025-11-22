package com.gosivgo.lesson7_ng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MathUtils2Test {

    private MathUtils2 mathUtils2;

    @BeforeMethod
    public void setUp() {
        mathUtils2 = new MathUtils2();
    }

    @Test
    public void testFactorialBaseCases() {
        assertEquals(mathUtils2.factorial(0), 1L, "Факториал 0 должен быть равен 1");
        assertEquals(mathUtils2.factorial(1), 1L, "Факториал 1 должен быть равен 1");
    }

    @Test
    public void testFactorialPositiveNumbers() {
        assertEquals(mathUtils2.factorial(2), 2L, "Факториал 2 должен быть равен 2");
        assertEquals(mathUtils2.factorial(3), 6L, "Факториал 3 должен быть равен 6");
        assertEquals(mathUtils2.factorial(4), 24L, "Факториал 4 должен быть равен 24");
        assertEquals(mathUtils2.factorial(5), 120L, "Факториал 5 должен быть равен 120");
        assertEquals(mathUtils2.factorial(15), 1_307_674_368_000L, "15! равен 1307674368000");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegativeNumberThrowsException() {
        mathUtils2.factorial(-2);
    }

    @Test
    public void testAddPositiveAndNegativeNumbers() {
        assertEquals(mathUtils2.add(0, 1), 1, "Ожидаемый результат: 0 + 1 = 1");
        assertEquals(mathUtils2.add(4, 3), 7, "Ожидаемый результат: 4 + 3 = 7");
        assertEquals(mathUtils2.add(-4, 4), 0, "Ожидаемый результат: -4 + 4 = 0");
        assertEquals(mathUtils2.add(-4, -3), -7, "Ожидаемый результат: -4 + (-3)= -7");
    }

    @Test
    public void testDifferenceVariousNumbers() {
        assertEquals(mathUtils2.diff(0, 4), -4, "Ожидаемый результат: 0 - 4 = -4");
        assertEquals(mathUtils2.diff(4, 4), 0, "Ожидаемый результат: 4 - 4 = 0");
        assertEquals(mathUtils2.diff(-4, 4), -8, "Ожидаемый результат: -4 - 4 = -8");
        assertEquals(mathUtils2.diff(8, 2), 6, "Ожидаемый результат: 8 - 2 = 6");
    }

    @Test
    public void testMultiplyPositiveAndNegativeAndZero() {
        assertEquals(mathUtils2.multiply(2, 4), 8, "Ожидаемый результат: 2 * 4 = 8");
        assertEquals(mathUtils2.multiply(2, -4), -8, "Ожидаемый результат: 2 * (-4) = -8");
        assertEquals(mathUtils2.multiply(4, 0), 0, "Ожидаемый результат: 4 * 0 = 0");
        assertEquals(mathUtils2.multiply(4, 4), 16, "Ожидаемый результат: 4 * 4 = 16");
    }

    @Test
    public void testDivideWithFractionalResults() {
        assertEquals(mathUtils2.div(6, 3), 2.0, "Ожидаемый результат: 6 / 3 = 2.0");
        assertEquals(mathUtils2.div(1, 2), 0.5, "Ожидаемый результат: 1 / 2 = 0.5");
        assertEquals(mathUtils2.div(6, -3), -2.0, "Ожидаемый результат: 6 / (-3) = -2.0");
        assertEquals(mathUtils2.div(0, 5), 0.0, "Ожидаемый результат: 0 / 5 = 0.0");
        assertEquals(mathUtils2.div(5, 2), 2.5, "Ожидаемый результат: 5 / 2 = 2.5");
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        mathUtils2.div(5, 0);
    }

    @Test
    public void testComplexMathematicalOperations() {
        int sum = mathUtils2.add(2, 3);
        int product = mathUtils2.multiply(sum, 4);
        double result = mathUtils2.div(product, 2);

        assertEquals(result, 10.0, "(2 + 3) × 4 / 2 должно быть 10.0");
    }
}
