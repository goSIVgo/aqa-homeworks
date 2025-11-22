package com.gosivgo.lesson7_ng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeometryUtils2Test {

    private GeometryUtils2 geometryUtils;

    @BeforeMethod
    public void setUp() {
        geometryUtils = new GeometryUtils2();
    }

    @Test
    public void testTriangleAreaValidTriangle() {
        assertEquals(geometryUtils.triangleArea(10, 17, 21), 84.0, 0.001,
                "Площадь треугольника со сторонами 10,17,21 должна быть 84.0");

        assertEquals(geometryUtils.triangleArea(5, 6, 7), 14.696, 0.001,
                "Площадь треугольника со сторонами 5,6,7 должна быть ~14.696");
    }

    @Test
    public void testTriangleAreaEquilateral() {
        double area = geometryUtils.triangleArea(4, 4, 4);
        assertEquals(area, 6.928, 0.001,
                "Площадь равностороннего треугольника со стороной 4 должна быть ~6.928");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaNonPositiveSides() {
        geometryUtils.triangleArea(0, 4, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaInvalidTriangle() {
        geometryUtils.triangleArea(1, 1, 3);
    }

    @Test
    public void testRightTriangleArea() {
        assertEquals(geometryUtils.rightTriangleArea(3, 4), 6.0, 0.001,
                "Площадь прямоугольного треугольника с основанием 3 и высотой 4 должна быть 6.0");

        assertEquals(geometryUtils.rightTriangleArea(5, 4), 10.0, 0.001,
                "Площадь прямоугольного треугольника с основанием 5 и высотой 4 должна быть 10.0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRightTriangleAreaNonPositiveValues() {
        geometryUtils.rightTriangleArea(0, 5);
    }

    @Test
    public void testTriangleAreaComparison() {
        double area1 = geometryUtils.triangleArea(3, 4, 5);
        double area2 = geometryUtils.rightTriangleArea(3, 4);

        assertEquals(area1, area2, 0.001,
                "Площадь треугольника 3,4,5 и прямоугольного с основанием 3, высотой 4 должны быть равны");

    }
}

