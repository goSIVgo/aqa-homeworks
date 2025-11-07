package com.gosivgo.lesson4.animals;

import java.util.Random;

public class AnimalSkillsPresentation {
    public static void main(String[] args) {
        Random random = new Random();

        Dog dog1 = new Dog("Урал");
        Dog dog2 = new Dog("Кукла");
        Dog dog3 = new Dog("Карат");
        Dog dog4 = new Dog("Рада");
        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Зося");
        Cat cat3 = new Cat("Зефирка");

        System.out.println("______________  DOG  ______________");
        dog1.run(random.nextInt(700));
        dog2.run(random.nextInt(700));
        dog3.run(random.nextInt(700));
        dog4.run(random.nextInt(700));

        dog2.swim(random.nextInt(15));
        dog3.swim(random.nextInt(15));

        System.out.println("______________  CAT  ______________");
        cat1.run(random.nextInt(300));
        cat2.run(random.nextInt(300));
        cat3.run(random.nextInt(300));

        cat1.swim(random.nextInt(30));

        System.out.println("\n______________  RESULT  ______________");
        System.out.println("Общее количество животных: " + Animal.getAnimalCount());
        System.out.println("Котов(кошек): " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());

    }

}
