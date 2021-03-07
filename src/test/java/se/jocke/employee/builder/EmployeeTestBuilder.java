package se.jocke.employee.builder;

import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTestBuilder {
    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(100).build())
                .firstName("Testare")
                .lastName("Testarsson")
                .salary(BigDecimal.valueOf(25000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(1);
    }

    public static List<Employee> buildList(){
        List<Employee> buildList = new ArrayList<>();
        buildList.add(
        Employee.builder().employeeId(EmployeeID.builder().id(100).build())
                .firstName("Testare")
                .lastName("Testarsson")
                .salary(BigDecimal.valueOf(25000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(1).build());
        buildList.add(
        Employee.builder().employeeId(EmployeeID.builder().id(200).build())
                .firstName("Coder")
                .lastName("Codersson")
                .salary(BigDecimal.valueOf(35000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(2).build());
        return buildList;
    }
}
