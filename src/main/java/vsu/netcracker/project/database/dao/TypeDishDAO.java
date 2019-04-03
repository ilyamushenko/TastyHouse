package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.TypeDish;

/**
 * @author Кушнеренко Виктор
 */
public interface TypeDishDAO extends JpaRepository<TypeDish, Integer> {

    TypeDish findByTitle(String title);

    TypeDish getById(Integer id);
}
