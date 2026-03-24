package MariosPizzaBar.util;

import MariosPizzaBar.model.Order;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderSorter {

    //Sorter ordrelisten efter tid
    public static void sortByTime(ArrayList<Order> orders) {
        orders.sort(Comparator.comparing(Order::getTime));
    }

    //Sorter ordrelisten efter ordrenummer
    public static void sortByOrderNumber(ArrayList<Order> orders) {
        orders.sort(Comparator.comparing(Order::getOrderNumber));
    }

    //Sorter ordrelisten efter pizza navn
    public static void sortByOrderName(ArrayList<Order> orders) {
        orders.sort(Comparator.comparing(Order::getPizzaName));
    }


}
