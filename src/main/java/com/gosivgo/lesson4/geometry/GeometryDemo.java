package com.gosivgo.lesson4.geometry;

/**
 * Демонстрационный класс для работы с геометрическими фигурами
 */
public class GeometryDemo {
    public static void main(String[] args) {
        System.out.println("========== РАСЧЕТ ХАРАКТЕРИСТИК ГЕОМЕТРИЧЕСКИХ ФИГУР ==========\n");

        // Создание массива различных фигур
        GeometricShape[] shapes = {
                new Circle(5.0, "красный", "черный"),
                new Rectangle(4.0, 6.0, "синий", "белый"),
                new Triangle(3.0, 4.0, 5.0, "зеленый", "желтый"),
                new Circle(7.5, "оранжевый", "коричневый"),
                new Rectangle(10.0, 2.5, "фиолетовый", "серый")
        };

        // Вывод информации о каждой фигуре
        for (int i = 0; i < shapes.length; i++) {
            System.out.print("Фигура " + (i + 1) + ": ");
            shapes[i].printInfo(); // Использование дефолтного метода из интерфейса
        }

        System.out.println("\n========== ПРИМЕРЫ ОТДЕЛЬНЫХ ФИГУР ==========");

        // Демонстрация работы с конкретными фигурами
        Circle circle = new Circle(10.0, "голубой", "черный");
        System.out.print("Круг: ");
        circle.printInfo();

        Triangle triangle = new Triangle(5.0, 12.0, 13.0, "розовый", "золотой");
        System.out.print("Треугольник: ");
        triangle.printInfo();
    }
}