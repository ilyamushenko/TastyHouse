package vsu.netcracker.project.database.service;

import org.springframework.data.domain.Sort;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {

    RestaurantTable addTable(RestaurantTable restaurantTable);

    void delete(Integer id);

    RestaurantTable editTable(RestaurantTable restaurantTable);

    List<RestaurantTable> findAll();

    List<RestaurantTable> findAll(Sort sort);

    RestaurantTable findById(Integer id);
}
