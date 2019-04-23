package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.DishStatus;

/**
 * @author Кушнеренко Виктор
 */
public interface DishStatusDAO extends JpaRepository<DishStatus, Integer> {

    DishStatus findByTitle(String title);
}
