package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.models.RestaurantTable;
import vsu.netcracker.project.database.models.TableStatus;
import vsu.netcracker.project.database.service.DishStatusService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.OrderService;
import vsu.netcracker.project.database.service.RestaurantTableService;
import vsu.netcracker.project.database.service.TableStatusService;
import vsu.netcracker.project.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    private final DishesFromOrderService dishesFromOrderService;

    private final DishStatusService dishStatusService;

    private final TableStatusService tableStatusService;

    private final OrderService orderService;

    /**
     * injecting services with constructor
     */
    @Autowired
    public WaiterController(RestaurantTableService restaurantTableService,
                            DishesFromOrderService dishesFromOrderService,
                            DishStatusService dishStatusService,
                            TableStatusService tableStatusService,
                            OrderService orderService) {
        this.restaurantTableService = restaurantTableService;
        this.dishesFromOrderService = dishesFromOrderService;
        this.dishStatusService = dishStatusService;
        this.tableStatusService = tableStatusService;
        this.orderService = orderService;
    }

    /**
     * get request, which return List of {@link Order} on concrete {@link RestaurantTable}
     *
     * @param tableNumber - the number of concrete {@link RestaurantTable}
     * @return returns List of {@link Order} on concrete {@link RestaurantTable}
     */
    @GetMapping("/orders/{tableNumber}")
    public List<Object> showOrdersOnTable(@PathVariable Integer tableNumber) {
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        List<Float> listTotalPriceOfDishes = Utils.getTotalPriceOfDishes(restaurantTable.getOrdersList());
        return Arrays.asList(restaurantTable, listTotalPriceOfDishes);
    }

    /**
     * get request, which return List of {@link RestaurantTable}
     *
     * @return returns List of {@link RestaurantTable}
     */
    @GetMapping
    public List<RestaurantTable> showTables() {
        return restaurantTableService.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/orders/delivery-dish/{dishFromOrderId}")
    public void deliverDish(@PathVariable Integer dishFromOrderId) {
        DishesFromOrder dfo = dishesFromOrderService.getById(dishFromOrderId);
        DishStatus deliveredStatus = dishStatusService.findByTitle("Отнесено");
        dfo.setDishStatus(deliveredStatus);
        dishesFromOrderService.editDishFromOrder(dfo);
    }

    @PostMapping("/clear/{tableNumber}")
    public void clearTableStatus(@PathVariable Integer tableNumber) {
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        for (Order order : restaurantTable.getOrdersList()) {
            for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
                dishesFromOrderService.delete(dishFromOrder.getId());
            }
            orderService.delete(order.getId());
            order.getDishesFromOrder().clear();
        }
        restaurantTable.getOrdersList().clear();
        TableStatus tableStatus = tableStatusService.findByTitle("free");
        restaurantTable.setTableStatus(tableStatus);
        restaurantTableService.editTable(restaurantTable);
    }
}
