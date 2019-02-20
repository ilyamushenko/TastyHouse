package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.OrdersDAO;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.service.OrdersService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDAO ordersDAO;

    @Override
    public Orders addOrder(Orders order) {
        return ordersDAO.saveAndFlush(order);
    }

    @Override
    public void delete(Integer id) {
        ordersDAO.deleteById(id);
    }

    @Override
    public Orders editOrder(Orders order) {
        return ordersDAO.saveAndFlush(order);
    }

    @Override
    public Orders findByTableNumber(Integer tableNumber) {
        return ordersDAO.findByTableNumber(tableNumber);
    }

    @Override
    public List<Orders> findByDateOrdersBetween(Timestamp dateOrders, Timestamp dateOrders2) {
        return ordersDAO.findByDateOrdersBetween(dateOrders, dateOrders2);
    }

    @Override
    public List<Orders> findAll() {
        return ordersDAO.findAll();
    }
}
