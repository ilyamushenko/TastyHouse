package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.netcracker.project.database.models.TypePayment;

/**
 * @author Кушнеренко Виктор
 */
public interface TypePaymentDAO extends JpaRepository<TypePayment, Integer> {

    TypePayment findByTitle(String title);
}
