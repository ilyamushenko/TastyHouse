package vsu.netcracker.project.database.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vsu.netcracker.project.database.models.Orders;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OrdersDAO extends CrudRepository<Orders, Long> {

    Orders findByTableNumber(Long tableNumber);

    List<Orders> findAll();
}
