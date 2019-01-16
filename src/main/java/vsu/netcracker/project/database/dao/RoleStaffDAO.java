package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.RoleStaff;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RoleStaffDAO extends CrudRepository<RoleStaff, Long> {

    RoleStaff findByTitle(String title);

    List<RoleStaff> findAll();
}
