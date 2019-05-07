package vsu.netcracker.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.netcracker.project.database.models.*;
import vsu.netcracker.project.database.models.enums.StatusDish;
import vsu.netcracker.project.database.service.*;
import vsu.netcracker.project.subModels.DishNameAndPrice;
import vsu.netcracker.project.subModels.IngredientForTomorrow;
import vsu.netcracker.project.utils.UtilsForAdministrator;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller class for handle admin requests
 *
 * @author Илья Мущенко
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdministratorController {
    //5555 5555 5555 4444 01/23 123 36101 ---- для оплаты по карте

    @Autowired
    private ImageService imageService;

    /**
     * type of {@link Dish}
     */
    private String type;

    /**
     * service for interaction with {@link Order} objects
     */
    private final OrderService orderService;

    /**
     * service for interaction with {@link vsu.netcracker.project.database.models.DishesFromOrder} objects
     */
    private final DishesFromOrderService dishesFromOrderService;

    /**
     * service for interaction with {@link Dish} objects
     */
    private final DishService dishService;

    /**
     * service for interaction with {@link Ingredient} objects
     */
    private final IngredientService ingredientService;
    /**
     * service for interaction with {@link TypeDish} objects
     */
    private final TypeDishService typeDishService;
    /**
     * service for interaction with {@link FoodIngredients} objects
     */
    private final FoodIngredientsService foodIngredientsService;
    /**
     * injecting services with constructor
     */
    @Autowired
    public AdministratorController(OrderService orderService, DishesFromOrderService dishesFromOrderService, DishService dishService, IngredientService ingredientService, TypeDishService typeDishService, FoodIngredientsService foodIngredientsService) {
        this.orderService = orderService;
        this.dishesFromOrderService = dishesFromOrderService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.typeDishService = typeDishService;
        this.foodIngredientsService = foodIngredientsService;
    }

    /**
     * post request for admin, which returns json object, containing info about most popular dish
     *
     * @param selectedType - selected type of statistic
     * @return returns Map which represent json object to client
     */
    @PostMapping
    public Map<String, String> post(@RequestBody Map<String, String> selectedType) {
        type = selectedType.get("statistic_type");
        System.out.println(type);

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp needTime = null;
        switch (type) {
            case "day": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
                break;
            }
            case "month": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
                break;
            }
            case "year": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusYears(1));
                break;
            }
        }

        List<Order> orders = orderService.findByDateOrdersBetween(needTime, now);
        List<List<DishesFromOrder>> dishesFromOrders = new ArrayList<>();
        for (Order ord : orders) {

            List<DishesFromOrder> temp = dishesFromOrderService.findDishesFromOrdersByOrderId(ord.getId());
            if (temp != null) {
                dishesFromOrders.add(temp);
            }
        }
        Map<Integer, Integer> counterOfDishes = new HashMap<>();

        for (List<DishesFromOrder> dfoList : dishesFromOrders) {

            for (DishesFromOrder dfo : dfoList) {
                int dish = dfo.getDish().getId();
                if (counterOfDishes.keySet().contains(dish)) {
                    counterOfDishes.put(dish, counterOfDishes.get(dish) + 1);
                } else {
                    counterOfDishes.put(dish, 1);
                }
            }
        }

        Integer needId = UtilsForAdministrator.findKeyWithMaxValueInMap(counterOfDishes);
        double count = UtilsForAdministrator.findMaxValueInMap(counterOfDishes);

        Dish dish = dishService.getById(needId);
        Map<String, String> json = new HashMap<>();
        json.put("type_dish", dish.getTypeDish().getTitle());
        json.put("name", dish.getName());
        json.put("img_url", dish.getImgUrl());
        json.put("time", dish.getPreparingTime().toString());
        json.put("price", String.valueOf(dish.getPrice() * count));
        json.put("count", String.valueOf((int) count));
        return json;
    }

    @PostMapping("/inform")
    public Map<String, String> getDishesInfo(@RequestBody Map<String, String> needDishMap) {
        String needDish = needDishMap.get("needDish");
        System.out.println(needDish);
        int id = Integer.valueOf(needDish);
        Map<String, String> map = UtilsForAdministrator.getFullInfoAboutDish(id, orderService, dishesFromOrderService);
        Dish dish = dishService.getById(Integer.valueOf(needDish));
        map.put("name", dish.getName());
        map.put("img", dish.getImgUrl());
        map.put("mass", dish.getMass());
        map.put("time", String.valueOf(dish.getPreparingTime()));
        map.put("type", String.valueOf(dish.getTypeDish().getTitle()));
        map.put("price", String.valueOf(dish.getPrice()));
        return map;
    }

    @GetMapping("/inform")
    public Map<String, List<Map<String, String>>> getAllDishes() {
        //Map<ТипБлюда, Map<НазваниеБлюда, НазваниеДляОтправкиОбратно(ID)>>


        List<Dish> dishes = dishService.findAll();
        Map<String, List<Map<String, String>>> json = new HashMap<>();
        boolean willAdd;

        for (Dish dish : dishes) {
            String typeDish = dish.getTypeDish().getTitle();
            List<Map<String, String>> tempList;
            if (json.containsKey(typeDish)) {
                tempList = json.get(typeDish);
                willAdd = false;
            } else {
                tempList = new ArrayList<>();
                willAdd = true;
            }
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("text", dish.getName());
            tempMap.put("value", String.valueOf(dish.getId()));
            tempList.add(tempMap);
            if (willAdd)
                json.put(typeDish, tempList);
        }
        System.out.println(json);
        return json;
    }

    @GetMapping("/rating")
    public Map<String, Long> salesForAllDishesOneMonth(@RequestParam("need_period") String period) {
        Timestamp needPeriodInTimestamp;// = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
        System.out.println(period);
        switch (period) {
            case "oneMonth":
                needPeriodInTimestamp = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
                break;
            case "oneWeek":
                needPeriodInTimestamp = Timestamp.valueOf(LocalDateTime.now().minusWeeks(1));
                break;
            case "threeMonths":
                needPeriodInTimestamp = Timestamp.valueOf(LocalDateTime.now().minusMonths(3));
                break;
            default:
                needPeriodInTimestamp = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
//                throw new IllegalStateException("Unexpected value: " + period);
        }

        List<Integer> listOfDishesId = new ArrayList<>();
        dishService.findAll().forEach(dish -> listOfDishesId.add(dish.getId()));
        return UtilsForAdministrator.getSalesForAllDishes(listOfDishesId, needPeriodInTimestamp, dishesFromOrderService, orderService, dishService);
    }
