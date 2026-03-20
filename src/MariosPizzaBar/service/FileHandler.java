package MariosPizzaBar.service;
import MariosPizzaBar.model.*;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class FileHandler {

    private static final String MENU = "src/MariosPizzaBar/pizzamenu.csv";
    private static final String HISTORIK = "src/MariosPizzaBar/Historik.csv";
    private static final String ORDERLIST = "src/MariosPizzaBar/bestillingsliste.csv";
    private static ArrayList<Order> pizzaOrder = new ArrayList<>();
    private static ArrayList<Pizza> pizzaMenu = new ArrayList<>();


    public ArrayList<Pizza> getPizzas() {
        return pizzaMenu;
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


        } catch (
                IOException e) {
            e.printStackTrace();
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
                Pizza pizza = new Pizza(number, name, price);
                Order order = new Order(pizza);

                pizzaOrder.add(order);
            }


        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
        }

        return pizzaOrder;


    }



    public Pizza findPizza(int pizzaNumber){
        return pizzaMenu.get(pizzaNumber);
    }

    public void showHistory() {

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORIK))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
            reader.close();


        } catch (
                IOException e) {
            e.printStackTrace();
        }
        //Måske udregne statistikken her?


    }

    public void writeToFileOrderList() {
        try {
            FileWriter fileWriter = new FileWriter(ORDERLIST);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Order order : pizzaOrder) {
                bufferedWriter.write(order.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFileHistory(Order order) {
        try {
            FileWriter fileWriter = new FileWriter(HISTORIK);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(order.toCSV());
            bufferedWriter.newLine();


            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removePizza(int number) {
        pizzaOrder.clear();                             //tilføjet af Dan, sletter listen fra memory
        loadOrderList();                                    //tilføjet af Dan, læser CSV fil
        for (int i = 0; i < pizzaOrder.size(); i++) {
            if (number == pizzaOrder.get(i).getPizza().getNumber()) { //rettet af Dan
                writeToFileHistory(pizzaOrder.get(i));
                pizzaOrder.remove(i);
                writeToFileOrderList();
                break;


            }
        }

    }


}
