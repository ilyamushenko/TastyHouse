package vsu.netcracker.project.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.netcracker.project.database.dao.TableStatusDAO;
import vsu.netcracker.project.database.models.TableStatus;
import vsu.netcracker.project.database.service.TableStatusService;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Service
public class TableStatusServiceImpl implements TableStatusService {

    private final TableStatusDAO tableStatusDAO;

    @Autowired
    public TableStatusServiceImpl(TableStatusDAO tableStatusDAO) {
        this.tableStatusDAO = tableStatusDAO;
    }

    @Override
    public TableStatus addStatus(TableStatus status) {
        return tableStatusDAO.saveAndFlush(status);
    }

    @Override
    public void delete(Integer id) {
        tableStatusDAO.deleteById(id);
    }

    @Override
    public TableStatus editStatus(TableStatus status) {
        return tableStatusDAO.saveAndFlush(status);
    }

    @Override
    public TableStatus findByTitle(String title) {
        return tableStatusDAO.findByTitle(title);
    }

    @Override
    public List<TableStatus> findAll() {
        return tableStatusDAO.findAll();
    }
}
