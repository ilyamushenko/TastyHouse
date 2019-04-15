package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.IngredientDAO;
import vsu.netcracker.project.database.models.Ingredient;
import vsu.netcracker.project.database.service.IngredientService;

import java.util.List;

/**
 * @author Попова Алина
 */

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientDAO ingredientDAO;

    @Autowired
    public IngredientServiceImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientDAO.saveAndFlush(ingredient);
    }

    @Override
    public void delete(Integer id) {
        ingredientDAO.deleteById(id);
    }

    @Override
    public Ingredient editIngredient(Ingredient ingredient) {
        return ingredientDAO.saveAndFlush(ingredient);
    }


    @Override
    public List<Ingredient> findAll() {
        return ingredientDAO.findAll();
    }

    @Override
    public Ingredient getById(Integer id) {
        return ingredientDAO.getById(id);
    }

}
