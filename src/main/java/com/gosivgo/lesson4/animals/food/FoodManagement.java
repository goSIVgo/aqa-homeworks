package com.gosivgo.lesson4.animals.food;

import com.gosivgo.lesson4.animals.Cat;

public class FoodManagement {
    private static final int FOOD_PER_CAT = 30; // Норма корма на одного кота

    public static void main(String[] args) {
        System.out.println("______________  FOOD MANAGEMENT  ______________");

        // Инициализация массива котов
        Cat[] cats = {
                new Cat("Барсик"),
                new Cat("Зося"),
                new Cat("Зефирка"),
                new Cat("Балу"),
                new Cat("Вита")
        };

        Food food = new Food(100); // Создание начального запаса еды

        printInitialInfo(cats, food);
        performFeedingProcess(cats, food);
        printFinalMessage();
    }

    // Вывод начальной информации о кормлении
    private static void printInitialInfo(Cat[] cats, Food food) {
        food.printFoodAmount();
        System.out.println("Количество котов(кошек): " + cats.length);
        System.out.println("Норма корма на кота(кошку): " + FOOD_PER_CAT + " еды");
    }

    // Основной процесс кормления - продолжается пока есть голодные коты
    private static void performFeedingProcess(Cat[] cats, Food food) {
        int feedingNumber = 1;

        while (hasHungryCats(cats)) {
            System.out.println("\n______________  Start Feed № " + feedingNumber + "  ______________");

            prepareFoodForFeeding(cats, food, feedingNumber); // Подготовка еды для кормления
            feedHungryCats(cats, food);                      // Непосредственное кормление
            food.printFoodAmount();                          // Вывод остатка еды

            feedingNumber++;
        }
    }

    // Подготовка необходимого количества еды для текущего кормления
    private static void prepareFoodForFeeding(Cat[] cats, Food food, int feedingNumber) {
        // В первом кормлении используем начальный запас, в последующих - пополняем
        if (feedingNumber > 1) {
            int hungryCatsCount = countHungryCats(cats);
            int totalFoodNeeded = hungryCatsCount * FOOD_PER_CAT;
            int currentFood = food.getFoodAmount();
            int foodToAdd = Math.max(0, totalFoodNeeded - currentFood); // Защита от отрицательных значений

            System.out.println("Голодных котов(кошек): " + hungryCatsCount);
            System.out.println("Нужно еды: " + totalFoodNeeded);
            System.out.println("Сейчас еды: " + currentFood);
            System.out.println("Добавляем еды: " + foodToAdd);

            food.addFood(foodToAdd);
        }
    }

    // Кормление всех голодных котов в массиве
    private static void feedHungryCats(Cat[] cats, Food food) {
        for (Cat cat : cats) {
            if (cat.isHungry()) {
                cat.feeding(food, FOOD_PER_CAT); // Попытка накормить кота
                printCatStatus(cat);              // Вывод результата кормления
            } else {
                System.out.println(cat.getName() + " - уже сытый(а)");
            }
        }
    }

    // Вывод текущего статуса кота после кормления
    private static void printCatStatus(Cat cat) {
        String status = cat.isHungry() ? "голоден(на)" : "сытый(а)";
        System.out.println(cat.getName() + " - " + status);
    }

    // Проверка наличия голодных котов
    private static boolean hasHungryCats(Cat[] cats) {
        for (Cat cat : cats) {
            if (cat.isHungry()) {
                return true;
            }
        }
        return false;
    }

    // Подсчет количества голодных котов
    private static int countHungryCats(Cat[] cats) {
        int count = 0;
        for (Cat cat : cats) {
            if (cat.isHungry()) {
                count++;
            }
        }
        return count;
    }

    private static void printFinalMessage() {
        System.out.println("\nВСЕ КОТЫ И КОШКИ СЫТЫ! Кормление завершено.");
    }
}