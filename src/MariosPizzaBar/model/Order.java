package MariosPizzaBar.model;

public class Order {
    private int orderNumber;
    private double time;
    private Pizza pizza;

    //Constuctor
    public Order (int orderNumber, double time, Pizza pizza){
        this.orderNumber = orderNumber;
        this.time = time;
        this.pizza = pizza;
    }

    //Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public double getTime() {
        return time;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String toCSV() {
        return orderNumber + "," + time + "," + "," + pizza;
    }

}
