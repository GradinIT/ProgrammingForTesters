package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.EmployeeModel;
import se.jensen.api.mapper.EmployeeModelMapper;
import se.jensen.entity.Employee;
import se.jensen.exercise.test.builder.EmployeeModelTestBuilder;
import se.jensen.exercise.test.builder.EmployeeTestBuilder;

public class TestEmployeeModelMapper {
    @Test
    public void testMapFromEmployeeToEmployeeModel() {
        Employee expected = EmployeeTestBuilder.build();
        EmployeeModel employeeModel = EmployeeModelMapper.map(expected);
        Assert.assertNotNull(employeeModel);
        Assert.assertEquals(expected.getEmployeeId().getId(),employeeModel.getEmployeeId());
        Assert.assertEquals(expected.getDepartmentId(),employeeModel.getDepartmentId());
        Assert.assertEquals(expected.getFirstName(),employeeModel.getFirstName());
        Assert.assertEquals(expected.getLastName(),employeeModel.getLastName());
        Assert.assertEquals(expected.getFullTime(),employeeModel.getFullTime());
        Assert.assertEquals(expected.getSalary(),employeeModel.getSalary());
    }
    @Test
    public void testMapFromEmployeeModelToEmployee() {
        EmployeeModel expected = EmployeeModelTestBuilder.build();
        Employee employee = EmployeeModelMapper.map(expected);
        Assert.assertNotNull(employee);
        Assert.assertEquals(expected.getEmployeeId(),employee.getEmployeeId().getId());
        Assert.assertEquals(expected.getDepartmentId(),employee.getDepartmentId());
        Assert.assertEquals(expected.getFirstName(),employee.getFirstName());
        Assert.assertEquals(expected.getLastName(), employee.getLastName());
        Assert.assertEquals(expected.getFullTime(),employee.getFullTime());
        Assert.assertEquals(expected.getSalary(),employee.getSalary());

    }
}
