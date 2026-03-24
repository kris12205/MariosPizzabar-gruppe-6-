package MariosPizzaBar.model;


import java.sql.Timestamp;

public class Order {
    private static int orderCounter = 0; //static da den skal gå på tværs
    private int orderNumber;
    private Pizza pizza;
    private Timestamp orderTime;
    private Size size;

    //Constuctor
    public Order (Pizza pizza, Size size){
        orderCounter++;
        this.orderNumber = orderCounter;
        this.orderTime = new Timestamp(System.currentTimeMillis());
        this.pizza = pizza;
        this.size = size;
    }

    //Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public Timestamp getTime() {
        return orderTime;
    }

    public Pizza getPizza() {
        return pizza;
    }

    //Returnere pizzaens navn. Bruges i OrderSorter til at sortere ordrelisten.
    public String getPizzaName() {
        return pizza.getName();
    }

    public String toCSV() {
        return orderNumber + "," + orderTime + "," + pizza + "," + size;
    }

}
