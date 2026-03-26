package MariosPizzaBar.model;

public class VIPCustomer extends Customer{

    public VIPCustomer(int customerNumber) {
        super(customerNumber);
    }

    @Override
    public String getType(){
        return "VIPCustomer";
    }

    //Denne kunde får 10% rabat på alle pizzaer
    @Override
    public String discount(double price) {
        double savings = price * 0.10;
        double finalPrice = price - savings;
        return "Koster: " + price +" kr\nRabat: 10%\nPris: " + finalPrice + " kr";
    }
}
