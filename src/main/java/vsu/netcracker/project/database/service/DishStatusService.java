package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.DishStatus;

import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
public interface DishStatusService {

    DishStatus addStatus(DishStatus status);

    void delete(Integer id);

    DishStatus editStatus(DishStatus status);

    List<DishStatus> findAll();

    DishStatus findByTitle(String title);
}
