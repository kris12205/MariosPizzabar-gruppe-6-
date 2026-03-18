package MariosPizzaBar.model;

public abstract class Customer {

    private int customerNumber;

    //Constructor
    public Customer(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    //Getter
    public int getCustomerNumer() {
        return customerNumber;
    }

    //Abstract method
    public abstract String getType();

    public abstract void discount(double price);

}
