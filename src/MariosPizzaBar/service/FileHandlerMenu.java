package MariosPizzaBar.service;

import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.util.ErrorHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileHandlerMenu {

    private static final String MENU = "src/MariosPizzaBar/pizzamenu.csv";
    private static ArrayList<Pizza> pizzaMenu = new ArrayList<>();


    public ArrayList<Pizza> getPizzas() {
        return pizzaMenu;
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

    public Pizza findPizza(int pizzaNumber) {
        return pizzaMenu.get(pizzaNumber - 1);
    }
}
