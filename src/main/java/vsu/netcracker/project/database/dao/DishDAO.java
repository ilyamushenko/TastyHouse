package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.TypeDish;

import java.util.List;
import java.util.Optional;

/**
 * @author Кушнеренко Виктор
 */
public interface DishDAO extends JpaRepository<Dish, Integer> {

    Dish getById(Integer id);

    List<Dish> findByTypeDish(TypeDish typeDish);
}
