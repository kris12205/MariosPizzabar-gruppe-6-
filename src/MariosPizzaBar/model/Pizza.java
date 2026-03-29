package MariosPizzaBar.model;

public class Pizza {
    //variabler til Pizza
    private int number;
    private String name;
    private int price;
    private String description;

    //Constructor
    public Pizza(int number, String name, int price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    // Constructor til showPizza
    public Pizza(int number, String name, int price, String description){
        this.number = number;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Tostring metode
    @Override
    public String toString() {
        return number + "," + name + "," + price;
    }

}