//    @GetMapping()
//    public Map<String, String> getInfoAboutAllDishes() {
//        UtilsForAdministrator.
//    }

//    @GetMapping("/addDish/notEnoughIngridients")
//    public Map<Ingredient, Double> showNotEnoughIngridientsForTommorow() {
//        return UtilsForAdministrator.getInformationOfIngredientsForTomorrowDay(dishesFromOrderService, orderService, dishService, ingredientService, foodIngredientsService);
//    }

    @GetMapping("/addDish/ingredient")
    public List<IngredientForTomorrow> showIngredients() {
        List<Ingredient> ingredient = ingredientService.findAll();
        List<IngredientForTomorrow> ingredients = UtilsForAdministrator.getInformationOfIngredientsForTomorrowDay(dishesFromOrderService, orderService, dishService, ingredientService, foodIngredientsService);
        ingredient.sort(Comparator.comparing(Ingredient::getName));
        ingredients.sort(Comparator.comparing(el -> el.getIngredient().getName()));
        return ingredients;
    }

    @GetMapping("/addDish/typeDish")
    public List<TypeDish> showTypeDish() {
        List<TypeDish> typeDishes = typeDishService.findAll();
        return typeDishes;
    }

    @PostMapping("/addDishInMenu")
    public Dish addDishInMenu(@RequestBody Map<String, Object> json) {
        Integer typeDish = (Integer) json.get("typeDish");
        String name = (String) json.get("name");
        Float price = Float.valueOf((String) json.get("price")) ;
        String massDish = (String) json.get("massDish");
        Time preparingTime = (Time.valueOf((String) json.get("preparingTime"))) ;
        String recipe = (String) json.get("recipe");
        String description = (String) json.get("description");
        String img = (String) json.get("img");
        String nameImg = name.replace(' ', '_');

        TypeDish typeDish1 = typeDishService.getById(typeDish);
        StatusDish statusDish = StatusDish.blocked;

        Dish dish = new Dish(name, price, recipe, massDish, preparingTime, typeDish1, imageService.saveImage(img, nameImg), description, statusDish);
        dishService.addDish(dish);

        List<Map<String, Object>> list = (List<Map<String, Object>>) json.get("dishIngredients");
        list.forEach(x -> {
            Integer ingridient = (Integer) x.get("value");
            Float mass = Float.valueOf((String) x.get("mass"));
            FoodIngredients foodIngredient = new FoodIngredients();
            foodIngredient.setQuantity(mass);
            foodIngredient.setIngredient(ingredientService.getById(ingridient));
            foodIngredient.setDish(dish);
            foodIngredientsService.addFoodIngredients(foodIngredient);
        });

        return dish;
    }

    @PostMapping("/addIngredient")
    public Ingredient addIngredient(@RequestBody Map<String, Object> json) {
        String name = (String) json.get("name");
        String type = (String) json.get("type");
        String unit = (String) json.get("unit");
        String price = (String) json.get("price");

        Ingredient ingredient = new Ingredient(name, type, 0f, unit, Float.valueOf(price));

        ingredientService.addIngredient(ingredient);
        return ingredient;
    }

    @PostMapping("/editMassIngredient")
    public Ingredient editMassIngredient(@RequestBody Map<String, String> json) {
        Float mass = Float.valueOf(json.get("mass"));
        Integer id = Integer.valueOf(json.get("id"));
        Ingredient ingredient = ingredientService.getById(id);
        ingredient.setQuantity_in_stock(ingredient.getQuantity_in_stock() + mass);

        ingredientService.editIngredient(ingredient);
        return ingredient;
    }

    @PostMapping("/deleteIngredient")
    public boolean deleteIngredient(@RequestBody Integer id) {
        if (id != null)  {
            List<FoodIngredients> foodIngredients = foodIngredientsService.findByIngredientId(id);
            if(foodIngredients.size()==0) {
                ingredientService.delete(id);
                return true;
            }
            else return false;
        }
        return false;
    }

    @GetMapping("/showDish")
    public List<Dish> showDish() {
        List<Dish> dishes = dishService.findAll();
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
        return dishes;
    }

    @GetMapping("/showStatusDish")
    public List<Map<String, String>> showStatusDish() {
        return StatusDish.getAll();
    }

    @PostMapping("/editDishInStopList")
    public Dish editDishInStopList(@RequestBody Map<String, Object> json) {
        StatusDish statusDish = StatusDish.valueOf((String) json.get("statusDish"));
        Integer id = (Integer) json.get("id");
        Dish dish = dishService.getById(id);
        dish.setStatusDish(statusDish);
        dishService.editDish(dish);
        return dish;
    }


    /**
     * Get request for admin, which help to get information about dishes and their prices,
     * which derived by ingredients
     * @return {@link Map} with {@link Dish} and prices
     */

    @GetMapping("/dishWithPrice")
    public @ResponseBody  List<DishNameAndPrice>  getDishesAndRealPrice() {
        List<DishNameAndPrice> list = new ArrayList<>();
        List<Dish> allDishes = dishService.findAll();
        allDishes.forEach(dish -> {
            list.add(new DishNameAndPrice(dish.getName(), dish.getPrice(),
                    (float) dish.getIngredients().stream().mapToDouble(ingredient ->
                            ingredient.getIngredient().getPrice()*ingredient.getQuantity()).sum()));
        });
        return list;
    }

    @GetMapping("/revenue/get_dishes/{period}")
    public @ResponseBody  List<DishNameAndPrice>  QQQQQgetDishesAndRealPrice(@PathVariable String period) {
        System.out.println("!!!!!!! _ _ _ _ _" + period + " _ _ _ _ _ _ _ !!!!!!!");

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp needTime = null;
        switch (period) {
            case "day": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
                break;
            }
            case "week": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
                break;
            }
            case "oneMonth": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusYears(1));
                break;
            }
            case "threeMonths": {
                needTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(3));
                break;
            }
        }
        return UtilsForAdministrator.getRevenueInPeriod(needTime, orderService, dishesFromOrderService, dishService);
    }
}
