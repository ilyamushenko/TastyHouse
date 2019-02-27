package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.Dishes;

import javax.transaction.Transactional;

/**
 * @author Кушнеренко Виктор
 */
public interface DishesDAO extends JpaRepository<Dishes, Integer> {

    @Query("select d from Dishes d where d.name = :name")
    Dishes findByName(@Param("name") String name);

    @Query("select d from Dishes d where d.id = :id")
    Dishes getById(@Param("id") Integer id);
}
