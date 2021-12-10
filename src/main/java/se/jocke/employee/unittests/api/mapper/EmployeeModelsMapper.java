package se.jocke.employee.unittests.api.mapper;

import lombok.EqualsAndHashCode;
import se.jocke.employee.unittests.api.EmployeeModel;
import se.jocke.employee.unittests.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;
@EqualsAndHashCode
public class EmployeeModelsMapper {
    public static List<EmployeeModel> map(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeModelMapper::map)
                .collect(Collectors.toList());
    }
}
