package com.gosivgo.lesson7.junit5;

public class GeometryUtils {

    /**
     * Вычисляет площадь треугольника по формуле Герона
     * @param a длина стороны A
     * @param b длина стороны B
     * @param c длина стороны C
     * @return площадь треугольника
     */
    public double triangleArea(double a, double b, double c) {
        // Проверка на неположительные стороны
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Все стороны треугольника должны быть положительными числами");
        }

        // Проверка неравенства треугольника
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует!");
        }

        // Формула Герона
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * Вычисляет площадь прямоугольного треугольника
     * @param base основание
     * @param height высота
     * @return площадь треугольника
     */
    public double rightTriangleArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть положительными числами");
        }
        return (base * height) / 2;
    }
}