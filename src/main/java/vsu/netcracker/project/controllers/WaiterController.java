package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.utils.Utils;

import java.util.List;
import java.util.Map;

@Controller
public class WaiterController {
    @Autowired
    private OrdersDAO ordersDAO;

    @RequestMapping(path = "/orders/{tableNumber}")
    public String showOrdersOnTable(@PathVariable Long tableNumber, Map<String, Object> model) {
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        Map<Integer, List<Dishes>> mapDishes = Utils.convertListToMap(order, 2);
        long percentOfReady = Utils.getPercentageOfReady(order);
        Float totalPriceOfDishes = Utils.getTotalPriceOfDishes(order);
        model.put("order", order);
        model.put("dishes", mapDishes);
        model.put("percentOfReady", percentOfReady);
        model.put("totalPrice", totalPriceOfDishes);
        return "WaiterOrdersOnTable";
    }

    @RequestMapping(path = "/")
    public String showTables(Map<String, Object> model) {
        List<Orders> orders = ordersDAO.findAll();
        Map<Integer, List<Orders>> mapOrders = Utils.convertListToMap(orders, 4);
        model.put("orders", mapOrders);
        return "WaiterTables";
    }
}
