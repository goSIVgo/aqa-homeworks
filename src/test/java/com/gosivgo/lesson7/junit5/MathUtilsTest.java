package com.gosivgo.lesson7.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    @DisplayName("Факториал: базовые случаи 0 и 1 должны возвращать 1")
     void testFactorialBaseCases() {
        assertEquals(1, mathUtils.factorial(0), "Факториал 0 должен быть равен 1");
        assertEquals(1, mathUtils.factorial(1), "Факториал 1 должен быть равен 1");
    }

    @Test
    @DisplayName("Факториал: корректный расчет для положительных чисел")
    void testFactorialPositiveNumbers() {
        assertEquals(2, mathUtils.factorial(2), "Факториал 2 должен быть равен 2");
        assertEquals(6, mathUtils.factorial(3), "Факториал 3 должен быть равен 6");
        assertEquals(24, mathUtils.factorial(4), "Факториал 4 должен быть равен 24");
        assertEquals(120, mathUtils.factorial(5), "Факториал 5 должен быть равен 120");
        assertEquals(1_307_674_368_000L, mathUtils.factorial(15), "15! равен 1307674368000");
    }

    @Test
    @DisplayName("Факториал: ошибка при отрицательном числе")
    void testFactorialNegativeNumberThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                mathUtils.factorial(-2), "Факториал отрицательного числа должен вызывать исключение");

        assertEquals("Факториал определён только для неотрицательных чисел", exception.getMessage(),
                "Сообщение об ошибке должно быть понятным!");
    }

    @Test
    @DisplayName("Сложение: корректная работа с положительными и отрицательными числами")
    void testAddPositiveAndNegativeNumbers() {
        assertEquals(1, mathUtils.add(0, 1), "Ожидаемый результат: 0 + 1 = 1");
        assertEquals(7, mathUtils.add(4, 3), "Ожидаемый результат: 4 + 3 = 7");
        assertEquals(0, mathUtils.add(-4, 4), "Ожидаемый результат: -4 + 4 = 0");
        assertEquals(-7, mathUtils.add(-4, -3), "Ожидаемый результат: -4 + (-3)= -7");
    }

    @Test
    @DisplayName("Разность: корректная работа с разными числами")
     void testDifferenceVariousNumbers() {
        assertEquals(-4, mathUtils.diff(0, 4), "Ожидаемый результат: 0 - 4 = -4");
        assertEquals(0, mathUtils.diff(4, 4), "Ожидаемый результат: 4 - 4 = 0");
        assertEquals(-8, mathUtils.diff(-4, 4), "Ожидаемый результат: -4 - 4 = -8");
        assertEquals(6, mathUtils.diff(8, 2), "Ожидаемый результат: 8 - 2 = 6");
    }

    @Test
    @DisplayName("Умножение: с отрицательными и положительными числами, с нулём")
    void testMultiplyPositiveAndNegativeAndZero() {
        assertEquals(8, mathUtils.multiply(2, 4), "Ожидаемый результат: 2 * 4 = 8");
        assertEquals(-8, mathUtils.multiply(2, -4), "Ожидаемый результат: 2 * (-4) = -8");
        assertEquals(0, mathUtils.multiply(4, 0), "Ожидаемый результат: 4 * 0 = 0");
        assertEquals(16, mathUtils.multiply(4, 4), "Ожидаемый результат: 4 * 4 = 16");
    }

    @Test
    @DisplayName("Деление: корректный расчет дробных результатов")
    void testDivideWithFractionalResults() {
        assertEquals(2.0, mathUtils.div(6, 3), "Ожидаемый результат: 6 / 3 = 2.0");
        assertEquals(0.5, mathUtils.div(1, 2), "Ожидаемый результат: 1 / 2 = 0.5");
        assertEquals(-2.0, mathUtils.div(6, -3), "Ожидаемый результат: 6 / (-3) = -2.0");
        assertEquals(0.0, mathUtils.div(0, 5), "Ожидаемый результат: 0 / 5 = 0.0");
        assertEquals(2.5, mathUtils.div(5, 2), "Ожидаемый результат: 5 / 2 = 2.5");
    }

    @Test
    @DisplayName("Деление: ошибка при делении на ноль")
    void testDivideByZeroThrowsException() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                mathUtils.div(5, 0), "Деление на ноль должно вызывать исключение");

        assertEquals("Деление на ноль невозможно!",
                exception.getMessage(),
                "Сообщение об ошибке должно быть ясным");
    }

    @Test
    @DisplayName("Комплексный тест: последовательные математические операции")
    void testComplexMathematicalOperations() {

        int sum = mathUtils.add(2, 3);
        int product = mathUtils.multiply(sum, 4);
        double result = mathUtils.div(product, 2);

        assertEquals(10.0, result, "(2 + 3) × 4 / 2 должно быть 10.0");
    }
}
