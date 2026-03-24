package MariosPizzaBar.ui;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.model.Size;
import MariosPizzaBar.service.FileHandler;
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
            System.out.println("\nVælg en mulighed ved at taste et tal fra 1-5\n" +
                    "1. Vis menukortet\n2. Vis ordreliste\n3. Tilføj ordre\n4. Færdiggør ordre" +
                    "\n5. Vis historik\n6. vis statistik\n7. Luk programmet");
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
                        concludeOrder();
                        scanner.nextLine();
                        break;
                    case 5:
                        showHistory();
                        break;
                    case 6:
                        System.out.print("Den mest populære pizza er: ");
                        System.out.println(Stats(fileHandler.getPizzaHistory()));
                        break;
                    case 7:
                        scanner.close();
                        running = false;
                        break;
                    default:
                        System.out.println("fejl");

                }
            } catch (NumberFormatException e){
                System.out.println(wrongInputMessage);
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

        // ArrayList<Order> orderList = fileHandler.loadOrderList();

        OrderSorter.sortByTime(orders);


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
        fileHandler.showHistory();
    }
}
