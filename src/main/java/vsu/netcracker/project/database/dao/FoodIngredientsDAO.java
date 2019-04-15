package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.FoodIngredients;

import java.util.List;

/**
 * @author Попова Алина
 */

public interface FoodIngredientsDAO extends JpaRepository<FoodIngredients, Integer> {

    List<FoodIngredients> findByDishId(Integer dishId);

    List<FoodIngredients> findByIngredientId(Integer ingredientId);

    FoodIngredients getById(Integer id);
}
