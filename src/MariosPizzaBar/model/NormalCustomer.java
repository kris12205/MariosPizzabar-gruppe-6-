package MariosPizzaBar.model;

public class NormalCustomer extends Customer{

    public NormalCustomer(int customerNumber) {
        super(customerNumber);
    }

    @Override
    public String getType(){
        return "NormalCustomer";
    }

    //Denne kunde får ingen rabat og betaler fuldpris
    @Override
    public void discount(double price) {
        System.out.println("Rabat: 0%");
    }
}
