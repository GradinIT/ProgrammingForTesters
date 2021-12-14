package se.jocke.employee.api.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelsMapper;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class TestEmployeeModdelsMapper {
    @Test
    public void testEmployeeModelsMapper() {
        List<Employee> EMPLOYEES = Arrays.asList(EmployeeTestBuilder.builder().build());
        List<EmployeeModel> EMPLOYEE_MODELS= Arrays.asList(EmployeeModelTestBuilder.builder().build());
        List<EmployeeModel> employeeModels = EmployeeModelsMapper.map(EMPLOYEES);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(employeeModels),
                ()->Assertions.assertEquals(EMPLOYEE_MODELS,employeeModels)
        );
    }
}