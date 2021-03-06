package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.models.RestaurantTable;
import vsu.netcracker.project.database.service.DishStatusService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.OrderService;
import vsu.netcracker.project.database.service.RestaurantTableService;
import vsu.netcracker.project.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

// ToDo - сделать "оплату потом" в отдельном потоке

/**
 * Controller class for handle waiter requests
 *
 * @author Кушнеренко Виктор
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("waiter")
public class WaiterController {

    /**
     * service for interaction with {@link RestaurantTable} objects
     */
    private final RestaurantTableService restaurantTableService;

    /**
     * injecting services with constructor
     */
    @Autowired
    public WaiterController(RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    @Autowired
    private DishStatusService dishStatusService;

    /**
     * get request, which return List of {@link Order} on concrete {@link RestaurantTable}
     *
     * @param tableNumber - the number of concrete {@link RestaurantTable}
     * @return returns List of {@link Order} on concrete {@link RestaurantTable}
     */
    @GetMapping("/orders/{tableNumber}")
    public List<Object> showOrdersOnTable(@PathVariable Integer tableNumber) {
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        List<Order> listOrders = restaurantTable.getOrdersList();
        listOrders.sort(Comparator.comparing(Order::getId));
        Map<Integer, Map<Integer, List<DishesFromOrder>>> mapOrders = Utils.convertListToMapWithMap(listOrders, 1);
        List<Integer> listPercentOfReady = Utils.getPercentageOfReady(listOrders);
        List<Float> listTotalPriceOfDishes = Utils.getTotalPriceOfDishes(listOrders);
        return Arrays.asList(restaurantTable, mapOrders, listPercentOfReady, listTotalPriceOfDishes);
    }

    /**
     * get request, which return List of {@link RestaurantTable}
     *
     * @return returns List of {@link RestaurantTable}
     */
    @GetMapping
    public List<Object> showTables() {
        List<RestaurantTable> restaurantTables = restaurantTableService.findAll(new Sort(Sort.Direction.ASC, "id"));
        System.out.println(restaurantTableService.findAll(new Sort(Sort.Direction.ASC, "id")));
        System.out.println(restaurantTableService.findAll(new Sort(Sort.Direction.ASC, "id")));
        Map<Integer, List<?>> mapRestaurantTable;
        mapRestaurantTable = Utils.convertListToMap(restaurantTables, 1);
        List<Object> listOfOrderStatus = new ArrayList<>();
        for (RestaurantTable restaurantTable : restaurantTables) {
            switch (restaurantTable.getTableStatus().getTitle()) {
                case "free":
                    listOfOrderStatus.add("free");
                    break;
                case "busy_need_to_bring":
                    if (restaurantTable.getOrdersList().stream().anyMatch(s -> s.getOrderStatus().getTitle().equals("paid"))) {
                        listOfOrderStatus.add("busy_need_to_bring_and_paid");
                    } else if (restaurantTable.getOrdersList().stream().allMatch(s -> s.getOrderStatus().getTitle().equals("not_paid"))) {
                        listOfOrderStatus.add("busy_need_to_bring_and_not_paid");
                    }
                    break;
                case "busy_need_attention":
                    if (restaurantTable.getOrdersList().stream().anyMatch(s -> s.getOrderStatus().getTitle().equals("paid"))) {
                        listOfOrderStatus.add("busy_need_attention_and_paid");
                    } else if (restaurantTable.getOrdersList().stream().allMatch(s -> s.getOrderStatus().getTitle().equals("not_paid"))) {
                        listOfOrderStatus.add("busy_need_attention_and_not_paid");
                    }
                    break;
                case "busy_no_need_attention":
                    if (restaurantTable.getOrdersList().stream().anyMatch(s -> s.getOrderStatus().getTitle().equals("paid"))) {
                        listOfOrderStatus.add("busy_no_need_attention_and_paid");
                    } else if (restaurantTable.getOrdersList().stream().allMatch(s -> s.getOrderStatus().getTitle().equals("not_paid"))) {
                        listOfOrderStatus.add("busy_no_need_attention_and_not_paid");
                    }
                    break;
            }
        }
        
        Map<Integer, List<?>> statusMap = Utils.convertListToMap(listOfOrderStatus, 1);
        return Arrays.asList(mapRestaurantTable, statusMap);
    }

    @GetMapping("/orders/delivery-dish/{dishFromOrderId}")
    public void deliverDish(@PathVariable Integer dishFromOrderId) {
        DishesFromOrder dfo = dishesFromOrderService.getById(dishFromOrderId);
        DishStatus deleveredStatus = dishStatusService.findByTitle("Отнесено");
        dfo.setDishStatus(deleveredStatus);
        dishesFromOrderService.editDishFromOrder(dfo);
    }
}
