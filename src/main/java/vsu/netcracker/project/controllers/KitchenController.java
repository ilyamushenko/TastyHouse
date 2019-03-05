package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.*;
import vsu.netcracker.project.database.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handle kitchen requests
 * @author Алина Попова
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("kitchen")
public class KitchenController {

    /**
     * service for interaction with {@link DishesFromOrder} objects
     */
    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link Order} objects
     */
    @Autowired
    private OrderService orderService;

    /**
     * service for interaction with {@link Dish} objects
     */
    @Autowired
    private DishService dishService;

    /**
     * service for interaction with {@link OrderStatus} objects
     */
    @Autowired
    private OrderStatusService orderStatusService;

    /**
     * service for interaction with {@link DishStatus} objects
     */
    @Autowired
    private DishStatusService dishStatusService;

    /**
     * service for interaction with {@link TableStatus} objects
     */
    @Autowired
    private TableStatusService tableStatusService;

    /**
     * service for interaction with {@link RestaurantTable} objects
     */
    @Autowired
    private RestaurantTableService restaurantTableService;

    /**
     * function, which changes the {@link TableStatus} of {@link RestaurantTable}
     *
     * @param restaurantTable - the {@link RestaurantTable}, which we need to update
     * @see KitchenController#changeDishStatus(Map)
     * @see KitchenController#changeOrderStatus(Order)
     */
    private void changeTableStatus(RestaurantTable restaurantTable) {
        TableStatus tableStatus = null;
        if (restaurantTable.getOrdersList().stream().anyMatch(s -> s.getOrderStatus().getTitle().equals("dish_is_ready"))) {
            tableStatus = tableStatusService.findByTitle("dish_is_ready");
        } else if (restaurantTable.getOrdersList().stream().anyMatch(s -> s.getOrderStatus().getTitle().equals("in_process_of_cooking"))) {
            tableStatus = tableStatusService.findByTitle("in_process_of_cooking");
        } else if (restaurantTable.getOrdersList().stream().allMatch(s -> s.getOrderStatus().getTitle().equals("no_one_here"))) {
            tableStatus = tableStatusService.findByTitle("no_one_here");
        }
        restaurantTable.setTableStatus(tableStatus);
        restaurantTableService.editTable(restaurantTable);
    }

    /**
     * function, which changes the {@link OrderStatus} of {@link Order}
     *
     * @param order - the {@link Order}, which we need to update
     * @see KitchenController#changeDishStatus(Map)
     * @see KitchenController#changeTableStatus(RestaurantTable)
     */
    private void changeOrderStatus(Order order) {
        OrderStatus orderStatus = null;
        if (order.getDishesFromOrder().stream().anyMatch(s -> s.getDishStatus().getTitle().equals("Готово"))) {
            orderStatus = orderStatusService.findByTitle("dish_is_ready");
        } else if (order.getDishesFromOrder().stream().anyMatch(s -> s.getDishStatus().getTitle().equals("Готовится"))) {
            orderStatus = orderStatusService.findByTitle("in_process_of_cooking");
        } else if (order.getDishesFromOrder().stream().allMatch(s -> s.getDishStatus().getTitle().equals("В ожидании"))) {
            orderStatus = orderStatusService.findByTitle("no_one_here");
        }
        order.setOrderStatus(orderStatus);
        orderService.editOrder(order);
    }

    /**
     * start request for kitchen
     *
     * @return returns List of {@link DishesFromOrder}, which have {@link DishStatus} equal to 'В ожидании'
     */
    @GetMapping
    public List<DishesFromOrder> showTables() {
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderService.findAll();
        List<DishesFromOrder> dishesFromOrdersKithen = new ArrayList<>();
        for (DishesFromOrder dishesFromOrder : dishesFromOrders
        ) {
            if (!dishesFromOrder.getDishStatus().getTitle().equals("Готово"))
                dishesFromOrdersKithen.add(dishesFromOrder);
        }
        dishesFromOrdersKithen.sort((p1, p2)->p1.getTimeOrder().compareTo(p2.getTimeOrder()));
        return dishesFromOrdersKithen;
    }

    /**
     * post request for changing {@link DishStatus}
     *
     * @param json - json object, which contains from status, id and tableNumber of {@link Order}
     * @see KitchenController#changeOrderStatus(Order)
     * @see KitchenController#changeTableStatus(RestaurantTable)
     */
    @PostMapping("/status-change")
    public void changeDishStatus(@RequestBody Map<String, Object> json) {
        String status = (String) json.values().toArray()[0];
        Integer id = (Integer) json.values().toArray()[1];
        Integer tableNumber = (Integer) json.values().toArray()[2];
        //Order orders = orderService.findById(id);
        Order order = orderService.findByTableNumber(tableNumber);
        RestaurantTable restaurantTable = order.getRestaurantTable();
        DishStatus dishStatus = dishStatusService.findByTitle(status);
        DishesFromOrder dishesFromOrder = dishesFromOrderService.getById(id);
        dishesFromOrder.setDishStatus(dishStatus);
        dishesFromOrderService.editDishFromOrder(dishesFromOrder);
        /*
        for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
            if (dishFromOrder.getDish().getId().equals(id)) {
                if (!dishFromOrder.getDishStatus().getTitle().equals("Готово")) {
                    dishFromOrder.setDishStatus(dishStatus);
                    dishesFromOrderService.editDishFromOrder(dishFromOrder);
                    break;
                }
            }
        }*/
        changeOrderStatus(order);
        changeTableStatus(restaurantTable);
    }
}