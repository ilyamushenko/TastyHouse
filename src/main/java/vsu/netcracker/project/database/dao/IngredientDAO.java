package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.Ingredient;

/**
 * @author Попова Алина
 */

public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {

    Ingredient getById(Integer id);

}
