package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.TypeDish;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishService {

    long count();

    Dish addDish(Dish dish);

    void delete(Integer id);

    Dish editDish(Dish dish);

    Dish getById(Integer id);

    List<Dish> findByTypeDish(TypeDish typeDish);

    List<Dish> findAll();


}
