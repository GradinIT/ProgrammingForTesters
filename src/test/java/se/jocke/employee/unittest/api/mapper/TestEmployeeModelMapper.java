package se.jocke.employee.unittest.api.mapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployeeModelMapper {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .departmentId(EMPLOYEE.getDepartmentId())
            .employeeId(EMPLOYEE.getEmployeeId().getId())
            .fullTime(EMPLOYEE.getFullTime())
            .salary(EMPLOYEE.getSalary())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .build();

    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(),employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(),employeeModel.getEmployeeId()); // getId?
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getSalary(),employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFirstName(),employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employeeModel.getLastName());
    }
    @Test
    public void testThatEmployeeIsEqualToEmployeeModel() {

        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertEquals(EMPLOYEE,employee); //EMPLOYEE_MODEL?
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId());
      //  Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId()employee.getEmployeeId().getId()); // getId?
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employee.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName());
    }

}
