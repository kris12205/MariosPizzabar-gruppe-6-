package MariosPizzaBar.ui;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.model.Size;
import MariosPizzaBar.service.FileHandler;
import MariosPizzaBar.util.OrderSorter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static MariosPizzaBar.model.Size.*;

public class PizzabarUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Order> orders = new ArrayList<>();
    private static FileHandler fileHandler = new FileHandler();

    // skal kalde de andre metoder
    public static void start(){
        System.out.println("Velkommen til Marios PizzaBar System! Vælg en mulighed " +
                "ved at taste et tal fra 1-5");
        System.out.println("1. Vis menkort");
        System.out.println("2. Vis ordreliste");
        System.out.println("3. Tilføj ordre");
        System.out.println("4. Færdiggør ordre");
        System.out.println("5. Vis historik");

        int input = scanner.nextInt();
        fileHandler.loadPizzaMenu();

        switch (input){
            case 1:
                showMenu();
                break;
            case 2:
                OrderSorter.sortByTime(orders);
                showOrders();
                break;
            case 3:
                addOrder(scanner);
                break;
            case 4:
                concludeOrder();
                break;
            case 5:
                showHistory();
                break;
        }
    }

    // skal printe menukortet fra pizzamenu.csv
    public static void showMenu(){
        System.out.print(fileHandler.getPizzas());
    }

    // skal printe orders
    public static void showOrders(){

        if (orders.isEmpty()) {

            System.out.println("Ordrelisten er tom.");

        } else {

            for (Order order : orders) {
                System.out.println(order);

            }
        }
    }

    // tilføjer order til orders
    private static void addOrder(Scanner scanner){

        System.out.println("Pizzanummer?");
        int pizzaNumber = scanner.nextInt();
        Pizza newOrder = fileHandler.findPizza(pizzaNumber);
        scanner.nextLine();

//        System.out.println("Størrelse?");
//        String answer = scanner.nextLine();
//
//        switch (answer.toLowerCase()) {
//            case "normal": {
//                Size size = NORMAL;
//            }
//            case "kids", "kid", "small": {
//                Size size = KIDS;
//            }
//            case "family", "large": {
//                Size size = FAMILY;
//            }
//            default: System.out.println("Forkert input. Prøv igen.");
//        }

        orders.add(new Order(newOrder));
        fileHandler.writeToFileOrderList();

    }

    // fjerner order fra orders og tilføjer til Historik.csv
    private static void concludeOrder(){
        showOrders(); // printer ordreliste som referencepunkt

        System.out.println("Hvilke order vil du færdiggøre?");
        int orderNumber = scanner.nextInt();

        // skal kalde metode i FileHandler der tilføjer order til historik.csv og fjerne fra ArrayList
       // fileHandler.concludeOrder(orderNumber);
    }

    // skal læse og printe historik.csv
    private static void showHistory(){
        // fileHandler.showHistory();
    }
}
