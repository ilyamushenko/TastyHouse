package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.Dishes;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DishesDAO extends CrudRepository<Dishes, Long> {

    List<Dishes> findByName(String name);
    Dishes findById(long id);
    List<Dishes> findAll();
}
