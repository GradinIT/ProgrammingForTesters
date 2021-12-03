package se.jocke.employee.old.api.mapper;

import se.jocke.employee.old.api.EmployeeModel;
import se.jocke.employee.old.entity.Employee;
import se.jocke.employee.old.entity.EmployeeID;

public class EmployeeModelMapper {
    public static Employee map(EmployeeModel model) {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(model.getEmployeeId()).build())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .salary(model.getSalary())
                .fullTime(model.getFullTime())
                .departmentId(model.getDepartmentId())
                .build();
    }

    public static EmployeeModel map(Employee employee) {
        return EmployeeModel.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build();
    }
}
