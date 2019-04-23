package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.RestaurantTable;

/**
 * @author Кушнеренко Виктор
 */
public interface RestaurantTableDAO extends JpaRepository<RestaurantTable, Integer> {

    RestaurantTable getById(Integer id);
}
