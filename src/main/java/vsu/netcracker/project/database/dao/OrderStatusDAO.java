package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.OrderStatus;

public interface OrderStatusDAO extends JpaRepository<OrderStatus, Integer> {

    @Query("select s from OrderStatus s where s.title = :title")
    OrderStatus findByTitle(@Param("title") String title);
}
