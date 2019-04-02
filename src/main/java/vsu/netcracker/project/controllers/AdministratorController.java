package vsu.netcracker.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.service.DishService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.OrderService;
import vsu.netcracker.project.utils.UtilsForAdministrator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handle admin requests
 *
 * @author Илья Мущенко
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdministratorController {
    //5555 5555 5555 4444 01/23 123 36101 ---- для оплаты по карте

    /**
     * type of {@link Dish}
     */
    private String type;

    /**
     * service for interaction with {@link Order} objects
     */
    private final OrderService orderService;

    /**
     * service for interaction with {@link vsu.netcracker.project.database.models.DishesFromOrder} objects
     */
    private final DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link Dish} objects
     */
    private final DishService dishService;

    /**
     * injecting services with constructor
     */
    @Autowired
    public AdministratorController(OrderService orderService, DishesFromOrderService dishesFromOrderService, DishService dishService) {
        this.orderService = orderService;
        this.dishesFromOrderService = dishesFromOrderService;
        this.dishService = dishService;
    }

    /**
     * post request for admin, which returns json object, containing info about most popular dish
     *
     * @param selectedType - selected type of statistic
     * @return returns Map which represent json object to client
     */
    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> selectedType) {
        type = selectedType.get("statistic_type");
        System.out.println(type);

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp needTime = null;
        switch (type) {
            case "day": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
                break;
            }
            case "month": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
                break;
            }
            case "year": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusYears(20));
                break;
            }
        }

        List<Order> orders = orderService.findByDateOrdersBetween(needTime, now);
        List<List<DishesFromOrder>> dishesFromOrders = new ArrayList<>();
        for (Order ord : orders) {

            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrdersByOrderId(ord.getId());
            if (temp != null) {
                dishesFromOrders.add(temp);
            }
        }
        Map<Integer, Integer> counterOfDishes = new HashMap<>();

        for (List<DishesFromOrder> dfoList : dishesFromOrders) {

            for (DishesFromOrder dfo : dfoList) {
                int dish = dfo.getDish().getId();
                if (counterOfDishes.keySet().contains(dish)) {
                    counterOfDishes.put(dish, counterOfDishes.get(dish) + 1);
                } else {
                    counterOfDishes.put(dish, 1);
                }
            }
        }

        Integer needId = UtilsForAdministrator.findKeyWithMaxValueInMap(counterOfDishes);
        double count = UtilsForAdministrator.findMaxValueInMap(counterOfDishes);

        Dish dish = dishService.getById(needId);
        Map<String, String> json = new HashMap<>();
        json.put("type_dish", dish.getTypeDish().getTitle());
        json.put("name", dish.getName());
        json.put("img_url", dish.getImgUrl());
        json.put("time", dish.getPreparingTime().toString());
        json.put("price", String.valueOf(dish.getPrice() * count));
        json.put("count", String.valueOf((int) count));
        return json;
    }

    @PostMapping("/inform")
    public Map<String, String> getDishesInfo(@RequestBody Map<String, String> needDishMap) {
        String needDish = needDishMap.get("needDish");
        System.out.println(needDish);
        int id = Integer.valueOf(needDish);
        Map<String, String> map = UtilsForAdministrator.getFullInfoAboutDish(id, orderService, dishesFromOrderService);
        Dish dish = dishService.getById(Integer.valueOf(needDish));
        map.put("name", dish.getName());
        map.put("img", dish.getImgUrl());
        map.put("mass", dish.getMass());
        map.put("time", String.valueOf(dish.getPreparingTime()));
        map.put("type", String.valueOf(dish.getTypeDish().getTitle()));
        map.put("price", String.valueOf(dish.getPrice()));
        return map;
    }

    @GetMapping("/inform")
    public Map<String, List<Map<String, String>>> getAllDishes() {
        //Map<ТипБлюда, Map<НазваниеБлюда, НазваниеДляОтправкиОбратно(ID)>>


        List<Dish> dishes = dishService.findAll();
        Map<String, List<Map<String, String>>> json = new HashMap<>();
        boolean willAdd;

        for (Dish dish : dishes) {
            String typeDish = dish.getTypeDish().getTitle();
            List<Map<String, String>> tempList;
            if (json.containsKey(typeDish)) {
                tempList = json.get(typeDish);
                willAdd = false;
            } else {
                tempList = new ArrayList<>();
                willAdd = true;
            }
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("text", dish.getName());
            tempMap.put("value", String.valueOf(dish.getId()));
            tempList.add(tempMap);
            if (willAdd)
                json.put(typeDish, tempList);
        }
        System.out.println(json);
        return json;
    }

//    @GetMapping()
//    public Map<String, String> getInfoAboutAllDishes() {
//        UtilsForAdministrator.
//    }

}
