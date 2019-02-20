package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.TypePaymentDAO;
import vsu.netcracker.project.database.models.TypePayment;
import vsu.netcracker.project.database.service.TypePaymentService;

import java.util.List;

@Service
public class TypePaymentServiceImpl implements TypePaymentService {

    @Autowired
    private TypePaymentDAO typePaymentDAO;

    @Override
    public TypePayment addTypePayment(TypePayment typePayment) {
        return typePaymentDAO.saveAndFlush(typePayment);
    }

    @Override
    public void delete(Integer id) {
        typePaymentDAO.deleteById(id);
    }

    @Override
    public TypePayment editTypePayment(TypePayment typePayment) {
        return typePaymentDAO.saveAndFlush(typePayment);
    }

    @Override
    public TypePayment findByTitle(String title) {
        return typePaymentDAO.findByTitle(title);
    }

    @Override
    public List<TypePayment> findAll() {
        return typePaymentDAO.findAll();
    }
}
