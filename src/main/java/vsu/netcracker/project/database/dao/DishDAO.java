package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Dish;

/**
 * @author Кушнеренко Виктор
 */
public interface DishDAO extends JpaRepository<Dish, Integer> {

    @Query("select d from Dish d where d.name = :name")
    Dish findByName(@Param("name") String name);

    @Query("select d from Dish d where d.id = :id")
    Dish getById(@Param("id") Integer id);
}
