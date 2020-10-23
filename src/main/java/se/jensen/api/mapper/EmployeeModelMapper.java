package se.jensen.api.mapper;

import se.jensen.api.EmployeeModel;
import se.jensen.entity.Employee;

public class EmployeeModelMapper {
    public static Employee map(EmployeeModel model) {
        return Employee.builder()
                .setEmployeeId(model.getEmployeeId())
                .setFirstName(model.getFirstName())
                .setLastName(model.getLastName())
                .setSalary(model.getSalary())
                .setFullTime(model.getFullTime())
                .build();
    }

    public static EmployeeModel map(Employee employee) {
        return EmployeeModel.builder()
                .setEmployeeId(employee.getEmployeeId())
                .setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setSalary(employee.getSalary())
                .setFullTime(employee.getFullTime())
                .build();
    }
}
