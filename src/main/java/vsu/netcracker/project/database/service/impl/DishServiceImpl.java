package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.DishDAO;
import vsu.netcracker.project.database.models.Dish;
import vsu.netcracker.project.database.models.TypeDish;
import vsu.netcracker.project.database.service.DishService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class DishServiceImpl implements DishService {

    private final DishDAO dishDAO;

    @Autowired
    public DishServiceImpl(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Override
    public Dish addDish(Dish dish) {
        return dishDAO.saveAndFlush(dish);
    }

    @Override
    public void delete(Integer id) {
        dishDAO.deleteById(id);
    }

    @Override
    public Dish editDish(Dish dish) {
        return dishDAO.saveAndFlush(dish);
    }

    @Override
    public Dish getById(Integer id) {
        return dishDAO.getById(id);
    }

    @Override
    public List<Dish> findByTypeDish(TypeDish typeDish) {
        return dishDAO.findByTypeDish(typeDish);
    }

    @Override
    public List<Dish> findAll() {
        return dishDAO.findAll();
    }
}
