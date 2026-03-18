package MariosPizzaBar.service;

import MariosPizzaBar.model.*;
import jdk.jfr.FlightRecorderListener;


import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String MENU = "src/MariosPizzaBar/pizzamenu.csv";
    private static final String HISTORIK = "src/MariosPizzaBar/Historik.csv";
    private static final String ORDERLILIST = "src/MariosPizzaBar/bestillingsliste.csv";
    private static ArrayList<Order> pizzaOrder = new ArrayList<>();
    private static ArrayList<Pizza> pizzaMenu = new ArrayList<>();


    public ArrayList<Pizza> getPizzas() {
        return pizzas;
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

    public void addPizza(Pizza pizza) {
        pizzaOrder.add(pizza);
        writeToFile();
    }

    public static void writeToFileOrderList() {
        try {
            FileWriter fileWriter = new FileWriter(ORDERLILIST);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Pizza pizza : pizzaOrder) {
                bufferedWriter.write(pizza.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFileHistory() {
        try {
            FileWriter fileWriter = new FileWriter(HISTORIK);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Pizza pizza : pizzaOrder) {
                bufferedWriter.write(pizza.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Order> removePizza(int number) {
        for (int i = 0; i < pizzaOrder.size(); i++) {
            if (number == pizzaOrder.get(i).getOrderNumber(); {
                pizzaOrder.remove(i); //fjerner sang fra ArrayList
                writeToFileOrderList();
                //opdatere txt
                return pizza;
            }
        }
        return null;
    }


}
