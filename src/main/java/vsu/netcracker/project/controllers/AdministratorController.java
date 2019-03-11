package vsu.netcracker.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.DishService;
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
 * @author Илья Мущенко
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    /**
     * type of {@link Dish}
     */
    private String type;

    /**
     * service for interaction with {@link Order} objects
     */
    @Autowired
    private OrderService orderService;

    /**
     * service for interaction with {@link vsu.netcracker.project.database.models.DishesFromOrder} objects
     */
    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link Dish} objects
     */
    @Autowired
    private DishService dishService;

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

        List<Order> orders = orderService.findByDateOrdersBetween(needTime, now);
        List<List<DishesFromOrder>> dishesFromOrders = new ArrayList<>();
        for (Order ord : orders) {

            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrderByOrder(ord.getId());
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
        String needDish = (String) needDishMap.values().toArray()[0];
        System.out.println(needDish);
        int id = Integer.valueOf(needDish);
        Map<String, String > map =  UtilsForAdministrator.getFullInfoAboutDish(id, orderService, dishesFromOrderService);
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

        for(Dish dish: dishes) {
            String typeDish = dish.getTypeDish().getTitle();
            List<Map<String, String>> templist;
            if(json.containsKey(typeDish)) {
                templist = json.get(typeDish);
                willAdd = false;
            }
            else {
                templist = new ArrayList<>();
                willAdd = true;
            }
            Map<String, String> tempmap = new HashMap<>();
            tempmap.put("text", dish.getName());
            tempmap.put("value", String.valueOf(dish.getId()));
            templist.add(tempmap);
            if(willAdd) json.put(typeDish, templist);
        }
        System.out.println(json);
        return json;

    }

//    @GetMapping()
//    public Map<String, String> getInfoAboutAllDishes() {
//        UtilsForAdministrator.
//    }

}
