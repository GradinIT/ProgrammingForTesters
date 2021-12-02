package se.jocke.employee.unittest.api.mapper;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.unittest.api.EmployeeModel;
import se.jocke.employee.test.builder.EmployeeTestBuilder;
import se.jocke.employee.unittest.entity.Employee;

public class TestEmployeeModelMapper {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .employeeId(EMPLOYEE.getEmployeeId())
            .departmentId(EMPLOYEE.getDepartmentId())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .fullTime(EMPLOYEE.getFullTime())
            .salary(EMPLOYEE.getSalary())
            .build();

    @Test
    public void testThatEmployeeIsEqualToEmployeeModel(){

        EmployeeModel employeemodel = EmployeeModelMapper.map(EMPLOYEE);

        Assertions.assertEquals(EMPLOYEE_MODEL,employeemodel);

        Assertions.assertEquals(EMPLOYEE.getEmployeeId(),employeemodel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(),employeemodel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(),employeemodel.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(),employeemodel.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(),employeemodel.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(),employeemodel.getFullTime());


    }
    @Test
    public void testThatEmployeeModelIsEqualToEmployee(){

        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);

        Assertions.assertEquals(EMPLOYEE,employee);

        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),employee.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employee.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(),employee.getFullTime());

    }
}
