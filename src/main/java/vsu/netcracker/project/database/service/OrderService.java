package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface OrderService {

    Order addOrder(Order order);

    void delete(Integer id);

    Order editOrder(Order order);

    Order findByTableNumber(Integer tableNumber);

    List<Order> findByDateOrdersBetween(Timestamp dateOrders, Timestamp dateOrders2);

    List<Order> findAll();

}
