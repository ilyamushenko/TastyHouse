package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.dao.StatusesDAO;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.Statuses;
import vsu.netcracker.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("kitchen")
public class KitchenController {

    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private DishesFromOrderDAO dishesFromOrderDAO;
    @Autowired
    private StatusesDAO statusesDAO;

    @GetMapping
    public List<DishesFromOrder> showTables() {
        List<Orders> orders = ordersDAO.findAll();
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderDAO.findAll();
        List<DishesFromOrder> dishesFromOrdersKithen = new ArrayList<>();
        for (DishesFromOrder dishesFromOrder : dishesFromOrders
        ) {
            if (dishesFromOrder.getStatus().equals("В ожидании"))
                dishesFromOrdersKithen.add(dishesFromOrder);
        }
        /*
        List<Dishes> dishes = dishesDAO.findAll();
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderDAO.findAll();
        List<Orders> orders_kitchen = new ArrayList<Orders>();
        Map<Integer, List<Dishes>> map = ;
        for (Orders order: orders
             ) {
            if (order.getStatuses().getId() == 5)
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

    // ToDo - по хорошему надо менять этот метод с учетом добавленных кнопок
    @PostMapping("/status-change") // пока не работает
    public void changeStatus(@RequestBody Map<String, Object> json) {
        String status = (String) json.values().toArray()[0];
        String name = (String) json.values().toArray()[1];
        Integer tableNumber = (Integer) json.values().toArray()[2];
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
            if (dishFromOrder.getDish().getName().equals(name)) {
                if (!dishFromOrder.getStatus().equals("Готово")) {
                    dishFromOrder.setStatus(status);
                    dishesFromOrderDAO.save(dishFromOrder);
                    break;
                }
            }
        }
        Statuses statuses = statusesDAO.findByTitle("no_one_here");
        if (status.equals("Готово") && order.getDishesFromOrder().stream().anyMatch(s -> s.getStatus().equals("Готово"))) {
            statuses = statusesDAO.findByTitle("dish_is_ready");
        }
        else if (status.equals("Готовится") && order.getDishesFromOrder().stream().anyMatch(s -> s.getStatus().equals("Готовится"))) {
            statuses = statusesDAO.findByTitle("in_process_of_cooking");
        }
        else if (status.equals("В ожидании") && order.getDishesFromOrder().stream().allMatch(s -> s.getStatus().equals("В ожидании"))) {
            statuses = statusesDAO.findByTitle("no_one_here");
        }
        order.setStatuses(statuses);
        ordersDAO.save(order);
    }
}
