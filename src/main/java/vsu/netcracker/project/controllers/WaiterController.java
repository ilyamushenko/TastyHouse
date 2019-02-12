package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("waiter")
public class WaiterController {

    @Autowired
    private OrdersDAO ordersDAO;

    @GetMapping("/orders/{tableNumber}")
    public List<Object> showOrdersOnTable(@PathVariable Integer tableNumber) {
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        Map<Integer, List<Dishes>> mapDishes = Utils.convertListToMap(order, 2);
        long percentOfReady = Utils.getPercentageOfReady(order);
        Float totalPriceOfDishes = Utils.getTotalPriceOfDishes(order);
        List<Object> list;
        list = Arrays.asList(mapDishes, order, percentOfReady, totalPriceOfDishes, order.getDishesFromOrder());
        return list;
    }

    @GetMapping
    public Map<Integer, List<Orders>> showTables() {
        List<Orders> orders = ordersDAO.findAll();
        Map<Integer, List<Orders>> mapOrders;
        mapOrders = Utils.convertListToMap(orders, 4);
        return mapOrders;
    }

    /*
    ToDo - сделать запросы к бд с кухни, меню и админа (ниже описание)
    Вопрос: а откуда мне узнать с какого столика все это?
    При нажатии на "Купить" блюдо должно добавиться в заказ, при отжатии - удалиться из него
    На кухне при нажатии "Взять" и "Готово" блюдо меняет свой статус в заказе и статус всего заказа меняется
    У админа пока не смотрел
    */
}


