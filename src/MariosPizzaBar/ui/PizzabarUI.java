package MariosPizzaBar.ui;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.service.FileHandler;
import MariosPizzaBar.util.OrderSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzabarUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Order> orders = new ArrayList<>();
    private static FileHandler fileHandler = new FileHandler();

    // skal kalde de andre metoder
    public static void start(){
        System.out.println("Velkommen til Marios PizzaBar system! Vælg en mulighed " +
                "ved at taste et tal fra 1-5");
        switch (){
            case 1:
                showMenu();
            case 2:
                showOrders();
            case 3:
                addOrder(Scanner scanner);
            case 4:
                concludeOrder();
            case 5:
                showHistory();
        }
    }

    // skal printe menukortet fra pizzamenu.csv
    public static void showMenu(){
        try { // læs og print pizzamenu.csv
        } catch(Exception e) {
            // gå til ErrorHandler
        }
    }

    // skal printe orders
    public static void showOrders(){

        if (orders.isEmpty()) {

            System.out.println("Ordrelisten er tom.");

        } else {

            OrderSorter.sortByTime(orders);

            for (Order order : orders) {
                System.out.println(order);

            }
        }
    }

    // tilføjer order til orders
    private static void addOrder(Scanner scanner){
        System.out.println("Ordernummer?");
        int orderNumber = scanner.nextInt();
        System.out.println("Tidspunkt?");
        int pizzaTime = scanner.nextInt();
        System.out.println("Pizzanummer?");
        int pizzaNumber = scanner.nextInt();

        // opretter en pizza baseret på pizzaNumber

        orders.add(new Order(orderNumber,pizzaTime,pizza));

    }

    // fjerner order fra orders og tilføjer til Historik.csv
    private static void concludeOrder(Order order){
        // skal kalde metode i FileHandler der tilføjer order til historik.csv
        orders.remove(order);
    }

    // skal læse og printe historik.csv
    private static void showHistory(){}
}
