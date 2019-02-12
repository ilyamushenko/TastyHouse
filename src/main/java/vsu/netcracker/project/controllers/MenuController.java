package vsu.netcracker.project.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.TypeDish;
import vsu.netcracker.project.utils.HibernateUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class MenuController {

    @Autowired
    private DishesDAO dishesDAO;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private DishesFromOrderDAO dishesFromOrderDAO;

    @GetMapping("menu/{dishType}")
    public List<Dishes> showTables(@PathVariable String dishType) {
        List<Dishes> dishes = dishesDAO.findAll();
        List<Dishes> selectedDish = new ArrayList<>();
        for (Dishes elem : dishes) {
            TypeDish elemType = elem.getTypeDish();
            if (elemType.getTitle().equals(dishType)) {
                selectedDish.add(elem);
            }
        }
        return selectedDish;
    }

    // ToDo - исправить возникающую ошибку, связанную с classloader
    // ToDo - по хорошему надо менять этот метод с учетом добавленных кнопок, а то тут не совсем правильно
    // ToDo - как достать номер столика, с которого заказал посетитель? И если он захочет вдруг пересесть на другой?
    @PostMapping("/buy")
    public void buyDish(@RequestBody Map<String, Object> json) {
        String name = (String) json.values().toArray()[0];
        Integer tableNumber = (Integer) json.values().toArray()[1];
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        Dishes dish = dishesDAO.findByName(name);
        Time realTime = dish.getPreparingTime();
        DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, "В ожидании");
        order.getDishesFromOrder().add(dishesFromOrder);
        dish.getDishesFromOrder().add(dishesFromOrder);
        dishesFromOrderDAO.save(dishesFromOrder);
        dishesFromOrder.setDish(dish);
        dishesFromOrder.setOrder(order);
        ordersDAO.save(order);
        dishesDAO.save(dish);
    }

    // ToDo - по хорошему надо менять этот метод с учетом добавленных кнопок
    @PostMapping("/remove")
    public void removeDish(@RequestBody Map<String, Object> json) {
        String name = (String) json.values().toArray()[0];
        Integer tableNumber = (Integer) json.values().toArray()[1];
        Orders order = ordersDAO.findByTableNumber(tableNumber);
        Dishes dish = dishesDAO.findByName(name);
        Time realTime = dish.getPreparingTime();
        DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, "В ожидании");
        order.getDishesFromOrder().remove(dishesFromOrder);
        dish.getDishesFromOrder().remove(dishesFromOrder);
        dishesFromOrderDAO.delete(dishesFromOrder);
        ordersDAO.save(order);
        dishesDAO.save(dish);
    }
}
