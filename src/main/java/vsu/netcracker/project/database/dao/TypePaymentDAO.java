package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.TypePayment;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TypePaymentDAO extends CrudRepository<TypePayment, Integer> {

    TypePayment findByTitle(String title);

    List<TypePayment> findAll();
}
