package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.DishStatusDAO;
import vsu.netcracker.project.database.models.DishStatus;
import vsu.netcracker.project.database.service.DishStatusService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class DishStatusServiceImpl implements DishStatusService {

    @Autowired
    private DishStatusDAO dishStatusDAO;

    @Override
    public DishStatus addStatus(DishStatus status) {
        return dishStatusDAO.saveAndFlush(status);
    }

    @Override
    public void delete(Integer id) {
        dishStatusDAO.deleteById(id);
    }

    @Override
    public DishStatus editStatus(DishStatus status) {
        return dishStatusDAO.saveAndFlush(status);
    }

    @Override
    public List<DishStatus> findAll() {
        return dishStatusDAO.findAll();
    }

    @Override
    public DishStatus findByTitle(String title) {
        return dishStatusDAO.findByTitle(title);
    }
}
