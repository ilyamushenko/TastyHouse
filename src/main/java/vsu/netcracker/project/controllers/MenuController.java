package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.*;
import vsu.netcracker.project.database.service.DishStatusService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.DishesService;
import vsu.netcracker.project.database.service.OrdersService;
import vsu.netcracker.project.database.service.impl.DishesFromOrderServiceImpl;
import vsu.netcracker.project.database.service.impl.DishesServiceImpl;
import vsu.netcracker.project.database.service.impl.OrdersServiceImpl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class MenuController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    @Autowired
    private DishStatusService dishStatusService;

    private List<Dishes> cart = new ArrayList<>();

    @GetMapping("menu/{dishType}")
    public List<Dishes> showTables(@PathVariable String dishType) {
        List<Dishes> dishes = dishesService.findAll();
        List<Dishes> selectedDish = new ArrayList<>();
        for (Dishes elem : dishes) {
            TypeDish elemType = elem.getTypeDish();
            if (elemType.getTitle().equals(dishType)) {
                selectedDish.add(elem);
            }
        }
        return selectedDish;
    }

    // ToDo - как достать номер столика, с которого заказал посетитель? И если он захочет вдруг пересесть на другой?
    @PostMapping("/buy")
    public void buyDish(@RequestBody Map<String, Object> json) {
        Integer id = (Integer) json.values().toArray()[0];
        Integer tableNumber = (Integer) json.values().toArray()[1];
        Orders order = ordersService.findByTableNumber(tableNumber);
        Dishes dish = dishesService.getById(id);
        DishStatus dishStatus = dishStatusService.findByTitle("В ожидании");
        Time realTime = dish.getPreparingTime();
        DishesFromOrder dishesFromOrder = new DishesFromOrder(realTime, dishStatus);
        order.getDishesFromOrder().add(dishesFromOrder);
        dish.getDishesFromOrder().add(dishesFromOrder);
        dishesFromOrder.setDish(dish);
        dishesFromOrder.setOrder(order);
        dishesFromOrderService.addDishFromOrder(dishesFromOrder);
    }

    @PostMapping("/remove")
    public void removeDish(@RequestBody Map<String, Object> json) {
        Integer id = (Integer) json.values().toArray()[0];
        Integer tableNumber = (Integer) json.values().toArray()[1];
        Orders order = ordersService.findByTableNumber(tableNumber);
        Dishes dish = dishesService.getById(id);
        DishesFromOrder dishesFromOrder = null;
        for (DishesFromOrder dishFromOrder : dish.getDishesFromOrder()) {
            if (dishFromOrder.getDish().equals(dish)) {
                dishesFromOrder = dishFromOrder;
            }
        }
        order.getDishesFromOrder().remove(dishesFromOrder);
        dish.getDishesFromOrder().remove(dishesFromOrder);
        dishesFromOrderService.delete(Objects.requireNonNull(dishesFromOrder).getId());
    }

    @PostMapping("/add")
    public void addDishToCart(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        Dishes dish = dishesService.getById(dishId);
        cart.add(dish);
    }

    @PostMapping("/delete")
    public void deleteDishFromCart(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        for (Dishes d : cart) {
            if (d.getId().equals(dishId)) {
                cart.remove(d);
                break;
            }
        }
    }
}