package com.gosivgo.lesson3.park;

public class Park {
    private final String parkName;
    private final Attraction[] attractions; // массив для хранения аттракционов
    private int attractionCount; // счетчик созданных аттракционов

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new Attraction[10]; // массив вмещает до 10 аттракционов
        this.attractionCount = 0;
        initializeAttractions(); // создание начальных аттракционов

    }

    // Внутренний класс для представления аттракциона
    public class Attraction {
        private String attractionName;
        private String workingHours;
        private double attractionPrice;

        public Attraction(String attractionName, String workingHours, double attractionPrice) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.attractionPrice = attractionPrice;
        }

        // Вывод информации об аттракционе в консоль
        public void printAttractionInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + attractionPrice + " руб.");
            System.out.println("-------------------------------");
        }

        // Геттеры для доступа к данным аттракциона
        public String getAttractionName() {
            return attractionName;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public double getAttractionPrice() {
            return attractionPrice;
        }

    }

    // Добавление нового аттракциона в массив
    public void addAttraction(String name, String hours, double price) {
        if (attractionCount < attractions.length) {
            attractions[attractionCount] = new Attraction(name, hours, price);
            attractionCount++;
        } else {
            System.out.println("Нельзя добавить больше аттракционов! Превышен лимит количества аттракционов: " + attractions.length + " аттракционов");
        }
    }

    // Инициализация парка с начальным набором аттракционов
    public void initializeAttractions() {
        addAttraction("Красная башня", "10:00-20:00", 250.0);
        addAttraction("Храм огня", "10:00-20:00", 250.0);
        addAttraction("Молот судьбы", "12:00-20:00", 350.0);
        addAttraction("Водная горка", "14:00-17:30", 400.0);
        addAttraction("Полёт в тоннеле", "11:00-19:00", 450.0);
    }

    // Вывод информации о всем парке и его аттракционах
    public void printParkInfo() {
        System.out.println("Мечта ближе, чем кажется. Она уже здесь!" + "\nПАРК " + parkName);
        System.out.println("************************************");

        // Вывод информации о всех аттракционах в массиве
        for (int i = 0; i < attractionCount; i++) {
            attractions[i].printAttractionInfo();
        }
    }

    // Получение копии массива аттракционов (без пустых элементов)
    public Attraction[] getAttractions() {
        Attraction[] result = new Attraction[attractionCount];
        for (int i = 0; i < attractionCount; i++) {
            result[i] = attractions[i];
        }
        return result;
    }

    // Найти аттракцион по имени
    public Attraction findAttractionByName(String name) {
        for (int i = 0; i < attractionCount; i++) {
            if (attractions[i].getAttractionName().equals(name)) {
                return attractions[i];
            }
        }
        return null; // возвращаем null если аттракцион не найден
    }

    // Получение общего количества аттракционов в парке
    public int getAttractionCount() {
        return attractionCount;
    }

}
