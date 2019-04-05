package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.FoodIngredients;

import java.util.List;

/**
 * @author Попова Алина
 */

public interface FoodIngredientsService {

    FoodIngredients addFoodIngredients(FoodIngredients foodIngredients);

    void delete(Integer id);

    FoodIngredients editFoodIngredients(FoodIngredients foodIngredients);

    List<FoodIngredients> findAll();

    List<FoodIngredients> findFoodIngredientsByDish(Integer dishId);

    List<FoodIngredients> findByIngredientId(Integer ingredientId);

    FoodIngredients getById(Integer id);

}
