package vsu.netcracker.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.FoodIngredients;
import vsu.netcracker.project.database.models.Ingredient;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.models.RestaurantTable;
import vsu.netcracker.project.database.models.TableStatus;
import vsu.netcracker.project.database.service.DishStatusService;
import vsu.netcracker.project.database.service.DishesFromOrderService;
import vsu.netcracker.project.database.service.FoodIngredientsService;
import vsu.netcracker.project.database.service.IngredientService;
import vsu.netcracker.project.database.service.RestaurantTableService;
import vsu.netcracker.project.database.service.TableStatusService;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handle kitchen requests
 *
 * @author Алина Попова
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("kitchen")
public class KitchenController {

    /**
     * service for interaction with {@link DishesFromOrder} objects
     */
    private final DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link DishStatus} objects
     */
    private final DishStatusService dishStatusService;

    /**
     * service for interaction with {@link TableStatus} objects
     */
    private final TableStatusService tableStatusService;

    /**
     * service for interaction with {@link RestaurantTable} objects
     */
    private final RestaurantTableService restaurantTableService;

    /**
     * service for interaction with {@link Ingredient} objects
     */
    private final IngredientService ingredientService;
    /**
     * service for interaction with {@link FoodIngredients} objects
     */
    private final FoodIngredientsService foodIngredientsService;

    /**
     * injecting services with constructor
     */
    @Autowired
    public KitchenController(DishesFromOrderService dishesFromOrderService,
                             DishStatusService dishStatusService,
                             TableStatusService tableStatusService,
                             RestaurantTableService restaurantTableService,
                             IngredientService ingredientService,
                             FoodIngredientsService foodIngredientsService) {
        this.dishesFromOrderService = dishesFromOrderService;
        this.dishStatusService = dishStatusService;
        this.tableStatusService = tableStatusService;
        this.restaurantTableService = restaurantTableService;
        this.ingredientService = ingredientService;
        this.foodIngredientsService = foodIngredientsService;
    }

    /**
     * function, which changes the {@link TableStatus} of {@link RestaurantTable}
     *
     * @param restaurantTable - the {@link RestaurantTable}, which we need to update
     * @see KitchenController#changeDishStatus(Map)
     */
    private void changeTableStatus(RestaurantTable restaurantTable) {
        TableStatus tableStatus = null;
        if (restaurantTable.getOrdersList().isEmpty()) {
            tableStatus = tableStatusService.findByTitle("free");
        } else if (restaurantTable.getOrdersList().stream().allMatch(
                    s -> s.getDishesFromOrder().stream().allMatch(
                            d -> d.getDishStatus().getTitle().equals("Отнесено")))) {
            tableStatus = tableStatusService.findByTitle("busy_need_attention");
        } else if (restaurantTable.getOrdersList().stream().anyMatch(
                    s -> s.getDishesFromOrder().stream().anyMatch(
                            d -> d.getDishStatus().getTitle().equals("Готово")))) {
            tableStatus = tableStatusService.findByTitle("busy_need_to_bring");
        } else if (restaurantTable.getOrdersList().stream().anyMatch(
                s -> s.getDishesFromOrder().stream().anyMatch(
                        d -> d.getDishStatus().getTitle().equals("Готовится") || d.getDishStatus().getTitle().equals("В ожидании")))) {
            tableStatus = tableStatusService.findByTitle("busy_no_need_attention");
        }
        restaurantTable.setTableStatus(tableStatus);
        restaurantTableService.editTable(restaurantTable);
    }

    /**
     * start request for kitchen
     *
     * @return returns List of {@link DishesFromOrder}, which have {@link DishStatus} equal to 'В ожидании'
     */
    @GetMapping
    public List<DishesFromOrder> showTables() {
        DishStatus dishStatus = dishStatusService.findByTitle("Готово");
        List<DishesFromOrder> dishesFromOrdersKithen = dishesFromOrderService.getByDishStatusIsNot(dishStatus);
        dishesFromOrdersKithen.sort(Comparator.comparing(DishesFromOrder::getTimeOrder));
        return dishesFromOrdersKithen;
    }

    /**
     * post request for changing {@link DishStatus}
     *
     * @param json - json object, which contains from status, id and tableNumber of {@link Order}
     * @see KitchenController#changeTableStatus(RestaurantTable)
     */
    @PostMapping("/status-change")
    public void changeDishStatus(@RequestBody Map<String, Object> json) {
        String status = (String) json.get("status");
        Integer id = (Integer) json.get("id");
        // Integer tableNumber = (Integer) json.get("tableNumber");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DishesFromOrder dishesFromOrder = dishesFromOrderService.getById(id);
        // RestaurantTable restaurantTable = restaurantTableService.findById(tableNumber);
        RestaurantTable restaurantTable = dishesFromOrder.getOrder().getRestaurantTable();
        if (status.equals("Готовится")) {
            dishesFromOrder.setBeginCookingTime(timestamp);
            Dish dish = dishesFromOrder.getDish();
            List<FoodIngredients> foodIngredients = foodIngredientsService.findFoodIngredientsByDish(dish.getId());
            foodIngredients.forEach(x -> {
                Ingredient ingredient = ingredientService.getById(x.getIngredient().getId());
                ingredient.setQuantity_in_stock(ingredient.getQuantity_in_stock() - x.getQuantity());
                ingredientService.editIngredient(ingredient);
            });
        } else if (status.equals("Готово")) {
            dishesFromOrder.setEndCookingTime(timestamp);
        }
        DishStatus dishStatus = dishStatusService.findByTitle(status);
        dishesFromOrder.setDishStatus(dishStatus);
        dishesFromOrderService.editDishFromOrder(dishesFromOrder);
        changeTableStatus(restaurantTable);
    }
}