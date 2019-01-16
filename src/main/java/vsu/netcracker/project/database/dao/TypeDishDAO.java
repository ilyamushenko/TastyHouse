package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.TypeDish;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TypeDishDAO extends CrudRepository<TypeDish, Long> {

    TypeDish findByTitle(String title);

    List<TypeDish> findAll();
}
