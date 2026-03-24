package MariosPizzaBar.ui;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.model.Size;
import MariosPizzaBar.service.FileHandler;
import MariosPizzaBar.util.ErrorHandler;
import MariosPizzaBar.util.OrderSorter;
import MariosPizzaBar.model.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import static MariosPizzaBar.model.Size.*;
import static MariosPizzaBar.service.Stats.Stats;

public class PizzabarUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Order> orders = new ArrayList<>();
    private static FileHandler fileHandler = new FileHandler();
    private static String wrongInputMessage = "Forkert input. Tast venligst et tal mellem 1-6.";

    // skal kalde de andre metoder
    public static void start() {
        System.out.println("Velkommen til Marios PizzaBar System!\n");

        boolean running = true;

        //Indlæser Pizza menuen
        fileHandler.loadPizzaMenu();
        //Indlæser bestillingslisten
        orders = fileHandler.loadOrderList();
        showOrders();

        while (running) {
            System.out.println("\nVælg en mulighed ved at taste et tal fra 1-8\n" +
                    "1. Vis menukortet\n2. Vis ordreliste\n3. Tilføj ordre\n4. Sorter ordrelisten\n5. Færdiggør ordre" +
                    "\n6. Vis historik\n7. Vis statistik\n8. Luk programmet");
            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        showMenu();
                        break;
                    case 2:
                        showOrders();
                        break;
                    case 3:
                        addOrder(scanner);
                        scanner.nextLine();
                        break;
                    case 4:
                        sortOrderList(scanner);
                        scanner.nextLine();
                        break;
                    case 5:
                        concludeOrder();
                        scanner.nextLine();
                        break;
                    case 6:
                        showHistory();
                        break;
                    case 7:
                        showStats();
                        break;
                    case 8:
                        scanner.close();
                        running = false;
                        break;
                    default:
                        System.out.println("fejl");

                }
            } catch (Exception e){
               ErrorHandler.handleErrors(e);
               ErrorHandler.handleInputErrors(e);
            }
        }
    }

    // skal printe menukortet fra pizzamenu.csv
    public static void showMenu() {
        for (Pizza pizza : fileHandler.getPizzas()) {
            System.out.print(pizza + "\n");
        }
    }

    // skal printe orders
    public static void showOrders() {
        String allOrders = "";

        if (orders.isEmpty()) {

            System.out.println("Ordrelisten er tom.");

        } else {

            for (Order order : orders) {
                allOrders = allOrders.concat(order.toCSV() + "\n");

            }
            System.out.println(allOrders);
        }
    }

    // tilføjer order til orders
    private static void addOrder(Scanner scanner) {

        System.out.println("Pizzanummer?");
        int pizzaNumber = scanner.nextInt();
        Pizza newOrder = fileHandler.findPizza(pizzaNumber);
        scanner.nextLine();

        System.out.println("Er størrelsen 'normal', 'kids' eller 'large'?");
        Size size = null;

        try {
            size = Size.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Forkert input. Prøve igen.");
            return;
        }

        fileHandler.addOrder(new Order(newOrder, size));
        addCustomer(newOrder);

    }

    public static void addCustomer(Pizza pizza){

        System.out.println("Hvilken slags kunde bestiller?\n1. Normal \n2. VIP\n3. Medarbejder");
        int input = scanner.nextInt();
        System.out.print("Indtast kundenummer: ");
        int customerNumber = scanner.nextInt();

        //Udregner pris
        switch (input) {
            case 1:
                NormalCustomer nc = new NormalCustomer(customerNumber);
                System.out.println(nc.discount(pizza.getPrice()));
                break;
            case 2:
                VIPCustomer vip = new VIPCustomer(customerNumber);
                System.out.println(vip.discount(pizza.getPrice()));
                break;
            case 3:
                EmployeeCustomer ec = new EmployeeCustomer(customerNumber);
                System.out.println(ec.discount(pizza.getPrice()));
                break;
            default:
                System.out.println("Ukendt input.");
        }

    }

    //Sorter ordrelisten
    public static void sortOrderList(Scanner scan) {
        System.out.println("Hvordan vil du sorter ordrelisten?\n1. Efter tid\n2. Alfabetisk\n3. Efter pizza navn");
        int input = scan.nextInt();

        switch (input) {
            case 1:
                OrderSorter.sortByTime(orders);
                break;
            case 2:
                OrderSorter.sortByOrderNumber(orders);
                break;
            case 3:
                OrderSorter.sortByOrderName(orders);
                break;
            default:
                System.out.println("Ukendt input");
        }
    }

    // fjerner order fra orders og tilføjer til Historik.csv
    private static void concludeOrder() {
        showOrders(); // printer ordreliste som referencepunkt

        System.out.println("Hvilke order vil du færdiggøre?");
        int orderNumber = scanner.nextInt();
        ArrayList<Order> updatedOrderList = fileHandler.removeOrder(orderNumber);
        if (updatedOrderList == null) {
            System.out.println("Denne ordre eksisterer ikke.");
        } else {
            System.out.println("Ordre nr." + orderNumber + " er færdiggjort.");
        }
        //fileHandler.removePizza(orderNumber);

        // skal kalde metode i FileHandler der tilføjer order til historik.csv og fjerne fra ArrayList
        // fileHandler.concludeOrder(orderNumber);
    }

    // skal læse og printe historik.csv
    private static void showHistory() {
        if(fileHandler.getPizzaHistory().isEmpty()) {
            System.out.println("Historikken er tom");
        } else {
            fileHandler.showHistory();
        }

    }

    //Kalder stats metode
    private static void showStats() {
        if(fileHandler.getPizzaHistory().isEmpty()) {
            System.out.println("Historikken er tom");
        } else {
            System.out.println(Stats(fileHandler.getPizzaHistory()));
        }

    }
}
