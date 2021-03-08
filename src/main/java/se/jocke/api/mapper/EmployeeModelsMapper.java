package se.jocke.api.mapper;

import se.jocke.api.EmployeeModel;
import se.jocke.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModelsMapper {
    public static List<EmployeeModel> map(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeModelMapper::map)
                .collect(Collectors.toList());
    }
}
