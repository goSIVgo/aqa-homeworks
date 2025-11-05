package com.gosivgo.lesson3.park;

public class ParkPoster {
    public static void main(String[] args) {
        Park dreamsPark = new Park("Остров Мечты");
        // Вывод основной информации о парке
        dreamsPark.printParkInfo();

        // Поиск конкретного аттракциона по имени
        Park.Attraction hammerOfFate = dreamsPark.findAttractionByName("Молот судьбы");
        if (hammerOfFate != null) {
            System.out.println("Нашли аттракцион: " + hammerOfFate.getAttractionName());
            System.out.println("Его цена: " + hammerOfFate.getAttractionPrice() + " руб.");
        }

        // Добавление нового аттракциона в парк
        dreamsPark.addAttraction("Замок Снежной Королевы", "11:00-18:00", 300.0);

        // Вывод обновленного списка аттракционов
        System.out.println("\nПосле добавления аттракциона(ов): ");
        dreamsPark.printParkInfo();

        // Получение информации о количестве аттракционов
        System.out.println("\nВсего аттракционов: " + dreamsPark.getAttractionCount());


    }


}

