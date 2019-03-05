package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface OrderDAO extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.id = :tableNumber")
    Order findByTableNumber(@Param("tableNumber") Integer tableNumber);

    @Query("select o from Order o where o.dateOrders between :dateOrders and :dateOrders2")
    List<Order> findByDateOrdersBetween(@Param("dateOrders") Timestamp dateOrders, @Param("dateOrders2") Timestamp dateOrders2);
}
