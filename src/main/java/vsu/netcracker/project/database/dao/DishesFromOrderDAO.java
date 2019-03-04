package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.models.Orders;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishesFromOrderDAO extends JpaRepository<DishesFromOrder, Integer> {

    @Query("select o.dishesFromOrder from Orders o where o.id = :orderId")
    DishesFromOrder findDishesFromOrderById(@Param("orderId") Integer orderId);

    @Query("select d from DishesFromOrder d where d.id = :id")
    DishesFromOrder getById(@Param("id") Integer id);
}
