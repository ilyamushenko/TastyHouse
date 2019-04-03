package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.FoodIngredientsDAO;
import vsu.netcracker.project.database.models.FoodIngredients;
import vsu.netcracker.project.database.service.FoodIngredientsService;

import java.util.List;

/**
 * @author Попова Алина
 */
@Service
public class FoodIngredientsServiceImpl implements FoodIngredientsService {

    private final FoodIngredientsDAO foodIngredientsDAO;

    @Autowired
    public FoodIngredientsServiceImpl(FoodIngredientsDAO foodIngredientsDAO) {
        this.foodIngredientsDAO = foodIngredientsDAO;
    }

    @Override
    public FoodIngredients addFoodIngredients(FoodIngredients foodIngredients) {
        return foodIngredientsDAO.saveAndFlush(foodIngredients);
    }

    @Override
    public void delete(Integer id) {
        foodIngredientsDAO.deleteById(id);
    }

    @Override
    public FoodIngredients editFoodIngredients(FoodIngredients foodIngredients) {
        return foodIngredientsDAO.saveAndFlush(foodIngredients);
    }

    @Override
    public List<FoodIngredients> findAll() {
        return foodIngredientsDAO.findAll();
    }

    @Override
    public List<FoodIngredients> findFoodIngredientsByDish(Integer dishId) {
        return foodIngredientsDAO.findFoodIngredientsByDish(dishId);
    }

    @Override
    public FoodIngredients getById(Integer id) {
        return foodIngredientsDAO.getById(id);
    }

}
