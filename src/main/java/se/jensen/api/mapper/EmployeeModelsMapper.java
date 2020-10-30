package se.jensen.api.mapper;

import se.jensen.api.EmployeeModel;
import se.jensen.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModelsMapper {
    public static List<EmployeeModel> map(List<Employee> employees) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (Employee employee : employees) {
            employeeModels.add(EmployeeModelMapper.map(employee));
        }
        return employeeModels;
    }
}
