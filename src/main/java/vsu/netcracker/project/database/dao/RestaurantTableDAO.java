package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Orders;
import vsu.netcracker.project.database.models.RestaurantTable;

import java.util.List;

public interface RestaurantTableDAO extends JpaRepository<RestaurantTable, Integer> {

    @Query("select rt from RestaurantTable rt where rt.id = :id")
    RestaurantTable getById(@Param("id") Integer id);
}
