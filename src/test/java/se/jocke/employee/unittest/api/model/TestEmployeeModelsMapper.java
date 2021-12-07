package se.jocke.employee.unittest.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelsMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeModelTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;

public class TestEmployeeModelsMapper {
    @Test
    public void testEmployeeModelsMapper() {
        List<Employee> EMPLOYEES = Arrays.asList(EmployeeTestBuilder.build());
        List<EmployeeModel> EMPLOYEE_MODELS = Arrays.asList(EmployeeModelTestBuilder.build());
        List<EmployeeModel> employeeModels = EmployeeModelsMapper.map(EMPLOYEES);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(employeeModels),
                ()->Assertions.assertEquals(EMPLOYEE_MODELS,employeeModels)
        );
    }
}
