package se.jensen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.jensen.entity.Employee;
@Repository
public interface EmployeeDao extends JpaRepository<EmployeePOJO, Integer> {
}
