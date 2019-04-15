package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.Staff;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface StaffService {

    Staff addStaff(Staff staff);

    void delete(Integer id);

    Staff editStaff(Staff staff);

    List<Staff> findAll();
}
