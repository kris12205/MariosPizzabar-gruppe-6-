package MariosPizzaBar.service;
import MariosPizzaBar.model.*;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import MariosPizzaBar.util.ErrorHandler;

public class FileHandler {

    private static final String MENU = "src/MariosPizzaBar/pizzamenu.csv";
    private static final String HISTORIK = "src/MariosPizzaBar/Historik.csv";
    private static final String ORDERLIST = "src/MariosPizzaBar/bestillingsliste.csv";
    private static ArrayList<Order> pizzaOrder = new ArrayList<>();
    private static ArrayList<Pizza> pizzaMenu = new ArrayList<>();
    private static ArrayList<Order> pizzaHistory = new ArrayList<>();


    public ArrayList<Pizza> getPizzas() {
        return pizzaMenu;
    }

    public ArrayList<Order> getPizzaHistory() {
        return pizzaHistory;
    }

    //Tilføje en ordre til ArrayList og CSV filen
    public void addOrder(Order order) {
        pizzaOrder.add(order);
        writeToFileOrderList();
    }

    public void loadPizzaMenu() {

        try (BufferedReader reader =
                        new BufferedReader(new FileReader(MENU))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int number = Integer.parseInt(parts[0]);
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);
                Pizza pizza = new Pizza(number, name, price);

                pizzaMenu.add(pizza);
            }


        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }

    }


    public ArrayList<Order> loadOrderList() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(ORDERLIST))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int number = Integer.parseInt(parts[2]);
                String name = parts[3];
                int price = Integer.parseInt(parts[4]);
                Size size = Size.valueOf(parts[5]);
                Pizza pizza = new Pizza(number, name, price);
                Order order = new Order(pizza, size);

                pizzaOrder.add(order);
            }


        } catch (Exception e){
            ErrorHandler.handleArrayException(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handeInputErrors(e);
        }

        return pizzaOrder;


    }


    //Går igennem pizza menuen og returner en pizza, som står på det givet index nummer
    public Pizza findPizza(int pizzaNumber){
        return pizzaMenu.get(pizzaNumber);
    }

    //Læser historik CSV filen og printer den
    public void showHistory() {

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORIK))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
            reader.close();


        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }
        //Måske udregne statistikken her?


    }

    //Går igennem ArrayListen af bestillinger og skriver dem til bestillings CSV filen
    public void writeToFileOrderList() {
        try {
            FileWriter fileWriter = new FileWriter(ORDERLIST);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Order order : pizzaOrder) {
                bufferedWriter.write(order.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }

    }

    //Går igennem historik ArrayListen og skriver den til historik CSV filen
    public static void writeToFileHistory() { //Har fjernet at metoden tager imod en ordre
        try {
            FileWriter fileWriter = new FileWriter(HISTORIK);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Order o : pizzaHistory) {
                bufferedWriter.write(o.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }

    }

    //Fjerner en ordre fra bestillingslisten, og skriver den til historikken
    public ArrayList<Order> removeOrder(int number) {
        for (int i = 0; i < pizzaOrder.size(); i++) {
            if(number == pizzaOrder.get(i).getOrderNumber()) {
                pizzaHistory.add(pizzaOrder.get(i)); //Tilføjer ordren til historik ArrayList
                writeToFileHistory(); //Skriver historik ArrayListen til CSV filen
                pizzaOrder.remove(i); //Fjerner ordren fra bestilling ArrayListen
                writeToFileOrderList(); //Opdatere bestillings CSV filen.
                return pizzaOrder;
            }
        }
        return null;
    }


}
