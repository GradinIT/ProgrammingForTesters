package se.jensen.api.mapper;

import se.jensen.api.EmployeeModel;
import se.jensen.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModelsMapper {
    public static List<EmployeeModel> map(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeModelMapper::map)
                .collect(Collectors.toList());
    }
}
