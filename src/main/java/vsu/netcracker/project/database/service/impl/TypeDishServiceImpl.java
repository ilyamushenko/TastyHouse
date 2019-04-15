package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.TypeDishDAO;
import vsu.netcracker.project.database.models.TypeDish;
import vsu.netcracker.project.database.service.TypeDishService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class TypeDishServiceImpl implements TypeDishService {

    private final TypeDishDAO typeDishDAO;

    @Autowired
    public TypeDishServiceImpl(TypeDishDAO typeDishDAO) {
        this.typeDishDAO = typeDishDAO;
    }

    @Override
    public TypeDish addTypeDish(TypeDish typeDish) {
        return typeDishDAO.saveAndFlush(typeDish);
    }

    @Override
    public void delete(Integer id) {
        typeDishDAO.deleteById(id);
    }

    @Override
    public TypeDish editTypeDish(TypeDish typeDish) {
        return typeDishDAO.saveAndFlush(typeDish);
    }

    @Override
    public TypeDish findByTitle(String title) {
        return typeDishDAO.findByTitle(title);
    }

    @Override
    public TypeDish getById(Integer id) {
        return typeDishDAO.getById(id);
    }

    @Override
    public List<TypeDish> findAll() {
        return typeDishDAO.findAll();
    }
}
