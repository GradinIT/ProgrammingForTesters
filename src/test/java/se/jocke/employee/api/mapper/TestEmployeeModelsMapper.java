package se.jocke.employee.api.mapper;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelsMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeModelTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;

public class TestEmployeeModelsMapper {
    @Test
    public void testEmployeeModelsMapper(){
        List<Employee> EMPLOYEES = Arrays.asList(EmployeeTestBuilder.bygg());
        List<EmployeeModel> EMPLOYEES_MODEL = Arrays.asList(EmployeeModelTestBuilder.bygg());
        List<EmployeeModel> employeeModels = EmployeeModelsMapper.map(EMPLOYEES);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(employeeModels),
                ()->Assertions.assertEquals(EMPLOYEES_MODEL, employeeModels)
        );
    }
}
