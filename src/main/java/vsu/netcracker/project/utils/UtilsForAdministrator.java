package vsu.netcracker.project.utils;

import org.springframework.stereotype.Service;
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
import vsu.netcracker.project.subModels.DishNameAndPrice;
import vsu.netcracker.project.subModels.IngredientForTomorrow;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Class with support functions
 * for help to {@link vsu.netcracker.project.controllers.AdministratorController}
 *
 * @author Илья Мущенко
 */
@Service
public class UtilsForAdministrator {

    public static final int     NEED_MONTHS  = 3;
    public static final String  MONDAY       = "Понедельник";
    public static final String  TUESDAY      = "Вторник";
    public static final String  WEDNESDAY    = "Среда";
    public static final String  THURSDAY     = "Четверг";
    public static final String  FRIDAY       = "Пятница";
    public static final String  SATURDAY     = "Суббота";
    public static final String  SUNDAY       = "Воскресенье";


    //ToDO: Придумать, как сделать сервисы @Autowired

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }


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

    /**
     * Give info about sells of ONE dish in need period
     * @param needTime need period
     * @param id - dish id
     * @param orderService ds
     * @param dishesFromOrderService dfos
     * @return count of sells
     */
    private static long getInformationAboutDishInPeriod(Timestamp needTime,
                                                       int id,
                                                       OrderService orderService,
                                                       DishesFromOrderService dishesFromOrderService) {

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Order> orders = orderService.findByDateOrdersBetween(needTime, now);
        long counter = 0L;
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

    /**
     * @param id - dish id
     * @param orderService - order service
     * @param dishesFromOrderService - dish from ord service
     * @return Map with information about sells of ONE dish by day of the week
     * in period {@value NEED_MONTHS}
     */
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

    /**
     *
     * @param map1 first map
     * @param map2 second map
     *
     * Method, which help to merge 2 maps
     */
    private static void mergeMaps(Map<String, String> map1, Map<String, String> map2) {
        for (Map.Entry<String, String> entry : map2.entrySet())
            map1.put(entry.getKey(), entry.getValue());
    }

    public static Map<String, String> getFullInfoAboutDish(int id,
                                                           OrderService orderService,
                                                           DishesFromOrderService dishesFromOrderService) {

        Map<String, String> json = new HashMap<>();

        long counterInDay = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusDays(1)),
                id, orderService, dishesFromOrderService);
        long counterInMonth = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusMonths(1)),
                id, orderService, dishesFromOrderService);
        long counterInYear = getInformationAboutDishInPeriod(Timestamp.valueOf(LocalDateTime.now().minusYears(1)),
                id, orderService, dishesFromOrderService);
        Map<String, String> popularDayOfWeek = getInformationAboutDishDayOfTheWeek(id, orderService, dishesFromOrderService);

        json.put("in_day", String.valueOf(counterInDay));
        json.put("in_week", String.valueOf(counterInMonth));
        json.put("in_year", String.valueOf(counterInYear));

        mergeMaps(json, popularDayOfWeek);

        return json;

    }

    /**
     *
     * @param listOfDishesId List, which contains all dishes id
     * @param dishesFromOrderService Dish from order service;
     * @param orderService Order service
     * @param dishService Dish service
     * @return Map, which contains information about sellers of ALL dishes in need period.
     */
    public static Map<String, Long> getSalesForAllDishes(List<Integer> listOfDishesId, Timestamp time, DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService) {
        Map<String, Long> json = new HashMap<>();
        for (int id : listOfDishesId) {
            long salesInMonth = getInformationAboutDishInPeriod(time, id, orderService, dishesFromOrderService);
            if(salesInMonth != 0) json.put(dishService.getById(id).getName(), salesInMonth);
        }

        return sortByValue(json);
    }

    /**
     * SubMethod which can count, how many day of weeks (for example: Monday, Friday or other)
     * in {@value NEED_MONTHS} months period
     * @return count of days
     */
    private static long countDayOfWeeksInNeedMonths() {
        LocalDateTime before = LocalDateTime.now().minusMonths(NEED_MONTHS);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return ChronoUnit.DAYS.between(before, tomorrow)/7;
    }

    /**
     *
     * @param dishesFromOrderService
     * @param orderService
     * @param dishService
     * @param ingredientService
     * @param foodIngredientsService
     * @return list of {@link IngredientForTomorrow}, which contains {@link Ingredient} and count
     * of this ingredient for tomorrow
     */
    public static List<IngredientForTomorrow> getInformationOfIngredientsForTomorrowDay(DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService, IngredientService ingredientService, FoodIngredientsService foodIngredientsService) {
        Map<Integer, Long> idAndSells = getDishesIdAndSellsInNextDayOfWeek(dishesFromOrderService, orderService, dishService);
        Map<Ingredient, Double> ingredientLongMap = new HashMap<>();
        List<IngredientForTomorrow> ingredientsForTomorrow = new ArrayList<>();
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
        ingredientLongMap.forEach((key, value) -> ingredientsForTomorrow.add(new IngredientForTomorrow(key, value)));
        return ingredientsForTomorrow;
    }
    //за три месяца Map<idБлюда, сколько раз было продано>

    /**
     * Method, which help to get {@link Map} with dish id and count of sells this dish for tomorrow
     * day of week for period = {@value NEED_MONTHS} months
     * @param dishesFromOrderService
     * @param orderService
     * @param dishService
     * @return Map with dish id, count of sells in period {@value NEED_MONTHS} months
     */
    private static Map<Integer, Long> getDishesIdAndSellsInNextDayOfWeek(DishesFromOrderService dishesFromOrderService, OrderService orderService, DishService dishService) {
        System.out.println(dishService.count());
        Map<Integer, Long> dishIdCountOfSells = new HashMap<>();
        List<Dish> dishes = dishService.findAll();
        String tomorrowDayOfWeek = getNameOfDayOfWeekByNumber(Timestamp.valueOf(LocalDateTime.now().plusDays(1)).toLocalDateTime().getDayOfWeek().getValue());
        for(Dish dish: dishes) {
            Map<String, String> dayOfWeeks = getInformationAboutDishDayOfTheWeek(dish.getId(), orderService, dishesFromOrderService);
            long sellsInDay = Long.parseLong(dayOfWeeks.get(tomorrowDayOfWeek));
            dishIdCountOfSells.put(dish.getId(), sellsInDay);
        }
        return dishIdCountOfSells;
    }

    public static List<DishNameAndPrice> getRevenueInPeriod(Timestamp period,
                                                            OrderService orderService,
                                                            DishesFromOrderService dishesFromOrderService,
                                                            DishService dishService) {

        List<DishNameAndPrice> list = new ArrayList<>();
        List<Dish> allDishes = dishService.findAll();
        allDishes.forEach(dish -> {
            long sells = getInformationAboutDishInPeriod(period, dish.getId(), orderService, dishesFromOrderService);
            float costPrice = (float) dish.getIngredients().stream().mapToDouble(ingredient ->
                    ingredient.getIngredient().getPrice()*ingredient.getQuantity()).sum();
            if((dish.getPrice() - costPrice) * sells != 0 || costPrice * sells != 0)
            list.add(new DishNameAndPrice(dish.getName(), dish.getPrice() * sells, costPrice * sells));
        });
        list.sort((o1, o2) -> (int) ((o1.getPrice() - o1.getPriceDerivedByIngredients()) - (o2.getPrice() - o2.getPriceDerivedByIngredients())));
        return list;
    }
}
