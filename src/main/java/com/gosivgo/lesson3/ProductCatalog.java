package com.gosivgo.lesson3;

public class ProductCatalog {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 73999.99, true);
        productsArray[1] = new Product("iQOO NEO11", "30.10.2025", "Vivo Corp.", "China", 45500, false);
        productsArray[2] = new Product("Pixel 9 Pro XL ", "13.08.2024", "Google Corp.", "China", 116250, true);
        productsArray[3] = new Product("Honor Magic V5", "28.08.2025", "Honor Device Co.", "China", 129700, true);
        productsArray[4] = new Product("Nothing Phone 3a", "11.03.2025", "Nothing Technology Limited", "China", 31800, true);


        for (Product product : productsArray) {
            product.printInfo();
        }
    }


}


