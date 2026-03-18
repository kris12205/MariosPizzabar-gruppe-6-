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

        switch (input){
            case 1:
                showMenu();
            case 2:
                showOrders();
            case 3:
                addOrder(scanner);
            case 4:
                concludeOrder();
            case 5:
                showHistory();
        }
    }

    // skal printe menukortet fra pizzamenu.csv
    public static void showMenu(){
        fileHandler.loadPizzaMenu();
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

        System.out.println("Ordrenummer?");
        int orderNumber = scanner.nextInt();

        System.out.println("Tidspunkt?");
        int pizzaTime = scanner.nextInt();

        System.out.println("Pizzanummer?");
        int pizzaNumber = scanner.nextInt();
        // skal kalde en metode i FileHandler der opretter en pizza baseret på pizzaNumber
        //Pizza pizza = fileHandler.readMenu(pizzaNumber);

        System.out.println("Størrelse?");
        String answer = scanner.nextLine();
        if (Objects.equals(answer.toLowerCase(), "normal")){
            Size size = NORMAL;
        } else if (Objects.equals(answer.toLowerCase(), "kids") ||
                    Objects.equals(answer.toLowerCase(), "kid") ||
                    Objects.equals(answer.toLowerCase(), "small")){
            Size size = KIDS;
        } else if (Objects.equals(answer.toLowerCase(), "family") ||
                    Objects.equals(answer.toLowerCase(), "large")){
            Size size = FAMILY;
        } else {
            System.out.println("Forkert input. Prøv igen.");
        }

        //orders.add(new Order(orderNumber,pizzaTime,pizza));

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
