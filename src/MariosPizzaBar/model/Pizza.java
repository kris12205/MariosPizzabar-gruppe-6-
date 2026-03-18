package MariosPizzaBar.model;

public class Pizza {
    private String name;
    private Size size;
    private int number;
    private int price;

    public Pizza(String name, Size size, int number, int price) {
        this.name = name;
        this.size = size;
        this.number = number;
        this.price = price;
    }

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

    @Override
    public String toString() {
        return getName() + "," + getSize() + "," + getNumber() + "," + getPrice();
    }

}
