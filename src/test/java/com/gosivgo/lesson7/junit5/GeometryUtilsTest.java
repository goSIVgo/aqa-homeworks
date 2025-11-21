package com.gosivgo.lesson7.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeometryUtilsTest {
    private GeometryUtils geometryUtils;

    @BeforeEach
    void setUp() {
        geometryUtils = new GeometryUtils();
    }

    @Test
    @DisplayName("Площадь треугольника: корректный расчет для правильного треугольника")
    void testTriangleAreaValidTriangle() {
        assertEquals(84.0, geometryUtils.triangleArea(10, 17, 21), 0.001,
                "Площадь треугольника со сторонами 10,17,21 должна быть 84.0");

        assertEquals(14.696, geometryUtils.triangleArea(5, 6, 7), 0.001,
                "Площадь треугольника со сторонами 5,6,7 должна быть ~14.696");
    }

    @Test
    @DisplayName("Площадь треугольника: равносторонний треугольник")
    void testTriangleAreaEquilateral() {
        double area = geometryUtils.triangleArea(4, 4, 4);
        assertEquals(6.928, area, 0.001,
                "Площадь равностороннего треугольника со стороной 4 должна быть ~6.928");
    }

    @Test
    @DisplayName("Площадь треугольника: ошибка при неположительных сторонах")
    void testTriangleAreaNonPositiveSides() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            geometryUtils.triangleArea(0, 4, 5);
        });
        assertEquals("Все стороны треугольника должны быть положительными числами",
                exception.getMessage());
    }

    @Test
    @DisplayName("Площадь треугольника: ошибка при невозможном треугольнике")
    void testTriangleAreaInvalidTriangle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            geometryUtils.triangleArea(1, 1, 3);
        });
        assertEquals("Треугольник с такими сторонами не существует",
                exception.getMessage());
    }

    @Test
    @DisplayName("Площадь прямоугольного треугольника: корректный расчет")
    void testRightTriangleArea() {
        assertEquals(6.0, geometryUtils.rightTriangleArea(3, 4), 0.001,
                "Площадь прямоугольного треугольника с основанием 3 и высотой 4 должна быть 6.0");

        assertEquals(10.0, geometryUtils.rightTriangleArea(5, 4), 0.001,
                "Площадь прямоугольного треугольника с основанием 5 и высотой 4 должна быть 10.0");
    }

    @Test
    @DisplayName("Площадь прямоугольного треугольника: ошибка при неположительных значениях")
    void testRightTriangleAreaNonPositiveValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            geometryUtils.rightTriangleArea(0, 5);
        });
        assertEquals("Основание и высота должны быть положительными числами",
                exception.getMessage());
    }

    @Test
    @DisplayName("Сравнение площадей: разные треугольники с одинаковой площадью")
    void testTriangleAreaComparison() {
        double area1 = geometryUtils.triangleArea(3, 4, 5);
        double area2 = geometryUtils.rightTriangleArea(3, 4);

        assertEquals(area1, area2, 0.001,
                "Площадь треугольника 3,4,5 и прямоугольного с основанием 3, высотой 4 должны быть равны");
    }
}
