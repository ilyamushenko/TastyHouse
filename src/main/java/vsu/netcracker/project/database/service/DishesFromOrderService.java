package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishesFromOrderService {

    DishesFromOrder addDishFromOrder(DishesFromOrder dishesFromOrder);

    void delete(Integer id);

    DishesFromOrder editDishFromOrder(DishesFromOrder dishesFromOrder);

    List<DishesFromOrder> findAll();

    List<DishesFromOrder> findDishesFromOrdersByOrderId(Integer ordId);

    DishesFromOrder getById(Integer id);

    List<DishesFromOrder> getByDishStatusIsNot(DishStatus dishStatus);
}
