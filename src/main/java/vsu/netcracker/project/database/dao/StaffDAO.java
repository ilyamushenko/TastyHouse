package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.Staff;

/**
 * @author Кушнеренко Виктор
 */
public interface StaffDAO extends JpaRepository<Staff, Integer> {

}
