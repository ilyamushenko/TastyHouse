package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.service.DishesFromOrderService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class DishesFromOrderServiceImpl implements DishesFromOrderService {

    @Autowired
    private DishesFromOrderDAO dishesFromOrderDAO;

    @Override
    public DishesFromOrder addDishFromOrder(DishesFromOrder dishesFromOrder) {
        return dishesFromOrderDAO.saveAndFlush(dishesFromOrder);
    }

    @Override
    public void delete(Integer id) {
        dishesFromOrderDAO.deleteById(id);
    }

    @Override
    public DishesFromOrder editDishFromOrder(DishesFromOrder dishesFromOrder) {
        return dishesFromOrderDAO.saveAndFlush(dishesFromOrder);
    }

    @Override
    public List<DishesFromOrder> findAll() {
        return dishesFromOrderDAO.findAll();
    }

    @Override
    public DishesFromOrder findDishesFromOrderByOrder(Integer orderId) {
        return dishesFromOrderDAO.findDishesFromOrderById(orderId);
    }
}
