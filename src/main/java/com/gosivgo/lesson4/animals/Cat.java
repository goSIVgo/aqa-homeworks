package com.gosivgo.lesson4.animals;

import com.gosivgo.lesson4.animals.food.Food;

public class Cat extends Animal {
    private static int catCount = 0;
    private final int maxRunDistance = 200;
    private boolean isHungry = true;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public String getAnimalType() {
        return "кот(кошка)";
    }

    @Override
    public void run(int distance) {
        if (distance > 0 && distance <= maxRunDistance) {
            System.out.println(getAnimalType() + " по кличке " + name + " пробежал(а) " + distance + " метров.");
        } else {
            System.out.println(getAnimalType() + " по кличке " + name + " не может пробежать " + distance + " максимум " + maxRunDistance + " метров.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getAnimalType() + " по кличке " + name + " не умеет плавать!");
    }

    public void feeding(Food food, int foodAmount) {
        if (!isHungry) {
            System.out.println(getAnimalType() + " по кличке " + name + " уже сыт(а)!");
            return;
        }
        if (food.reduceFood(foodAmount)) {
            isHungry = false;
            System.out.println(getAnimalType() + " по кличке " + name + " покушал " + foodAmount + " еды!");
        } else {
            System.out.println(getAnimalType() + " по кличке " + name + " не стал(а) есть, так как еды мало!");
        }
    }

    public static int getCatCount() {
        return catCount;
    }

    // Проверить сытость
    public boolean isHungry() {
        return isHungry;
    }


}