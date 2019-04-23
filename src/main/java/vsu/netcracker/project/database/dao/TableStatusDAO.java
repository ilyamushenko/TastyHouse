package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.TableStatus;

/**
 * @author Кушнеренко Виктор
 */
public interface TableStatusDAO extends JpaRepository<TableStatus, Integer> {

    TableStatus findByTitle(String title);
}
