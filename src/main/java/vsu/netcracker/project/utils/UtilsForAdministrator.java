package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class with support functions
 * for help to {@link vsu.netcracker.project.controllers.AdministratorController}
 *
 * @author Илья Мущенко
 */
public class UtilsForAdministrator {

    /**
     * function, which finds key with max value of id in Map
     *
     * @param map - entry Map, with {@link Dish} id and index
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
     * function, which finds count of pays for {@link Dish}
     *
     * @param map - entry Map, with {@link Dish} id and index
     * @return returns index, which according to max id in Map
     */
    public static int findMaxValueInMap(Map<Integer, Integer> map) {
        int max = 0;
        for (int val : map.values()) {
            if (val > max) max = val;
        }
        return max;
    }

    //Сколько раз в день в неделю в месяц
    //В какие дни недели чаще всего покупают
    //В какое время чаще всего покупают
    //
    private static int getInformationAboutDishInPeriod(Timestamp needTime,
                                                       int id,
                                                       OrderService orderService,
                                                       DishesFromOrderService dishesFromOrderService) {

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Order> orders = orderService.findByDateOrdersBetween(needTime, now);
        int counter = 0;
        for (Order order : orders) {
            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrderByOrder(order.getId());
            if (temp != null) {
                for (DishesFromOrder dishesFromOrder : temp) {
                    if (id == dishesFromOrder.getDish().getId())
                        counter++;
                }
            }
        }
        return counter;
    }

    private static Map<String, String> getInformationAboutDishDayOfTheWeek(int id,
                                                                           OrderService orderService,
                                                                           DishesFromOrderService dishesFromOrderService) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        HashMap<Integer, Integer> dayOfWeeks = new HashMap<>();
        List<Order> orders = orderService.findByDateOrdersBetween(Timestamp.valueOf(LocalDateTime.now().minusMonths(3)), now);
        for (Order order : orders) {
            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrderByOrder(order.getId());
            if (temp != null) {
                for (DishesFromOrder dishesFromOrder : temp) {
                    if (id == dishesFromOrder.getDish().getId()) { //Сделать мапу где будут дни недели, а потом поиск макс
                        int day = order.getDateOrders().toLocalDateTime().getDayOfWeek().getValue();
                        Integer count = dayOfWeeks.get(day);
                        dayOfWeeks.put(day, count == null ? 1 : count + 1);
                    }
                }
            }
        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("Понедельник", String.valueOf(dayOfWeeks.get(1) == null ? 0 : dayOfWeeks.get(1)));
        resultMap.put("Вторник", String.valueOf(dayOfWeeks.get(2) == null ? 0 : dayOfWeeks.get(2)));
        resultMap.put("Среда", String.valueOf(dayOfWeeks.get(3) == null ? 0 : dayOfWeeks.get(3)));
        resultMap.put("Четверг", String.valueOf(dayOfWeeks.get(4) == null ? 0 : dayOfWeeks.get(4)));
        resultMap.put("Пятница", String.valueOf(dayOfWeeks.get(5) == null ? 0 : dayOfWeeks.get(5)));
        resultMap.put("Суббота", String.valueOf(dayOfWeeks.get(6) == null ? 0 : dayOfWeeks.get(6)));
        resultMap.put("Воскресенье", String.valueOf(dayOfWeeks.get(7) == null ? 0 : dayOfWeeks.get(7)));

        return resultMap;
    }

    private static void mergeMaps(Map<String, String> map1, Map<String, String> map2) {
        for (Map.Entry<String, String> entry : map2.entrySet())
            map1.put(entry.getKey(), entry.getValue());
    }

    public static Map<String, String> getFullInfoAboutDish(int id,
                                                           OrderService orderService,
                                                           DishesFromOrderService dishesFromOrderService) {

        Map<String, String> json = new HashMap<>();

        int counterInDay = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusDays(1)),
                id, orderService, dishesFromOrderService);
        int counterInMonth = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)),
                id, orderService, dishesFromOrderService);
        int counterInYear = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusYears(1)),
                id, orderService, dishesFromOrderService);
        Map<String, String> popularDayOfWeek = getInformationAboutDishDayOfTheWeek(id, orderService, dishesFromOrderService);

        json.put("in_day", String.valueOf(counterInDay));
        json.put("in_week", String.valueOf(counterInMonth));
        json.put("in_year", String.valueOf(counterInYear));

        mergeMaps(json, popularDayOfWeek);

        return json;

    }

//    public static getFullInformationAboutAllDishes() {
//
//    }
}
