package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.RoleStaffDAO;
import vsu.netcracker.project.database.models.RoleStaff;
import vsu.netcracker.project.database.service.RoleStaffService;

import java.util.List;

@Service
public class RoleStaffServiceImpl implements RoleStaffService {

    @Autowired
    private RoleStaffDAO roleStaffDAO;

    @Override
    public RoleStaff addRoleStaff(RoleStaff roleStaff) {
        return roleStaffDAO.saveAndFlush(roleStaff);
    }

    @Override
    public void delete(Integer id) {
        roleStaffDAO.deleteById(id);
    }

    @Override
    public RoleStaff editRoleStaff(RoleStaff roleStaff) {
        return roleStaffDAO.saveAndFlush(roleStaff);
    }

    @Override
    public RoleStaff findByTitle(String title) {
        return roleStaffDAO.findByTitle(title);
    }

    @Override
    public List<RoleStaff> findAll() {
        return roleStaffDAO.findAll();
    }
}
