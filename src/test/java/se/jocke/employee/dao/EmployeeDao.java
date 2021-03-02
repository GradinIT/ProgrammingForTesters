package se.jocke.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jocke.dao.EmployeeDatabaseEntry;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeDatabaseEntry, Integer> {
}
