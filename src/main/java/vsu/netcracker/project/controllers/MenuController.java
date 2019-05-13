package vsu.netcracker.project.controllers;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.*;
import vsu.netcracker.project.database.models.enums.StatusDish;
import vsu.netcracker.project.database.service.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Controller class for handle menu requests
 * @author Андрей Стрижко
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class MenuController {

    /**
     * service for interaction with {@link Dish} objects
     */
    @Autowired
    private DishService dishService;

    /**
     * service for interaction with {@link Order} objects
     */
    @Autowired
    private OrderService orderService;

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
    @Autowired
    private TableStatusService tableStatusService;

    /**
     * cart for {@link Dish}, which the {@link Staff} bought
     */
    private List<Dish> cart = new ArrayList<>();
    private Object LocalDateTime;

    /**
     * get request, which gives the List of {@link Dish} of determined type
     *
     * @param dishType - type of {@link Dish}
     * @return returns the List of {@link Dish} of determined type
     */
    @GetMapping("menu/{dishType}")
    public List<Dish> showTables(@PathVariable String dishType) {
        List<Dish> dishes = dishService.findByStatusDish(StatusDish.available); //findAll();
        List<Dish> selectedDish = new ArrayList<>();
        for (Dish dish : dishes) {
            List<FoodIngredients> foodIngredients = dish.getIngredients();
            for (FoodIngredients ing : foodIngredients) {
                if (ing.getQuantity() >= ing.getIngredient().getQuantity_in_stock()) {
                    dish.setStatusDish(StatusDish.no_ingredients);
                    break;
                }
                else if(dish.getStatusDish() == StatusDish.no_ingredients) {
                    dish.setStatusDish(StatusDish.available);
                }
            }
            dishService.editDish(dish);
        }
        dishes = dishes.stream().filter(x -> x.getStatusDish() == StatusDish.available).collect(Collectors.toList());
        for (Dish elem : dishes) {
            TypeDish elemType = elem.getTypeDish();
            if (elemType.getTitle().equals(dishType)) {
                selectedDish.add(elem);
            }
        }
        return selectedDish;
    }

    // ToDo - как достать номер столика, с которого заказал посетитель? И если он захочет вдруг пересесть на другой?

    /**
     * post request, which add the {@link Dish} to the cart
     *
     * @param json - json object, which contains the id of {@link Dish}
     */
    @PostMapping("/add")
    public void addDishToCart(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        Dish dish = dishService.getById(dishId);
        cart.add(dish);
    }

    /**
     * post request, which delete the {@link Dish} from the cart
     *
     * @param json - json object, which contains the id of {@link Dish}
     */
    @PostMapping("/delete")
    public void deleteDishFromCart(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        for (Dish d : cart) {
            if (d.getId().equals(dishId)) {
                cart.remove(d);
                break;
            }
        }
    }

    /**
     * post request, which delete the {@link Dish} of determined type
     *
     * @param json - json object, which contains the id of {@link Dish}
     */
    @PostMapping("/cancel")
    public void cancelDishes(@RequestBody Map<String, Object> json) {
        Integer dishId = (Integer) json.values().toArray()[0];
        Predicate<Dish> deletingOnIdPredicate = d -> d.getId().equals(dishId);
        cart.removeIf(deletingOnIdPredicate);
    }

    /**
     * post request for confirm the order with {@link Dish} in the cart
     *
     * @param json - json object, which contains the number of {@link Order}
     */
    @PostMapping("/confirm/{pay}")
    public String confirmOrder(@RequestBody Map<String, String> json, @PathVariable Integer pay) {
        String message = "";
        Integer tableNumber = Integer.valueOf(json.get("tableNumber"));
        String stripeToken = String.valueOf(json.get("token"));
        float price = 0;
        boolean tmp = false;
        Order order = new Order();
        RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        order.setRestaurantTable(restaurantTable);
        order.setType("На месте");
        order.setDateOrders(Timestamp.valueOf(org.joda.time.LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss")));
        if (pay == 1) {
            order.setTypePayment(typePaymentService.findByTitle("По карте"));
        } else order.setTypePayment(typePaymentService.findByTitle("Наличными"));
        order.setOrderStatus(orderStatusService.findByTitle("Принят"));
        orderService.addOrder(order);
        message = "Оплата наличными. Ожидайте официанта";

        for (Dish dish : cart) {
            price += dish.getPrice();
        }
        if (pay == 1) {
            Stripe.apiKey = "sk_test_LWGe8XDaCeaFRuAVJopBOdoT00K322zhWt";
            if (stripeToken != null) {
                try {
                    Map<String, Object> params = new HashMap<>();
                    params.put("amount", Math.round(price / 0.6381));
                    params.put("currency", "usd");
                    params.put("description", "Example charge");
                    params.put("source", stripeToken);
                    Map<String, String> metadata = new HashMap<>();
                    metadata.put("order_id", order.getId().toString());
                    params.put("metadata", metadata);
                    Charge charge = Charge.create(params);
                    if (charge.getPaid() && charge.getStatus().equals("succeeded")) {
                        tmp = true;
                        message = "Оплата по карте прошла успешно";
                        System.out.println("yes");
                    }
                } catch (Exception e) {
                    message = "Ошибка оплаты картой";
                    System.out.println("no");
                }
            }
        }

        if (tmp || pay == 2) {
            TableStatus tableStatus = tableStatusService.findByTitle("busy_need_to_bring");
            restaurantTable.setTableStatus(tableStatus);
            restaurantTableService.editTable(restaurantTable);

            int preparingTimeInSecond = cart.stream()
                    .mapToInt(dish -> dish.getPreparingTime().toLocalTime().toSecondOfDay())
                    .sum();
            Time preparingTime = new Time(preparingTimeInSecond);
            DishStatus dishStatus = dishStatusService.findByTitle("В ожидании");
            for (Dish dish : cart) {
                DishesFromOrder dishesFromOrder = new DishesFromOrder(preparingTime, dishStatus);
                dishesFromOrder.setDish(dish);
                dishesFromOrder.setOrder(order);
                dishesFromOrderService.addDishFromOrder(dishesFromOrder);
            }
        }
        cart.clear();
        return message;
    }
}