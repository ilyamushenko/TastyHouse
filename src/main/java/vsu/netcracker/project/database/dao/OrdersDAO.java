package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Orders;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface OrdersDAO extends JpaRepository<Orders, Integer> {

    @Query("select o from Orders o where o.id = :tableNumber")
    Orders findByTableNumber(@Param("tableNumber") Integer tableNumber);

    @Query("select o from Orders o where o.dateOrders between :dateOrders and :dateOrders2")
    List<Orders> findByDateOrdersBetween(@Param("dateOrders") Timestamp dateOrders, @Param("dateOrders2") Timestamp dateOrders2);
}
