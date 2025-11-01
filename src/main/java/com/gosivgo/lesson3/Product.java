package com.gosivgo.lesson3;

public class Product {
    private String productName;
    private String productionDate;
    private String productManufacturer;
    private String countryOfOrigin;
    private double productPrice;
    private boolean bookingStatus;

    public Product(String name, String date, String manufacturer, String country, double price, boolean status) {
        this.productName = name;
        this.productionDate = date;
        this.productManufacturer = manufacturer;
        this.countryOfOrigin = country;
        this.productPrice = price;
        this.bookingStatus = status;
    }

    public void printInfo() {
        System.out.println("\n*****     Информация о товаре     *****");
        System.out.println("Название товара: " + productName);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + productManufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + String.format("%.2f", productPrice) + " руб.");
        System.out.println("Состояние бронирования покупателем: " + (bookingStatus ? "Забронирован" : "Доступен для заказа"));

    }

}
