package com.gosivgo.lesson6.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneDirectory {
    private final Map<String, List<String>> phoneBook;

    public PhoneDirectory() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        phoneBook.putIfAbsent(lastName, new ArrayList<>());

        //Добавляем номер к существующей фамилии
        List<String> numbers = phoneBook.get(lastName);
        if (!numbers.contains(phoneNumber)) {
            numbers.add(phoneNumber);
        }
    }

    public List<String> get(String lastName) {
        //Возвращаем номера или пустой список если фамилии нет
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }

    private String formatNumbers(List<String> numbers) {
        if (numbers.isEmpty()) {
            return "-"; // прочерк вместо []
        }
        return String.join(", ", numbers); //номера через запятую
    }

    //поиск с красивым выводом
    public void findAndPrint(String lastName) {
        List<String> numbers = get(lastName);
        String formattedNumbers = formatNumbers(numbers);
        System.out.println(lastName + ": " + formattedNumbers);
    }

    //Выводим весь справочник
    public void printAll() {
        System.out.println("_____________ ТЕЛЕФОННЫЙ СПРАВОЧНИК _____________");
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            String formattedNumbers = formatNumbers(entry.getValue());
            System.out.println(entry.getKey() + ": " + formattedNumbers);

        }
    }
}

