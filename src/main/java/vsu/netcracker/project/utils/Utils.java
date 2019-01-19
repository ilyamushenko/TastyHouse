package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.Orders;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<Integer, List<?>> convertListToMap(List<?> list, int step) {
        Map<Integer, List<?>> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i += step) {
            if (i + step < list.size())
                map.put(i + 1, list.subList(i, i + step));
            else
                map.put(i + 1, list.subList(i, list.size()));
        }
        return map;
    }

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
        List<Dishes> dishes = order.getDishesFromOrder().getDishesSet();
        for (Dishes dish : dishes) {
            sum += dish.getPrice();
        }
        return sum;
    }

    private static long getTotalTimeOfCooking(Orders order) {
        long orderTotalTimeSeconds = 0;
        List<Dishes> dishes = order.getDishesFromOrder().getDishesSet();
        for (Dishes dish : dishes) {
            orderTotalTimeSeconds += dish.getPreparingTime().toLocalTime().toSecondOfDay();
        }
        return orderTotalTimeSeconds;
    }
}
