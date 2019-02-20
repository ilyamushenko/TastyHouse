package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Dishes;

import java.util.List;

public interface DishesService {

    Dishes addDish(Dishes dish);

    void delete(Integer id);

    Dishes editDish(Dishes dish);

    Dishes getByName(String name);

    Dishes getById(Integer id);

    List<Dishes> findAll();
}
