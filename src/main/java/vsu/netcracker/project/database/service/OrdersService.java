package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Orders;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface OrdersService {

    Orders addOrder(Orders order);

    void delete(Integer id);

    Orders editOrder(Orders order);

    Orders findByTableNumber(Integer tableNumber);

    List<Orders> findByDateOrdersBetween(Timestamp dateOrders, Timestamp dateOrders2);

    List<Orders> findAll();

}
