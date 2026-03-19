package MariosPizzaBar.model;


import java.sql.Timestamp;

public class Order {
    private static int orderCounter = 0; //static da den skal gå på tværs
    private int orderNumber;
    private Pizza pizza;
    private Timestamp orderTime;

    //Constuctor
    public Order (Pizza pizza){
        orderCounter++;
        this.orderNumber = orderCounter;
        this.orderTime = new Timestamp(System.currentTimeMillis());
        this.pizza = pizza;
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

    public String toCSV() {
        return orderNumber + "," + orderTime + "," + pizza;
    }

}
