package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.DishStatus;

public interface DishStatusDAO extends JpaRepository<DishStatus, Integer> {

    @Query("select ds from DishStatus ds where ds.title = :title")
    DishStatus findByTitle(@Param("title") String title);
}
