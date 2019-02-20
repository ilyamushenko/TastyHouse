package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.TypeDish;

import java.util.List;

public interface TypeDishService {

    TypeDish addTypeDish(TypeDish typeDish);

    void delete(Integer id);

    TypeDish editTypeDish(TypeDish typeDish);

    TypeDish findByTitle(String title);

    List<TypeDish> findAll();
}
