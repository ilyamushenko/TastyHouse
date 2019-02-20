package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.RoleStaff;

public interface RoleStaffDAO extends JpaRepository<RoleStaff, Integer> {

    @Query("select r from RoleStaff r where r.title = :title")
    RoleStaff findByTitle(@Param("title") String title);
}
