package MariosPizzaBar.ui;

import MariosPizzaBar.model.Order;
import MariosPizzaBar.model.Pizza;
import MariosPizzaBar.model.Size;
import MariosPizzaBar.service.*;
import MariosPizzaBar.util.ErrorHandler;
import MariosPizzaBar.util.*;
import MariosPizzaBar.model.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import static MariosPizzaBar.model.Size.*;
import static MariosPizzaBar.service.OrderHandler.*;
import static MariosPizzaBar.service.Stats.Stats;

public class PizzabarUI {
    private static Scanner scanner = new Scanner(System.in);
    private static FileHandlerMenu fileHandlerMenu = new FileHandlerMenu();
    private static FileHandlerHistory fileHandlerHistory = new FileHandlerHistory();



    // skal kalde de andre metoder
    public static void start() {

        System.out.println(Color.GREEN + """
        
        Velkommen til Marios PizzaBar System!
        -------------------------------------""" + Color.RESET);

        boolean running = true;

        //Indlæser Pizza menuen
        fileHandlerMenu.loadPizzaMenu();

        //Indlæser bestillingslisten
        getOrders();
        showOrders();

        while (running) {
            System.out.println("\nVælg en mulighed ved at taste et tal fra 1-9\n" +
                    "1. Vis menukortet\n2. Vis drikkevare\n3. Vis ordreliste\n4. Tilføj ordre\n5. Sorter ordrelisten" +
                    "\n6. Færdiggør ordre\n7. Vis historik\n8. Vis statistik\n9. Luk programmet");
            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        showMenu();
                        break;
                    case 2:
                        Drinks.printDrinks();
                        break;
                    case 3:
                        showOrders();
                        break;
                    case 4:
                        addOrder();
                        // scanner.nextLine();
                        break;
                    case 5:
                        sortOrderList(scanner);
                        scanner.nextLine();
                        break;
                    case 6:
                        concludeOrder();
                        // scanner.nextLine();
                        break;
                    case 7:
                        showHistory();
                        break;
                    case 8:
                        showStats();
                        break;
                    case 9:
                        scanner.close();
                        running = false;
                        System.out.println(Color.YELLOW + "Tak for nu." + Color.RESET);
                        break;
                    default:
                        System.out.println(Color.RED + "Uventet fejl. Tast et tal mellem 1-9." + Color.RESET);

                }
            } catch (Exception e){
               ErrorHandler.handleErrors(e);
               ErrorHandler.handleInputErrors(e);
            }
        }
    }

    // skal printe menukortet fra pizzamenu.csv
    public static void showMenu() {
        for (Pizza pizza : fileHandlerMenu.getPizzas()) {
            System.out.print(Color.GREEN + pizza + "\n" + Color.RESET);
        }
    }


    // skal læse og printe historik.csv
    private static void showHistory() {
        if(fileHandlerHistory.getPizzaHistory().isEmpty()) {
            System.out.println(Color.RED + "Historikken er tom" + Color.RESET);
        } else {
            fileHandlerHistory.showHistory();
        }

    }

    //Kalder stats metode
    private static void showStats() {
        if(fileHandlerHistory.getPizzaHistory().isEmpty()) {
            System.out.println(Color.RED + "Historikken er tom" + Color.RESET);
        } else {
            System.out.println(Color.YELLOW + Stats(fileHandlerHistory.getPizzaHistory()) + Color.RESET);
        }

    }
}
