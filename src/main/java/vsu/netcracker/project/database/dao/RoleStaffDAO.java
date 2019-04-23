package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.RoleStaff;

/**
 * @author Кушнеренко Виктор
 */
public interface RoleStaffDAO extends JpaRepository<RoleStaff, Integer> {

    RoleStaff findByTitle(String title);
}
