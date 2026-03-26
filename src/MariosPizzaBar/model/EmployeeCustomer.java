package MariosPizzaBar.model;

public class EmployeeCustomer extends Customer{

    public EmployeeCustomer(int customerNumber) {
        super(customerNumber);
    }

    @Override
    public String getType(){
        return "EmployeeCustomer";
    }

    //Denne kunde får 20% rabat på alle pizzaer
    @Override
    public String discount(double price) {
        double savings = price * 0.20;
        double finalPrice = price - savings;
        return "Koster: " + price +" kr\nRabat: 20%\nPris: " + finalPrice + " kr";
    }
}
