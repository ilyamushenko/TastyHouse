package vsu.netcracker.project.database.service;

import vsu.netcracker.project.database.models.TableStatus;

import java.util.List;

public interface TableStatusService {

    TableStatus addStatus(TableStatus status);

    void delete(Integer id);

    TableStatus editStatus(TableStatus status);

    TableStatus findByTitle(String title);

    List<TableStatus> findAll();
}
