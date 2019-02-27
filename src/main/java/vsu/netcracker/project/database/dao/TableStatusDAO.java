package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.TableStatus;

/**
 * @author Кушнеренко Виктор
 */
public interface TableStatusDAO extends JpaRepository<TableStatus, Integer> {

    @Query("select ts from TableStatus ts where ts.title = :title")
    TableStatus findByTitle(@Param("title") String title);
}
