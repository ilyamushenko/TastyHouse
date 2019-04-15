package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.RestaurantTableDAO;
import vsu.netcracker.project.database.models.RestaurantTable;
import vsu.netcracker.project.database.service.RestaurantTableService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableDAO restaurantTableDAO;

    @Autowired
    public RestaurantTableServiceImpl(RestaurantTableDAO restaurantTableDAO) {
        this.restaurantTableDAO = restaurantTableDAO;
    }

    @Override
    public RestaurantTable addTable(RestaurantTable restaurantTable) {
        return restaurantTableDAO.saveAndFlush(restaurantTable);
    }

    @Override
    public void delete(Integer id) {
        restaurantTableDAO.deleteById(id);
    }

    @Override
    public RestaurantTable editTable(RestaurantTable restaurantTable) {
        return restaurantTableDAO.saveAndFlush(restaurantTable);
    }

    @Override
    public List<RestaurantTable> findAll() {
        return restaurantTableDAO.findAll();
    }

    @Override
    public List<RestaurantTable> findAll(Sort sort) {
        return restaurantTableDAO.findAll(sort);
    }

    @Override
    public RestaurantTable findById(Integer id) {
        return restaurantTableDAO.getById(id);
    }
}
