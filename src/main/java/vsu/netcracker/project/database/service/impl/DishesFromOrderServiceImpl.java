package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.DishesFromOrderDAO;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.models.DishesFromOrder;
import vsu.netcracker.project.database.service.DishesFromOrderService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class DishesFromOrderServiceImpl implements DishesFromOrderService {

    private final DishesFromOrderDAO dishesFromOrderDAO;

    @Autowired
    public DishesFromOrderServiceImpl(DishesFromOrderDAO dishesFromOrderDAO) {
        this.dishesFromOrderDAO = dishesFromOrderDAO;
    }

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
    public List<DishesFromOrder> findDishesFromOrdersByOrderId(Integer ordId) {
        return dishesFromOrderDAO.findDishesFromOrdersByOrderId(ordId);
    }

    @Override
    public DishesFromOrder getById(Integer id) {
        return dishesFromOrderDAO.getById(id);
    }

    @Override
    public List<DishesFromOrder> getByDishStatusIsNot(DishStatus dishStatus) {
        return dishesFromOrderDAO.getByDishStatusIsNot(dishStatus);
    }
}
