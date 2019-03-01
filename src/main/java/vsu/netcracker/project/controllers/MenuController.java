package vsu.netcracker.project.controllers;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.*;
import vsu.netcracker.project.database.service.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;

/**
 * Controller class for handle menu requests
 * @author Андрей Стрижко
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class MenuController {

    /**
     * service for interaction with {@link Dishes} objects
     */
    @Autowired
    private DishesService dishesService;

    /**
     * service for interaction with {@link Orders} objects
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * service for interaction with {@link DishStatus} objects
     */
    @Autowired
    private DishStatusService dishStatusService;

    /**
     * service for interaction with {@link DishesFromOrder} objects
     */
    @Autowired
    private DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link RestaurantTable} objects
     */
    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private TypePaymentService typePaymentService;
    @Autowired
    private OrderStatusService orderStatusService;

    /**
     * cart for {@link Dishes}, which the {@link Staff} bought
     */
    private List<Dishes> cart = new ArrayList<>();
    private Object LocalDateTime;

    /**
     * get request, which gives the List of {@link Dishes} of determined type
     *
     * @param dishType - type of {@link Dishes}
     * @return returns the List of {@link Dishes} of determined type
     */
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

    /**
     * post request, which add the {@link Dishes} to the cart
     *
     * @param json - json object, which contains the id of {@link Dishes}
     */
    @PostMapping("/add")
    public void addDishToCart(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        Dishes dish = dishesService.getById(dishId);
        cart.add(dish);
    }

    /**
     * post request, which delete the {@link Dishes} from the cart
     *
     * @param json - json object, which contains the id of {@link Dishes}
     */
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

    /**
     * post request, which delete the {@link Dishes} of determined type
     *
     * @param json - json object, which contains the id of {@link Dishes}
     */
    @PostMapping("/cancel") // ToDo - проверить что работает отмена и подтверждение
    public void cancelDishes(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        Predicate<Dishes> deletingOnIdPredicate = d -> d.getId().equals(dishId);
        cart.removeIf(deletingOnIdPredicate);
    }

    /**
     * post request for confirm the order with {@link Dishes} in the cart
     *
     * @param json - json object, which contains the number of {@link Orders}
     */
    @PostMapping("/confirm")
    public void confirmOrder(@RequestBody Map<String, String> json) {
        Integer tableNumber = Integer.valueOf(json.get("tableNumber"));

        Orders order = new Orders();
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        order.setRestaurantTable(restaurantTable);
        order.setType("На месте");
        order.setDateOrders(Timestamp.valueOf(org.joda.time.LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss")));
        order.setTypePayment(typePaymentService.findByTitle("Наличными"));
        order.setOrderStatus(orderStatusService.findByTitle("Принят"));
        ordersService.addOrder(order);

        int preparingTimeInSecond = cart.stream()
                .mapToInt(dish -> dish.getPreparingTime().toLocalTime().toSecondOfDay())
                .sum();
        Time preparingTime = new Time(preparingTimeInSecond);
        DishStatus dishStatus = dishStatusService.findByTitle("В ожидании");
        for (Dishes dish : cart) {
            DishesFromOrder dishesFromOrder = new DishesFromOrder(preparingTime, dishStatus);
            dishesFromOrder.setDish(dish);
            dishesFromOrder.setOrder(order);
            dishesFromOrderService.addDishFromOrder(dishesFromOrder);
        }
    }
}