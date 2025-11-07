package com.gosivgo.lesson4.geometry;

/**
 * Класс прямоугольника с расчетом периметра и площади
 */
public class Rectangle implements GeometricShape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height); // Периметр прямоугольника
    }

    @Override
    public double getArea() {
        return width * height; // Площадь прямоугольника
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}