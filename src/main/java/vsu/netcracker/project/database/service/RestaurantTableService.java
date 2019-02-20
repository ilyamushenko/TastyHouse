package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {

    RestaurantTable addTable(RestaurantTable restaurantTable);

    void delete(Integer id);

    RestaurantTable editTable(RestaurantTable restaurantTable);

    List<RestaurantTable> findAll();

    RestaurantTable findById(Integer id);
}
