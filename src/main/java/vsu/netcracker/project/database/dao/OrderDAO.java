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

    List<Order> findByDateOrdersBetween(Timestamp dateOrders, Timestamp dateOrders2);
}
