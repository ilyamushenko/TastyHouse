package vsu.netcracker.project.controllers;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.utils.HibernateUtil;
import vsu.netcracker.project.utils.Utils;

import java.sql.Time;
import java.util.*;



import java.util.Arrays;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("kitchen")
public class KitchenController {

    @Autowired
    private DishesDAO dishesDAO;
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private DishesFromOrderDAO dishesFromOrderDAO;

    @GetMapping
    public List<DishesFromOrder> showTables() {
        List<Orders> orders = ordersDAO.findAll();
        List<DishesFromOrder> dishesFromOrders = dishesFromOrderDAO.findAll();
        List<DishesFromOrder> dishesFromOrdersKithen = new ArrayList<>();
        for (DishesFromOrder dishesFromOrder: dishesFromOrders
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
            if (dishesFromOrder.getOrdersSet().equals(orders_kitchen))
                orders_kitchen.add(order);
        }*/
        //Map<Integer, List<Dishes>> mapOrders;


        //Map<Integer, List<Dishes>> mapOrders;
        //mapOrders = Utils.convertListToMap(dishesFromOrdersKithen, 4);

        return dishesFromOrdersKithen;
    }

    // ToDo - у меня тут мысли возникают: а как найти нужное блюдо, если 2 одинаковых будет в заказе? (ну только если по id, но как его получить?)

    /* @PostMapping("/status-change")
    public void changeStatus(@RequestBody Map<String, Object> json) {
        /* передаем блюдо и заказ, к которому он относится (или нет смысла передавать заказ?) - меняем статус блюда
         в заказе - меняем статус заказа
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            String status = (String) json.values().toArray()[0];
            for (DishesFromOrder dishFromOrder : order.getDishesFromOrder()) {
                if (dishFromOrder.getDishesSet().getName().equals(name)) {
                    dish = dishFromOrder.getDishesSet();
                    break;
                }
            }
            Time realTime = dish.getPreparingTime();
            DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, "В ожидании",
                    session.load(Orders.class, Objects.requireNonNull(order).getId()),
                    session.load(Dishes.class, dish.getId()));
            session.delete(dishesFromOrder);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}
