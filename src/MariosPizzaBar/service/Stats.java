package MariosPizzaBar.service;

import java.util.ArrayList;
import MariosPizzaBar.ui.PizzabarUI;
import MariosPizzaBar.model.Order;

public class Stats {
    public static String Stats(ArrayList<Order> orders) {
        int highestCount = 0;
        String mostBought = "Tom";

        for (int i = 0; i < orders.size(); i++) {
            int count = 0;

            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(i).getPizza().getNumber() == orders.get(j).getPizza().getNumber()) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                mostBought = orders.get(i).getPizza().getName();
            }
        }
        return "Den mest populære pizza er: " + mostBought;
    }
}
