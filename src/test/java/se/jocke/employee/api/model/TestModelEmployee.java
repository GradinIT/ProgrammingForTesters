package se.jocke.employee.api.model;


import org.junit.jupiter.api.Assertions;


import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.department.entity.Employee;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestModelEmployee {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();



    @Test


    public void testCreateEmployeeModel () {

        EmployeeModel employee = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build();



    }


    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });
    }

}
