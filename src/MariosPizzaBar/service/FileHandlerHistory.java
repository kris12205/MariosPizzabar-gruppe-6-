package MariosPizzaBar.service;
import MariosPizzaBar.model.*;
import java.io.*;

import java.util.ArrayList;
import MariosPizzaBar.util.ErrorHandler;




public class FileHandlerHistory {
    // definerer farver i ANSI
    private static String RED = "\u001B[31m";
    private static String GREEN = "\u001B[32m";
    private static String YELLOW = "\u001B[33m";
    private static String RESET = "\u001B[0m";

    private static final String HISTORIK = "src/MariosPizzaBar/Historik.csv";
    private static ArrayList<Order> pizzaHistory = new ArrayList<>();


    public ArrayList<Order> getPizzaHistory() {
        return pizzaHistory;
    }

    public void showHistory() {

        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORIK))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(GREEN + line + RESET);

            }
            reader.close();


        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }

    }

     public void loadHistory() {

       try (BufferedReader reader =
                    new BufferedReader(new FileReader(HISTORIK))) {

          String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int number = Integer.parseInt(data[2]);
                String name = data[3];
                int price = Integer.parseInt(data[4]);
                Size size = Size.valueOf(data[5]);
                Pizza pizza = new Pizza(number, name, price);
                Order order = new Order(pizza, size);
                pizzaHistory.add(order);
           }

        } catch (Exception e){
           ErrorHandler.handleArrayException(e);
           ErrorHandler.handlefileErrors(e);
           ErrorHandler.handleInputErrors(e);
        }
    }


    //Går igennem historik ArrayListen og skriver den til historik CSV filen
    public void writeToFileHistory() { //Har fjernet at metoden tager imod en ordre
        try {
            FileWriter fileWriter = new FileWriter(HISTORIK);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Order o : pizzaHistory) {
                bufferedWriter.write(o.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            ErrorHandler.handleErrors(e);
            ErrorHandler.handlefileErrors(e);
            ErrorHandler.handleArrayException(e);
        }

    }

}
