package MariosPizzaBar.service;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.model.Size;
import MariosPizzaBar.util.ErrorHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandlerOrderList {

    private static final String ORDERLIST = "src/MariosPizzaBar/bestillingsliste.csv";
    private static ArrayList<Order> pizzaOrder = new ArrayList<>();
    private static FileHandlerHistory handlerHistory = new FileHandlerHistory();


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

    //Tilføje en ordre til ArrayList og CSV filen
    public void addOrder(Order order) {
        pizzaOrder.add(order);
        writeToFileOrderList();
    }

    //Læser bestillingsliste.csv igennem og tilføjer dem til ArrayListen pizzaOrder
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
            ErrorHandler.handleInputErrors(e);
        }

        return pizzaOrder;

    }

    //Fjerner en ordre fra bestillingslisten, og skriver den til historikken
    public ArrayList<Order> removeOrder(int number) {
        for (int i = 0; i < pizzaOrder.size(); i++) {
            if(number == pizzaOrder.get(i).getOrderNumber()) {
                handlerHistory.getPizzaHistory().add(pizzaOrder.get(i)); //Tilføjer ordren til historik ArrayList
                handlerHistory.writeToFileHistory(); //Skriver historik ArrayListen til CSV filen
                pizzaOrder.remove(i); //Fjerner ordren fra bestilling ArrayListen
                writeToFileOrderList(); //Opdatere bestillings CSV filen.
                return pizzaOrder;
            }
        }
        return null;
    }
}
