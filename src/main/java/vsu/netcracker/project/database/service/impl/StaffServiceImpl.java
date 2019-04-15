package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.StaffDAO;
import vsu.netcracker.project.database.models.Staff;
import vsu.netcracker.project.database.service.StaffService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffDAO staffDAO;

    @Autowired
    public StaffServiceImpl(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    @Override
    public Staff addStaff(Staff staff) {
        return staffDAO.saveAndFlush(staff);
    }

    @Override
    public void delete(Integer id) {
        staffDAO.deleteById(id);
    }

    @Override
    public Staff editStaff(Staff staff) {
        return staffDAO.saveAndFlush(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffDAO.findAll();
    }
}
