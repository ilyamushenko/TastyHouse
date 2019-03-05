package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Dish;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishService {

    Dish addDish(Dish dish);

    void delete(Integer id);

    Dish editDish(Dish dish);

    Dish getByName(String name);

    Dish getById(Integer id);

    List<Dish> findAll();
}
