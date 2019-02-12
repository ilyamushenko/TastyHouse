package vsu.netcracker.project.controllers;


import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.utils.UtilsForAdministrator;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    private String type;
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private DishesFromOrderDAO dishesFromOrderDAO;
    @Autowired
    private DishesDAO dishesDAO;

    @GetMapping
    public Map<String, String> please() {
        System.out.println("priiint");
        HashMap<String, String> json = new HashMap<>();
        json.put("1", "first");
        json.put("2", "second");
        json.put("3", "third");

        return json;
    }

    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> selectedType){
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
        List<Orders> needOrders = ordersDAO.findByDateOrdersBetween(needTime, Timestamp.valueOf(LocalDateTime.now()));
        Map<Long, Integer> counterOfDishes = new HashMap<>();
        for(Orders ord : needOrders) {
            try {
                long dish = dishesFromOrderDAO.findDishesFromOrderByOrder(ord).getDishesSet().getId();
                if (counterOfDishes.keySet().contains(dish)) {
                    counterOfDishes.put(dish, counterOfDishes.get(dish) + 1);
                } else {
                    counterOfDishes.put(dish, 1);
                }
            } catch (Exception ignored) {}
        }
        long needId = UtilsForAdministrator.findKeyWithMaxValueInMap(counterOfDishes);
        Dishes dish = dishesDAO.findById(needId);
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
