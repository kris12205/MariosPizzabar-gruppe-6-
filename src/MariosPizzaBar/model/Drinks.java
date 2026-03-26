package MariosPizzaBar.model;

import MariosPizzaBar.util.Color;

public class Drinks {

    private static String[] drinks = {"Coca Cola Zero", "Coca Cola", "Fanta", "Faxe Kondi", "Tuborg Classic", "Carlsberg"};

    public static void printDrinks() {
        System.out.println(Color.GREEN + String.join("\n", drinks) + Color.RESET);

    }
}
