package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.OrderStatusDAO;
import vsu.netcracker.project.database.models.OrderStatus;
import vsu.netcracker.project.database.service.OrderStatusService;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusDAO orderStatusDAO;

    @Override
    public OrderStatus addStatus(OrderStatus status) {
        return orderStatusDAO.saveAndFlush(status);
    }

    @Override
    public void delete(Integer id) {
        orderStatusDAO.deleteById(id);
    }

    @Override
    public OrderStatus editStatus(OrderStatus status) {
        return orderStatusDAO.saveAndFlush(status);
    }

    @Override
    public OrderStatus findByTitle(String title) {
        return orderStatusDAO.findByTitle(title);
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusDAO.findAll();
    }
}
