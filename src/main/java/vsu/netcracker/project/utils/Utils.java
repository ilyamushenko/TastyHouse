package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.Statuses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<Integer, List<Orders>> convertListToMap(List<Orders> list, int step) {
        LinkedHashMap<Integer, List<Orders>> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i += step) {
            if (i + step < list.size())
                map.put(i, list.subList(i, i + step));
            else
                map.put(i, list.subList(i, list.size()));
        }
        return map;
    }

    public static Map<Integer, List<Dishes>> convertListToMap(Orders order, int step) {
        LinkedHashMap<Integer, List<Dishes>> map = new LinkedHashMap<>();
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        List<Dishes> list = new ArrayList<>();
        for (DishesFromOrder dishFromOrder : dishesFromOrder) {
            list.add(dishFromOrder.getDish());
        }
        for (int i = 0; i < list.size(); i += step) {
            if (i + step < list.size())
                map.put(i, list.subList(i, i + step));
            else
                map.put(i, list.subList(i, list.size()));
        }
        return map;
    }

    // ToDo - сделать правильный подсчет времени для прогресс бара у официанта

    public static long getPercentageOfReady(Orders order) {
        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        long nowSeconds = nowTimestamp.toLocalDateTime().toLocalTime().toSecondOfDay();
        long orderSeconds = order.getDateOrders().toLocalDateTime().toLocalTime().toSecondOfDay();
        long orderTotalTimeSeconds = getTotalTimeOfCooking(order);
        long timestampDiff = Math.abs(nowSeconds - orderSeconds);
        return 100 * timestampDiff / orderTotalTimeSeconds;
    }

    public static Float getTotalPriceOfDishes(Orders order) {
        Float sum = 0.0f;
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        for (DishesFromOrder dishFromOrder : dishesFromOrder) {
            sum += dishFromOrder.getDish().getPrice();
        }
        return sum;
    }

    private static long getTotalTimeOfCooking(Orders order) {
        long orderTotalTimeSeconds = 0;
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        for (DishesFromOrder dishFromOrder : dishesFromOrder) {
            orderTotalTimeSeconds += dishFromOrder.getDish().getPreparingTime().toLocalTime().toSecondOfDay();
        }
        return orderTotalTimeSeconds;
    }
}
