package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.OrderStatus;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.service.DishStatusService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.OrderStatusService;
import vsu.netcracker.project.database.service.OrdersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("kitchen")
public class KitchenController {

    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private DishStatusService dishStatusService;

    @GetMapping
    public List<DishesFromOrder> showTables() {
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderService.findAll();
        List<DishesFromOrder> dishesFromOrdersKithen = new ArrayList<>();
        for (DishesFromOrder dishesFromOrder : dishesFromOrders
        ) {
            if (dishesFromOrder.getDishStatus().getTitle().equals("В ожидании"))
                dishesFromOrdersKithen.add(dishesFromOrder);
        }
        /*
        List<Dishes> dishes = dishesDAO.findAll();
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderDAO.findAll();
        List<Orders> orders_kitchen = new ArrayList<Orders>();
        Map<Integer, List<Dishes>> map = ;
        for (Orders order: orders
             ) {
            if (order.getOrderStatus().getId() == 5)
                orders_kitchen.add(order);
order.getDishesFromOrder()
            map = Utils.convertListToMap(order, 2);
        }*/
        /*
        for (DishesFromOrder dishesFromOrder: dishesFromOrders
        ) {
            if (dishesFromOrder.getOrder().equals(orders_kitchen))
                orders_kitchen.add(order);
        }*/
        //Map<Integer, List<Dishes>> mapOrders;


        //Map<Integer, List<Dishes>> mapOrders;
        //mapOrders = Utils.convertListToMap(dishesFromOrdersKithen, 4);

        return dishesFromOrdersKithen;
    }

    // ToDo - изменить этот метод с учетом добавленных кнопок
    @PostMapping("/status-change") // ToDo - после апдейта заказ на дно уходит
    public void changeStatus(@RequestBody Map<String, Object> json) {
        String status = (String) json.values().toArray()[0];
        Integer id = (Integer) json.values().toArray()[1];
        Integer tableNumber = (Integer) json.values().toArray()[2];
        Orders order = ordersService.findByTableNumber(tableNumber);
        DishStatus dishStatus = dishStatusService.findByTitle(status);
        for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
            if (dishFromOrder.getDish().getId().equals(id)) {
                if (!dishFromOrder.getDishStatus().getTitle().equals("Готово")) {
                    dishFromOrder.setDishStatus(dishStatus);
                    dishesFromOrderService.editDishFromOrder(dishFromOrder);
                    break;
                }
            }
        }
        OrderStatus statuses = null;
        if (order.getDishesFromOrder().stream().anyMatch(s -> s.getDishStatus().getTitle().equals("Готово"))) {
            statuses = orderStatusService.findByTitle("dish_is_ready");
        } else if (order.getDishesFromOrder().stream().anyMatch(s -> s.getDishStatus().getTitle().equals("Готовится"))) {
            statuses = orderStatusService.findByTitle("in_process_of_cooking");
        } else if (order.getDishesFromOrder().stream().allMatch(s -> s.getDishStatus().getTitle().equals("В ожидании"))) {
            statuses = orderStatusService.findByTitle("no_one_here");
        }
        order.setOrderStatus(statuses);
        ordersService.editOrder(order);
        // ToDo - почему-то после смены статуса появляются "мертвые" кортежи
    }
}
