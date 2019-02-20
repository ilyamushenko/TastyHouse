package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.Staff;

public interface StaffDAO extends JpaRepository<Staff, Integer> {

    @Query("select s from Staff s where s.email = :email")
    Staff findByEmail(@Param("email") String email);
}
