package com.gosivgo.lesson4.geometry;

public interface GeometricShape {

    double getPerimeter();

    double getArea();

    String getFillColor();

    String getBorderColor();

    /**
     * Дефолтный метод для вывода всех характеристик фигуры
     * Реализован в интерфейсе для избежания дублирования кода
     */
    default void printInfo() {
        System.out.println("Периметр: " + String.format("%.2f", getPerimeter()) +
                ", Площадь: " + String.format("%.2f", getArea()) +
                ", Цвет фона: " + getFillColor() +
                ", Цвет границ: " + getBorderColor());
    }
}

