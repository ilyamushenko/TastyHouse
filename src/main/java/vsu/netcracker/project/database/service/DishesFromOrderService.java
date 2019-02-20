package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.DishesFromOrder;

import java.util.List;

public interface DishesFromOrderService {

    DishesFromOrder addDishFromOrder(DishesFromOrder dishesFromOrder);

    void delete(Integer id);

    DishesFromOrder editDishFromOrder(DishesFromOrder dishesFromOrder);

    List<DishesFromOrder> findAll();

    DishesFromOrder findDishesFromOrderByOrder(Integer orderId);
}
