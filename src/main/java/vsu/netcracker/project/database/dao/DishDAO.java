package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.TypeDish;
import vsu.netcracker.project.database.models.enums.StatusDish;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishDAO extends JpaRepository<Dish, Integer> {

    Dish getById(Integer id);

    long count();

    List<Dish> findByTypeDish(TypeDish typeDish);

    List<Dish> findByStatusDish(StatusDish statusDish);
}
