package vsu.netcracker.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/* ToDo - что нужно выводить на страничку:
    1. Заказы: номер столика, дата, статус
    2. Блюдо: название, суммарная цена заказа, возможно время
    3. DishesFromOrder: Возможно время, статус
    4. Statuses: title
*/

@Controller
public class WaiterController {

    @RequestMapping(path = "/orders")
    public String showOrdersOnTable(Map<String, Object> model) {
        return "WaiterOrdersOnTable";
    }

    @RequestMapping(path = "/")
    public String showTables(Map<String, Object> model) {
        return "WaiterTables";
    }
}
