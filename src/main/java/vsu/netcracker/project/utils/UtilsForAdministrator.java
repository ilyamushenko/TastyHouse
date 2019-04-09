package vsu.netcracker.project.utils;

import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.FoodIngredients;
import vsu.netcracker.project.database.models.Ingredient;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.service.DishService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.FoodIngredientsService;
import vsu.netcracker.project.database.service.IngredientService;
import vsu.netcracker.project.database.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public static final int NEED_MONTHS = 3;
    public static final String MONDAY = "Понедельник";
    public static final String TUESDAY = "Вторник";
    public static final String WEDNESDAY = "Среда";
    public static final String THURSDAY = "Четверг";
    public static final String FRIDAY = "Пятница";
    public static final String SATURDAY = "Суббота";
    public static final String SUNDAY = "Воскресенье";

    private static String getNameOfDayOfWeekByNumber(int numberOfDay) {
        String nameOfDayOfWeek = MONDAY;
        switch (numberOfDay) {
            case 1:
                nameOfDayOfWeek = MONDAY;
                break;
            case 2:
                nameOfDayOfWeek = TUESDAY;
                break;
            case 3:
                nameOfDayOfWeek = WEDNESDAY;
                break;
            case 4:
                nameOfDayOfWeek = THURSDAY;
                break;
            case 5:
                nameOfDayOfWeek = FRIDAY;
                break;
            case 6:
                nameOfDayOfWeek = SATURDAY;
                break;
            case 7:
                nameOfDayOfWeek = SUNDAY;
                break;
        }
        return nameOfDayOfWeek;
    }

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
            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrdersByOrderId(order.getId());
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
        List<Order> orders = orderService.findByDateOrdersBetween(Timestamp.valueOf(LocalDateTime.now().minusMonths(NEED_MONTHS)), now);
        for (Order order : orders) {
            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrdersByOrderId(order.getId());
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
        resultMap.put(MONDAY, String.valueOf(dayOfWeeks.get(1) == null ? 0 : dayOfWeeks.get(1)));
        resultMap.put(TUESDAY, String.valueOf(dayOfWeeks.get(2) == null ? 0 : dayOfWeeks.get(2)));
        resultMap.put(WEDNESDAY, String.valueOf(dayOfWeeks.get(3) == null ? 0 : dayOfWeeks.get(3)));
        resultMap.put(THURSDAY, String.valueOf(dayOfWeeks.get(4) == null ? 0 : dayOfWeeks.get(4)));
        resultMap.put(FRIDAY, String.valueOf(dayOfWeeks.get(5) == null ? 0 : dayOfWeeks.get(5)));
        resultMap.put(SATURDAY, String.valueOf(dayOfWeeks.get(6) == null ? 0 : dayOfWeeks.get(6)));
        resultMap.put(SUNDAY, String.valueOf(dayOfWeeks.get(7) == null ? 0 : dayOfWeeks.get(7)));

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

    public static Map<String, String> getSalesForAllDishes(List<Integer> listOfDishesId, DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService) {
        Map<String, String> json = new HashMap<>();
        for (int id : listOfDishesId) {
            int salesInMonth = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)), id, orderService, dishesFromOrderService);
            json.put(dishService.getById(id).getName(), String.valueOf(salesInMonth));
        }
        return json;
    }

    private static long countDayOfWeeksInNeedMonths() {
        LocalDateTime before = LocalDateTime.now().minusMonths(NEED_MONTHS);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(2);
        return ChronoUnit.DAYS.between(before, tomorrow)/7;
    }

    public static Map<Ingredient, Double> getInformationOfIngredientsForTomorrowDay(DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService, IngredientService ingredientService, FoodIngredientsService foodIngredientsService) {
        Map<Integer, Long> idAndSells = getDishesIdAndSellsInNextDayOfWeek(dishesFromOrderService, orderService, dishService);
        Map<Ingredient, Double> ingredientLongMap = new HashMap<>();
        long countDays = countDayOfWeeksInNeedMonths();
        for(Map.Entry<Integer, Long> entry: idAndSells.entrySet()) {
            //ингредиенты в 1 блюдо
            List<FoodIngredients> ingredientsOfDish = foodIngredientsService.findFoodIngredientsByDish(entry.getKey());
                for(FoodIngredients foodIngredient : ingredientsOfDish) {
                    Ingredient temp = foodIngredient.getIngredient();
                    float quantity = foodIngredient.getQuantity();
                    ingredientLongMap.merge(temp, Math.ceil((quantity * entry.getValue()) / countDays), (a, b) -> a + b);
                }
        }
        return ingredientLongMap;
    }
    //за три месяца Map<idБлюда, сколько раз было продано>
    private static Map<Integer, Long> getDishesIdAndSellsInNextDayOfWeek(DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService) {
        System.out.println(dishService.count());
        Map<Integer, Long> dishIdCountOfSells = new HashMap<>();
        List<Dish> dishes = dishService.findAll();
        long countOfDishes = dishService.count();
        String tommorowDayOfWeek = getNameOfDayOfWeekByNumber(Timestamp.valueOf(LocalDateTime.now().plusDays(2)).toLocalDateTime().getDayOfWeek().getValue());
        for(Dish dish: dishes) {
            Map<String, String> dayOfWeeks = getInformationAboutDishDayOfTheWeek(dish.getId(), orderService, dishesFromOrderService);
            long sellsInDay = Long.parseLong(dayOfWeeks.get(tommorowDayOfWeek));
            dishIdCountOfSells.put(dish.getId(), sellsInDay);
        }
        return dishIdCountOfSells;
    }
//    public static getFullInformationAboutAllDishes() {
//
//    }
}
