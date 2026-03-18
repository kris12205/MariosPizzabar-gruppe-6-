package MariosPizzaBar.service;

import MariosPizzaBar.model.*;


import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String FILE_NAME = "src/MariosPizzaBar/pizzamenu.csv";

    public ArrayList<Pizza> loadPizzaMenu() {

        ArrayList<Pizza> pizza = new ArrayList<>();

        try (
                BufferedReader reader =
                        new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int number = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);


            }


        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return pizza;
    }

    public void saveHistory(ArrayList<Pizza> pizza) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("src/MariosPizzaBar/Historik.csv"))) {

            for (Pizza pizza : pizza) {

                Pizza p = (Pizza) pizza;

                writer.write("PET," +
                        p.getNumber() + "," +
                        p.get.Name() + "," +
                        p.getPrice());

            }

            writer.newLine();
        }

    } catch(
    IOException e)

    {
        e.printStackTrace();
    }

    public void saveHistory(ArrayList<Pizza> pizza) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("src/MariosPizzaBar/Historik.csv"))) {

            for (Pizza pizza : pizza) {

                Pizza p = (Pizza) pizza;

                writer.write("PET," +
                        p.getNumber() + "," +
                        p.get.Name() + "," +
                        p.getPrice());

            }

            writer.newLine();
        }

    } catch(
    IOException e)

    {
        e.printStackTrace();
    }

}
