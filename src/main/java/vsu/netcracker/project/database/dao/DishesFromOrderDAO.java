package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.DishesFromOrder;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DishesFromOrderDAO extends CrudRepository<DishesFromOrder, Long> {

    List<DishesFromOrder> findAll();
}
