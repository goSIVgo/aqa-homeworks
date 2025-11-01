package com.gosivgo.lesson3.park;

public class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    public class Attraction {
        private String attractionName;
        private String workingHours;
        private double attractionPrice;

        public Attraction(String attractionName, String workingHours, double attractionPrice) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.attractionPrice = attractionPrice;
        }

        public void printAttractionInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + attractionPrice + " руб.");
            System.out.println("-------------------------------");
        }
    }

    public void printParkInfo() {
        System.out.println("Мечта ближе, чем кажется. Она уже здесь!" + "\nПАРК " + parkName);
        System.out.println("************************************");

        Attraction redTower = new Attraction("Красная башня", "10:00-20:00", 250.0);
        Attraction templeofFire = new Attraction("Храм огня", "10:00-20:00", 250.0);
        Attraction hammerOfFate = new Attraction("Молот судьбы", "12:00-20:00", 350.0);
        Attraction waterSlide = new Attraction("Водная горка", "14:00-17:30", 400.0);
        Attraction flyingInTunnel = new Attraction("Полёт в тоннеле", "11:00-19:00", 450.0);

        // Выводим информацию об аттракционах
        redTower.printAttractionInfo();
        templeofFire.printAttractionInfo();
        hammerOfFate.printAttractionInfo();
        waterSlide.printAttractionInfo();
        flyingInTunnel.printAttractionInfo();

    }
}
