package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.models.RestaurantTable;

import java.sql.Timestamp;
import java.util.*;

/**
 * Class with support functions
 * for help to {@link vsu.netcracker.project.controllers.WaiterController}
 * @author Кушнеренко Виктор
 */
public class Utils {

    /**
     * function which converts List of {@link RestaurantTable} into Map with Lists of a certain size
     *
     * @param list - list of {@link RestaurantTable}, which need to convert
     * @param step - size of every List in result Map
     * @return returns Map from index and Lists of {@link RestaurantTable} of a certain size
     * @see Utils#convertListToMap(Order, int)
     * @see Utils#convertListToMapWithMap(List, int)
     */
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

    /**
     * function which converts {@link Dish} of object with class {@link Order} into Map with Lists of a certain size
     *
     * @param order - order, whose {@link Dish} need to convert
     * @param step - size of every List in result Map
     * @return returns Map from index and Lists of {@link Dish} of a certain size
     * @see Utils#convertListToMap(List, int)
     * @see Utils#convertListToMapWithMap(List, int)
     */
    public static Map<Integer, List<Dish>> convertListToMap(Order order, int step) {
        LinkedHashMap<Integer, List<Dish>> map = new LinkedHashMap<>();
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        List<Dish> list = new ArrayList<>();
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

    /**
     * function which converts List of {@link Order} into double Map of {@link DishesFromOrder} with Lists of a certain size
     *
     * @param orders - List of {@link Order} which need to convert
     * @param step - size of every List in result Map
     * @return returns double Map from index and Lists of {@link DishesFromOrder} of a certain size
     * @see Utils#convertListToMap(List, int)
     * @see Utils#convertListToMap(Order, int)
     */
    public static Map<Integer, Map<Integer, List<DishesFromOrder>>> convertListToMapWithMap(List<Order> orders, int step) {
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

    /**
     * function, which gives from List of {@link Order} List with percentage of ready every order
     *
     * @param orders - List of {@link Order}, from which we take percentages
     * @return returns List with percentage of ready every order
     * @see Utils#getTotalTimeOfCooking(Order)
     */
    public static List<Integer> getPercentageOfReady(List<Order> orders) {
        List<Integer> listPercentageOfReady = new ArrayList<>();
        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        Integer nowSeconds = nowTimestamp.toLocalDateTime().toLocalTime().toSecondOfDay();
        for (Order order : orders) {
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

    /**
     * function, which give from {@link Order} total time of cooking all of the {@link Dish} in it
     *
     * @param order - {@link Order}, from which we take total time of cooking
     * @return returns total time of cooking in seconds
     * @see Utils#getPercentageOfReady(List)
     */
    private static Integer getTotalTimeOfCooking(Order order) {
        int orderTotalTimeSeconds = 0;
        List<DishesFromOrder> dishesFromOrder = order.getDishesFromOrder();
        for (DishesFromOrder dishFromOrder : dishesFromOrder) {
            orderTotalTimeSeconds += dishFromOrder.getDish().getPreparingTime().toLocalTime().toSecondOfDay();
        }
        return orderTotalTimeSeconds;
    }

    /**
     * function, which give from List of {@link Order} total price of {@link Dish}
     *
     * @param orders - List of {@link Order}, from which we take total price of {@link Dish}
     * @return returns List of total price of {@link Dish}, which integer part means the count of rubles and
     * the float part means the count of kopeek
     */
    public static List<Float> getTotalPriceOfDishes(List<Order> orders) {
        List<Float> listTotalPriceOfDishes = new ArrayList<>();
        for (Order order : orders) {
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
