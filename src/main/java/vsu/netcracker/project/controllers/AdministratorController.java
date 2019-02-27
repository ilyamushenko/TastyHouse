package vsu.netcracker.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.DishesService;
import vsu.netcracker.project.database.service.OrdersService;
import vsu.netcracker.project.database.service.impl.DishesFromOrderServiceImpl;
import vsu.netcracker.project.database.service.impl.DishesServiceImpl;
import vsu.netcracker.project.database.service.impl.OrdersServiceImpl;
import vsu.netcracker.project.utils.UtilsForAdministrator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handle admin requests
 * @author Илья Мущенко
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    /**
     * type of {@link Dishes}
     */
    private String type;

    /**
     * service for interaction with {@link Orders} objects
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * service for interaction with {@link vsu.netcracker.project.database.models.DishesFromOrder} objects
     */
    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link Dishes} objects
     */
    @Autowired
    private DishesService dishesService;

    /**
     * start request for admin
     *
     * @return returns Map which represent json object to client
     */
    @GetMapping
    public Map<String, String> please() {
        System.out.println("priiint");
        HashMap<String, String> json = new HashMap<>();
        json.put("1", "first");
        json.put("2", "second");
        json.put("3", "third");

        return json;
    }

    /**
     * post request for admin, which returns json object, containing info about most popular dish
     *
     * @param selectedType - selected type of statistic
     * @return returns Map which represent json object to client
     */
    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> selectedType) {
        type = (String) selectedType.values().toArray()[0];
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
        List<Orders> needOrders = ordersService.findByDateOrdersBetween(needTime, Timestamp.valueOf(LocalDateTime.now()));
        // из-за того что заказов нет - type_dish не пихается - тихо возникает исключение
        Map<Integer, Integer> counterOfDishes = new HashMap<>();
        for (Orders ord : needOrders) {
            try {
                Integer dish = dishesFromOrderService.findDishesFromOrderByOrder(ord.getId()).getDish().getId();
                if (counterOfDishes.keySet().contains(dish)) {
                    counterOfDishes.put(dish, counterOfDishes.get(dish) + 1);
                } else {
                    counterOfDishes.put(dish, 1);
                }
            } catch (Exception ignored) {
            }
        }
        Integer needId = UtilsForAdministrator.findKeyWithMaxValueInMap(counterOfDishes);
        Dishes dish = dishesService.getById(needId);
        Map<String, String> json = new HashMap<>();
        json.put("type_dish", dish.getTypeDish().getTitle());
        json.put("name", dish.getName());
        json.put("img_url", dish.getImgUrl());
        json.put("time", dish.getPreparingTime().toString());
        json.put("price", String.valueOf(dish.getPrice().toString()));
        json.put("count", String.valueOf(UtilsForAdministrator.findMaxValueInMap(counterOfDishes)));
        return json;
    }
}
