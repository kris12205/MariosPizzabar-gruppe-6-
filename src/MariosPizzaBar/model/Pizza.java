package MariosPizzaBar.model;

public class Pizza {
    //variabler til Pizza
    private int number;
    private String name;
    private int price;

    //Constructor
    public Pizza(int number, String name, int price) {
        this.number = number;
        this.name = name;
        this.price = price;
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

    // Tostring metode
    @Override
    public String toString() {
        return number + "," + name + "," + price;
    }

}
