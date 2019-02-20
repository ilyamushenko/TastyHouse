package vsu.netcracker.project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.netcracker.project.database.models.TableStatus;

public interface TableStatusDAO extends JpaRepository<TableStatus, Integer> {
}
