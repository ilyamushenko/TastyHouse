package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.Staff;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface StaffDAO extends CrudRepository<Staff, Integer> {

    Staff findByEmail(String email);

    List<Staff> findAll();
}
