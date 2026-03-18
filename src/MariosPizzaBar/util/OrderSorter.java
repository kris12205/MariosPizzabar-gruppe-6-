package MariosPizzaBar.util;

import MariosPizzaBar.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderSorter {

    public static void sortByTime(ArrayList<Order> orders) {
        Collections.sort(orders, Comparator.comparing(Order::getTime));
    }

    public static void sortByOrderNumber(ArrayList<Order> orders) {
        Collections.sort(orders, Comparator.comparing(Order::getOrderNumber));
    }


}
