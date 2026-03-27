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

    //Går gennem ArrayListen af ordres og printer dem ud
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

    //Tjekker om inputtet er en Size
    public static Size findSize(Scanner sc) {
        Size size = null;
        String input;

        while(true) {
            System.out.println("Er størrelsen 'normal', 'kids' eller 'family'?");

            if (sc.hasNext()) {
                input = sc.nextLine().toUpperCase();

                for (Size s : Size.values()) {
                    if (s.name().equalsIgnoreCase(input)) {
                        size = Size.valueOf(input);
                        return size;
                    }
                }
            } else {
                System.out.println("Ukendt input");
            }

        }
    }

    // tilføjer order til orders
    public static void addOrder() {

        System.out.println("Hvor mange pizzaer vil du tilføje?");

        int pizzaAntal = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pizzaAntal; i++) {
            System.out.println("\nPizza #" + (i + 1));

            System.out.println("Pizzanummer?");
            int pizzaNumber = Integer.parseInt(scanner.nextLine());
            Pizza newOrder = fileHandlerMenu.findPizza(pizzaNumber);

            Size size = null;

            try {
                size = findSize(scanner);
            } catch (Exception e) {
                ErrorHandler.handleErrors(e);
                ErrorHandler.handleNullException(e);
                ErrorHandler.handleInputErrors(e);
                return;
            }

            fileHandlerOrders.addOrder(new Order(newOrder, size));
            addCustomer(newOrder);
            System.out.println(Color.YELLOW + "\nPizza #" + (i + 1) + " er tilføjet" + Color.RESET);

        }

    }
    public static void addCustomer(Pizza pizza){

        int customerNumber = 0;

        while (true) {
            System.out.println("Hvilken slags kunde bestiller?\n1. Normal \n2. VIP\n3. Medarbejder");

            //Sørger for at inputtet er en int
            if (scanner.hasNextInt()) {
                customerNumber = Integer.parseInt(scanner.nextLine());

                //Sørger for at det er et tal mellem 1-3
                if (customerNumber > 0 && customerNumber <= 3) {
                    System.out.println("Indtast kundenummer: ");

                    //Udregner pris
                    switch (customerNumber) {
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

                    break;
                }
                else System.out.println("Indtast et tal mellem 1-3");
            } else {
                System.out.println("Forkerts input!");
            }

        }

    }

    // fjerner order fra orders og tilføjer til Historik.csv
    public static void concludeOrder() {
        System.out.println("Conclude order begyndt");

        System.out.println("Hvor mange ordre vil du færdiggøre?");
        int pizzaAntal = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pizzaAntal; i++) {
            if (orders.isEmpty()) {
                System.out.println(Color.RED + "Ordrelisten er tom." + Color.RESET);
            } else {
                showOrders(); //Printer ordreliste som referencepunkt

                System.out.println("Hvilke order vil du færdiggøre?");
                int orderNumber = Integer.parseInt(scanner.nextLine());
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
    public static void sortOrderList() {
        System.out.println("Hvordan vil du sorter ordrelisten?\n1. Efter tid\n2. Alfabetisk\n3. Efter pizza navn");
        int input = Integer.parseInt(scanner.nextLine());

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
