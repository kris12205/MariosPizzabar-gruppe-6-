package MariosPizzaBar.service;

import java.util.ArrayList;
import MariosPizzaBar.ui.PizzabarUI;
import MariosPizzaBar.model.Order;

public class Stats {
    public static int Stats(ArrayList<Order> orders) {
        System.out.println("Påbegynder stats");
        int highestCount = 0;
        int mostBought = -1;

        for (int i = 0; i < orders.size(); i++) {
            int count = 0;

            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(i).getOrderNumber() == orders.get(j).getOrderNumber()) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                mostBought = orders.get(i).getOrderNumber();
            }
        }
        return mostBought;
    }
}
