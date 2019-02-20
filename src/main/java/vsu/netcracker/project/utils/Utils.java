package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.RestaurantTable;

import java.sql.Timestamp;
import java.util.*;

public class Utils {

    public static Map<Integer, List<RestaurantTable>> convertListToMap(List<RestaurantTable> list, int step) {
        LinkedHashMap<Integer, List<RestaurantTable>> map = new LinkedHashMap<>();
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

    public static Map<Integer, Map<Integer, List<DishesFromOrder>>> convertListToMapWithMap(List<Orders> orders, int step) {
        LinkedHashMap<Integer, Map<Integer, List<DishesFromOrder>>> map = new LinkedHashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            LinkedHashMap<Integer, List<DishesFromOrder>> tempMap = new LinkedHashMap<>();
            for (int j = 0; j < orders.get(i).getDishesFromOrder().size(); j += step) {
                if (j + step < orders.get(i).getDishesFromOrder().size())
                    tempMap.put(j, orders.get(i).getDishesFromOrder().subList(j, j + step));
                else
                    tempMap.put(j, orders.get(i).getDishesFromOrder().subList(j, orders.get(i).getDishesFromOrder().size()));
            }
            map.put(i, tempMap);
        }
        return map;
    }

    public static List<Integer> getPercentageOfReady(List<Orders> orders) {
        List<Integer> listPercentageOfReady = new ArrayList<>();
        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        Integer nowSeconds = nowTimestamp.toLocalDateTime().toLocalTime().toSecondOfDay();
        for (Orders order : orders) {
            Integer orderSeconds = order.getDateOrders().toLocalDateTime().toLocalTime().toSecondOfDay();
            Integer orderTotalTimeSeconds = getTotalTimeOfCooking(order);
            Integer timestampDiff = Math.abs(nowSeconds - orderSeconds);
            if (timestampDiff >= orderTotalTimeSeconds)
                listPercentageOfReady.add(100);
            else
                listPercentageOfReady.add(100 * timestampDiff / orderTotalTimeSeconds);
        }
        return listPercentageOfReady;
    }

    private static Integer getTotalTimeOfCooking(Orders order) {
        int orderTotalTimeSeconds = 0;
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        for (DishesFromOrder dishFromOrder : dishesFromOrder) {
            orderTotalTimeSeconds += dishFromOrder.getDish().getPreparingTime().toLocalTime().toSecondOfDay();
        }
        return orderTotalTimeSeconds;
    }

    public static List<Float> getTotalPriceOfDishes(List<Orders> orders) {
        List<Float> listTotalPriceOfDishes = new ArrayList<>();
        for (Orders order : orders) {
            Float sum = 0.0f;
            List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
            for (DishesFromOrder dishFromOrder : dishesFromOrder) {
                sum += dishFromOrder.getDish().getPrice();
            }
            listTotalPriceOfDishes.add(sum);
        }
        return listTotalPriceOfDishes;
    }
}
