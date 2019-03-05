package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.OrderDAO;
import vsu.netcracker.project.database.models.Order;
import vsu.netcracker.project.database.service.OrderService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Order addOrder(Order order) {
        return orderDAO.saveAndFlush(order);
    }

    @Override
    public void delete(Integer id) {
        orderDAO.deleteById(id);
    }

    @Override
    public Order editOrder(Order order) {
        return orderDAO.saveAndFlush(order);
    }

    @Override
    public Order findByTableNumber(Integer tableNumber) {
        return orderDAO.findByTableNumber(tableNumber);
    }

    @Override
    public List<Order> findByDateOrdersBetween(Timestamp dateOrders, Timestamp dateOrders2) {
        return orderDAO.findByDateOrdersBetween(dateOrders, dateOrders2);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}
