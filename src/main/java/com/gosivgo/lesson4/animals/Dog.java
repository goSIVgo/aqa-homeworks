package com.gosivgo.lesson4.animals;

public class Dog extends Animal {
    private static int dogCount = 0;
    private final int maxRunDistance = 500;
    private final int maxSwimDistance = 10;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public String getAnimalType() {
        return "собака";
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
        if (distance > 0 && distance <= maxSwimDistance) {
            System.out.println(getAnimalType() + " по кличке " + name + " проплыл(а) " + distance + " метров.");
        } else {
            System.out.println(getAnimalType() + " по кличке " + name + " не может проплыть " + distance + " максимум " + maxSwimDistance + " метров.");
        }
    }

    public static int getDogCount(){
        return dogCount;
    }
}
