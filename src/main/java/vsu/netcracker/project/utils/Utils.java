package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Orders;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<Integer, List<Orders>> convertOrdersListToMap(List<Orders> list) {
        Map<Integer, List<Orders>> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i+=4) {
            if (i + 4 < list.size())
                map.put(i + 1, list.subList(i, i + 4));
            else
                map.put(i + 1, list.subList(i, list.size()));
        }
        return map;
    }

    public static long getPercentageOfReady(Orders order) {
        Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
        long timestampDiff = nowTimestamp.getTime() - order.getDateOrders().getTime();
        return 100*timestampDiff/order.getDishesFromOrder().getRealTime().getTime();
    }
}
