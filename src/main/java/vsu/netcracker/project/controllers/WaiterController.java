package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.RestaurantTable;
import vsu.netcracker.project.database.service.OrdersService;
import vsu.netcracker.project.database.service.RestaurantTableService;
import vsu.netcracker.project.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("waiter")
public class WaiterController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @GetMapping("/orders/{tableNumber}")
    public List<Object> showOrdersOnTable(@PathVariable Integer tableNumber) {
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        List<Orders> listOrders = restaurantTable.getOrdersList();
        Map<Integer, Map<Integer, List<DishesFromOrder>>> mapOrders = Utils.convertListToMapWithMap(listOrders, 2);
        List<Integer> listPercentOfReady = Utils.getPercentageOfReady(listOrders);
        List<Float> listTotalPriceOfDishes = Utils.getTotalPriceOfDishes(listOrders);
        List<Object> list;
        list = Arrays.asList(restaurantTable, mapOrders, listPercentOfReady, listTotalPriceOfDishes);
        return list;
    }

    @GetMapping
    public Map<Integer, List<RestaurantTable>> showTables() {
        List<RestaurantTable> restaurantTables = restaurantTableService.findAll();
        Map<Integer, List<RestaurantTable>> mapRestaurantTable;
        mapRestaurantTable = Utils.convertListToMap(restaurantTables, 4);
        return mapRestaurantTable;
    }
}


