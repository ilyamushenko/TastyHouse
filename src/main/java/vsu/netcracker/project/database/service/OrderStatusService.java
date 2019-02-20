package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    OrderStatus addStatus(OrderStatus status);

    void delete(Integer id);

    OrderStatus editStatus(OrderStatus status);

    OrderStatus findByTitle(String title);

    List<OrderStatus> findAll();
}
