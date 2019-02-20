package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Staff;

import java.util.List;

public interface StaffService {

    Staff addStaff(Staff staff);

    void delete(Integer id);

    Staff editStaff(Staff staff);

    Staff findByEmail(String email);

    List<Staff> findAll();
}
