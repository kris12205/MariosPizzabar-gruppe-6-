package MariosPizzaBar.model;


import java.sql.Timestamp;

public class Order {
    private static int orderCounter = 0; //static da den skal gå på tværs
    private int orderNumber;
    private Pizza pizza;
    private Timestamp orderTime;
    private Size size;

    // Constuctor til skrivning til bestillingsliste.csv
    public Order (Pizza pizza, Size size){
        orderCounter++;
        this.orderNumber = orderCounter;
        this.orderTime = new Timestamp(System.currentTimeMillis());
        this.pizza = pizza;
        this.size = size;
    }

    // Constructor til læsning af bestillingsliste.csv
    public Order(Pizza pizza, Size size, Timestamp timestamp, int orderNumber){
        this.pizza = pizza;
        this.size = size;
        this.orderTime = timestamp;
        this.orderNumber = orderNumber;
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
