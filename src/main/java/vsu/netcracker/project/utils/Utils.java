package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<Integer, List<Orders>> convertOrdersListToMap(List<Orders> list) {
        Map<Integer, List<Orders>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i+=4) {
            if (i + 4 < list.size())
                map.put(i + 1, list.subList(i, i + 4));
            else
                map.put(i + 1, list.subList(i, list.size() - 1));
        }
        return map;
    }
}
