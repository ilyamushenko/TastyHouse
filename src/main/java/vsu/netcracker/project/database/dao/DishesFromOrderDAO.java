package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishesFromOrderDAO extends JpaRepository<DishesFromOrder, Integer> {

    List<DishesFromOrder> findDishesFromOrderById(Integer orderId);

    DishesFromOrder getById(Integer id);

    List<DishesFromOrder> getByDishStatusIsNot(DishStatus dishStatus);
}
