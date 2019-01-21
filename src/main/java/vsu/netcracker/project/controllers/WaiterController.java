package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class WaiterController {

    @Autowired
    private OrdersDAO ordersDAO;

    @GetMapping("/orders/{tableNumber}")
    public List<Object> showOrdersOnTable(@PathVariable Long tableNumber) {
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        Map<Integer, List<Dishes>> mapDishes = Utils.convertListToMap(order, 2);
        long percentOfReady = Utils.getPercentageOfReady(order);
        Float totalPriceOfDishes = Utils.getTotalPriceOfDishes(order);
        List<Object> list;
        list = Arrays.asList(mapDishes, order, percentOfReady, totalPriceOfDishes);
        return list;
    }

    @GetMapping
    public Map<Integer, List<Orders>> showTables() {
        List<Orders> orders = ordersDAO.findAll();
        Map<Integer, List<Orders>> mapOrders;
        mapOrders = Utils.convertListToMap(orders, 4);
        return mapOrders;
    }
}


