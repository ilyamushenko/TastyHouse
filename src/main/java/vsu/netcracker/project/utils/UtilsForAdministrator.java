package vsu.netcracker.project.utils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

public class UtilsForAdministrator {

    public static long findKeyWithMaxValueInMap(Map<Long, Integer> map) {
        int resultVal = 0;
        long resultKey = 0;
        for(Map.Entry<Long, Integer> item: map.entrySet()) {
            if(item.getValue() > resultVal) {
                resultKey = item.getKey();
                resultVal = item.getValue();
            }
        }
        return resultKey;
    }

    public static int findMaxValueInMap(Map<Long, Integer> map) {
        int max = 0;
        for(int val : map.values()) {
            if (val > max) max = val;
        }
        return max;
    }

}
