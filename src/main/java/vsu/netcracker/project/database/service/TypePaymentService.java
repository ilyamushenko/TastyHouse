package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.TypePayment;

import java.util.List;

public interface TypePaymentService {

    TypePayment addTypePayment(TypePayment typePayment);

    void delete(Integer id);

    TypePayment editTypePayment(TypePayment typePayment);

    TypePayment findByTitle(String title);

    List<TypePayment> findAll();
}
