package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.OrderStatus;

/**
 * @author Кушнеренко Виктор
 */
public interface OrderStatusDAO extends JpaRepository<OrderStatus, Integer> {

    OrderStatus findByTitle(String title);
}
