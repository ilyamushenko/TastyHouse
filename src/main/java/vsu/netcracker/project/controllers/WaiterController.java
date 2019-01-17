package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.utils.Utils;

import java.util.List;
import java.util.Map;

/* ToDo - что нужно выводить на страничку:
    1. Заказы: номер столика, дата, статус
    2. Блюдо: название, суммарная цена заказа, возможно время
    3. DishesFromOrder: Возможно время, статус
    4. Statuses: title
*/

@Controller
public class WaiterController {

    @Autowired
    private OrdersDAO ordersDAO;

    @RequestMapping(path = "/orders/{tableNumber}")
    public String showOrdersOnTable(@PathVariable Long tableNumber, Map<String, Object> model) {
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        long percentOfReady = Utils.getPercentageOfReady(order);
        model.put("order", order);
        model.put("percentOfReady", percentOfReady);
        return "WaiterOrdersOnTable";
    }

    @RequestMapping(path = "/")
    public String showTables(Map<String, Object> model) {
        List<Orders> orders = ordersDAO.findAll();
        Map<Integer, List<Orders>> mapOrders = Utils.convertOrdersListToMap(orders);
        model.put("orders", mapOrders);
        return "WaiterTables";
    }
}
