package com.gosivgo.lesson6.phonebook;

/**
 * Демонстрация работы телефонного справочника
 */
public class PhoneBookDemo {

    public static void main(String[] args) {
        // Создаем телефонный справочник
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        // Обычные записи
        phoneDirectory.add("Любимов", "+7-913-888-88-88");
        phoneDirectory.add("Добров", "+7-923-777-77-77");
        phoneDirectory.add("Мирная", "+7-902-555-55-55");

        // Однофамильцы - несколько номеров на одну фамилию
        phoneDirectory.add("Добров", "+7-999-111-11-11");
        phoneDirectory.add("Добров", "+7-913-888-77-55");
        phoneDirectory.add("Мирная", "+7-906-666-66-66");

        // Выводим весь справочник
        phoneDirectory.printAll();

        // Поиск по фамилиям

        System.out.println("\n____ ПОИСК ПО СПРАВОЧНИКУ ____");

        phoneDirectory.findAndPrint("Мирная");
        phoneDirectory.findAndPrint("Любимов");
        phoneDirectory.findAndPrint("Добров");
        phoneDirectory.findAndPrint("Врагова");

        // Добавление новой фамилии
        System.out.println("\nДобавляем новую запись в справочник...");
        phoneDirectory.add("Астоновский", "+7-111-111-11-11");
        phoneDirectory.findAndPrint("Астоновский");

        // Вывод всего справочника
        phoneDirectory.printAll();
    }
}