package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.TypeDish;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface TypeDishService {

    TypeDish addTypeDish(TypeDish typeDish);

    void delete(Integer id);

    TypeDish editTypeDish(TypeDish typeDish);

    TypeDish findByTitle(String title);

    TypeDish getById(Integer id);

    List<TypeDish> findAll();
}
