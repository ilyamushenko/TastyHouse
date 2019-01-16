package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.Statuses;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface StatusesDAO extends CrudRepository<Statuses, Long> {

    Statuses findByTitle(String title);

    List<Statuses> findAll();
}
