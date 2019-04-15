package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.RoleStaff;

/**
 * @author Кушнеренко Виктор
 */
public interface RoleStaffDAO extends JpaRepository<RoleStaff, Integer> {

    RoleStaff findByTitle(String title);
}
