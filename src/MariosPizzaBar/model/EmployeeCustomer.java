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
    public void discount(double price) {
        System.out.println("Rabat: 20%");
        double savings = price * 0.20;
        double finalPrice = price - savings;
        System.out.println(finalPrice);

    }
}
