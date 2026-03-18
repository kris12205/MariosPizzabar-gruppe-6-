package MariosPizzaBar.util;

import MariosPizzaBar.model.Order;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderSorter {

    public static void sortByTime(ArrayList<Order> orders) {
        orders.sort(Comparator.comparing(Order::getTime));
    }

    public static void sortByOrderNumber(ArrayList<Order> orders) {
        orders.sort(Comparator.comparing(Order::getOrderNumber));
    }


}
