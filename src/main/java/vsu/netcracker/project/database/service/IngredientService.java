package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Ingredient;

import java.util.List;

/**
 * @author Попова Алина
 */
public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    void delete(Integer id);

    Ingredient editIngredient(Ingredient ingredient);

    List<Ingredient> findAll();

    Ingredient getById(Integer id);

}
