package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.TypeDish;

public interface TypeDishDAO extends JpaRepository<TypeDish, Integer> {

    @Query("select td from TypeDish td where td.title = :title")
    TypeDish findByTitle(@Param("title") String title);
}
