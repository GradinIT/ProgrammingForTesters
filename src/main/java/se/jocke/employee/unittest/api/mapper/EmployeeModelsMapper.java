package se.jocke.employee.unittest.api.mapper;

import se.jocke.employee.unittest.api.EmployeeModel;
import se.jocke.employee.unittest.api.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModelsMapper {
    public static List<EmployeeModel> map(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeModelMapper::map)
                .collect(Collectors.toList());
    }
}
