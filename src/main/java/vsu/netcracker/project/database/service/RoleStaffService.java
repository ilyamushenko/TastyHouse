package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.RoleStaff;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface RoleStaffService {

    RoleStaff addRoleStaff(RoleStaff roleStaff);

    void delete(Integer id);

    RoleStaff editRoleStaff(RoleStaff roleStaff);

    RoleStaff findByTitle(String title);

    List<RoleStaff> findAll();
}
