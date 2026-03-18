package MariosPizzaBar.model;

public class Pizza {
    //variabler til Pizza
    private int number;
    private String name;
    private int price;
    private Size size;

    //Constructor
    public Pizza(int number, String name, int price, Size size) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    //Getters
    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
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
        return getName() + "," + getSize() + "," + getNumber() + "," + getPrice();
    }

}
