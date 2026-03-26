package MariosPizzaBar.service;

import MariosPizzaBar.model.*;
import MariosPizzaBar.util.Color;
import MariosPizzaBar.util.ErrorHandler;
import MariosPizzaBar.util.OrderSorter;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderHandler {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Order> orders = new ArrayList<>();
    private static FileHandlerOrderList fileHandlerOrders = new FileHandlerOrderList();
    private static FileHandlerMenu fileHandlerMenu = new FileHandlerMenu();




    public static ArrayList<Order> getOrders(){
        return orders = fileHandlerOrders.loadOrderList();
    }

    public static void showOrders() {
        String allOrders = "";

        if (orders.isEmpty()) {

            System.out.println(Color.RED + "Ordrelisten er tom." + Color.RESET);

        } else {

            for (Order order : orders) {
                allOrders = allOrders.concat(order.toCSV() + "\n");

            }
            System.out.println(Color.YELLOW + "Bestillingsliste: \n" + allOrders + Color.RESET);
        }
    }

    // tilføjer order til orders
    public static void addOrder() {

        System.out.println("Hvor mange pizzaer vil du tilføje?");

        int pizzaAntal = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pizzaAntal; i++) {
            System.out.println("\nPizza #" + (i + 1));

            System.out.println("Pizzanummer?");
            int pizzaNumber = scanner.nextInt();
            Pizza newOrder = fileHandlerMenu.findPizza(pizzaNumber);
            scanner.nextLine();

            System.out.println("Er størrelsen 'normal', 'kids' eller 'family'?");
            Size size = null;

            try {
                size = Size.valueOf(scanner.nextLine().toUpperCase());
            } catch (Exception e) {
                ErrorHandler.handleErrors(e);
                ErrorHandler.handleNullException(e);
                ErrorHandler.handleInputErrors(e);
                return;
            }

            fileHandlerOrders.addOrder(new Order(newOrder, size));
            addCustomer(newOrder);
            System.out.println(Color.YELLOW + "\nPizza # " + (i + 1) + "er tilføjet" + Color.RESET);

        }

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
                System.out.println(Color.RED + "Ukendt input." + Color.RESET);
        }

    }

    // fjerner order fra orders og tilføjer til Historik.csv
    public static void concludeOrder() {

        int pizzaAntal = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pizzaAntal; i++) {
            if (orders.isEmpty()) {
                System.out.println(Color.RED + "Ordrelisten er tom." + Color.RESET);
            } else {
                showOrders(); //Printer ordreliste som referencepunkt

                System.out.println("Hvilke order vil du færdiggøre?");
                int orderNumber = scanner.nextInt();
                ArrayList<Order> updatedOrderList = fileHandlerOrders.removeOrder(orderNumber);

                if (updatedOrderList == null) {
                    System.out.println(Color.RED + "Denne ordre eksisterer ikke." + Color.RESET);
                } else {
                    System.out.println(Color.YELLOW + "Ordre nr." + orderNumber + " er færdiggjort." + Color.RESET);
                }
            }
        }
    }

    //Sorter ordrelisten
    public static void sortOrderList(Scanner scan) {
        System.out.println("Hvordan vil du sorter ordrelisten?\n1. Efter tid\n2. Alfabetisk\n3. Efter pizza navn");
        int input = scan.nextInt();

        switch (input) {
            case 1:
                OrderSorter.sortByTime(orders);
                showOrders();
                break;
            case 2:
                OrderSorter.sortByOrderNumber(orders);
                showOrders();
                break;
            case 3:
                OrderSorter.sortByOrderName(orders);
                showOrders();
                break;
            default:
                System.out.println(Color.RED + "Ukendt input" + Color.RESET);
        }
    }

}
