package vsu.netcracker.project.utils;

import java.util.Map;

/**
 * Class with support functions
 * for help to {@link vsu.netcracker.project.controllers.AdministratorController}
 * @author Илья Мущенко
 */
public class UtilsForAdministrator {

    /**
     * function, which finds key with max value of id in Map
     *
     * @param map - entry Map, with {@link vsu.netcracker.project.database.models.Dishes} id and index
     * @return returns max id in Map
     */
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

    /**
     * function, which finds count of pays for {@link vsu.netcracker.project.database.models.Dishes}
     *
     * @param map - entry Map, with {@link vsu.netcracker.project.database.models.Dishes} id and index
     * @return returns index, which according to max id in Map
     */
    public static int findMaxValueInMap(Map<Integer, Integer> map) {
        int max = 0;
        for (int val : map.values()) {
            if (val > max) max = val;
        }
        return max;
    }

}
