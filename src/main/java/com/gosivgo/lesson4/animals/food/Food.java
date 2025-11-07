package com.gosivgo.lesson4.animals.food;

public class Food {
    private int foodAmount;

    public Food(int initialFood) {
        this.foodAmount = Math.max(initialFood, 0); //Защита от отрицательного количества еды
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " еды. Общее количество еды составляет: " + foodAmount);
        } else {
            System.out.println("Еды не добавлено! (отрицательное значение) ");
        }
    }

    public boolean reduceFood(int amount) {
        if (amount <= 0) {
            System.out.println("Нельзя брать отрицательное  количество еды!");
            return false;
        }
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        } else {
            System.out.println("В миске недостаточно еды!!! Нужно " + amount + " имеется лишь: " + foodAmount);
            return false;
        }
    }

    public void printFoodAmount() {
        System.out.println("Остаток еды: " + foodAmount);
    }
}
