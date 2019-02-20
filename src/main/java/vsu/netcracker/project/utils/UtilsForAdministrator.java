package vsu.netcracker.project.utils;

import java.util.Map;

public class UtilsForAdministrator {

    public static Integer findKeyWithMaxValueInMap(Map<Integer, Integer> map) {
        int resultVal = 0;
        Integer resultKey = 0;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() > resultVal) {
                resultKey = item.getKey();
                resultVal = item.getValue();
            }
        }
        return resultKey;
    }

    public static int findMaxValueInMap(Map<Integer, Integer> map) {
        int max = 0;
        for (int val : map.values()) {
            if (val > max) max = val;
        }
        return max;
    }

}
