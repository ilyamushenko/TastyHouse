package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.DishesDAO;
import vsu.netcracker.project.database.models.Dishes;
import vsu.netcracker.project.database.service.DishesService;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {

    @Autowired
    private DishesDAO dishesDAO;

    @Override
    public Dishes addDish(Dishes dish) {
        return dishesDAO.saveAndFlush(dish);
    }

    @Override
    public void delete(Integer id) {
        dishesDAO.deleteById(id);
    }

    @Override
    public Dishes editDish(Dishes dish) {
        return dishesDAO.saveAndFlush(dish);
    }

    @Override
    public Dishes getByName(String name) {
        return dishesDAO.findByName(name);
    }

    @Override
    public Dishes getById(Integer id) {
        return dishesDAO.getById(id);
    }

    @Override
    public List<Dishes> findAll() {
        return dishesDAO.findAll();
    }
}
