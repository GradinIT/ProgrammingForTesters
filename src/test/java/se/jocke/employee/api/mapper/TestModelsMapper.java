package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

import java.util.Arrays;
import java.util.List;


public class TestModelsMapper {
    // Both Employee.EmployeeBuilder & EmployeeModel.EmployeeModelBuilder will be tested with EmployeeModelsMapper

    // testEmployeeToEmployeeModelsMapping()
    // Create Employee List "employee" from EmployeeBuilder -> asList
    private final List<Employee> employees = Arrays.asList(EmployeeTestBuilder.builder().build());
    // Create EmployeeModel List "employeeModelsMapper" from EmployeeModelsMapper
    private final List<EmployeeModel> employeeModelsMapper = EmployeeModelsMapper.map(employees);


    @Test
    @DisplayName("Test EmployeeTestBuilder with EmployeeModelsMapper")
    public void testEmployeeToEmployeeModelsMapping() {
        Assertions.assertNotNull(employees);
        Assertions.assertNotNull(employeeModelsMapper);

        Assertions.assertAll(
                () -> Assumptions.assumeTrue(employees.size() == 1),
                () -> Assumptions.assumeTrue(employees.size() == employeeModelsMapper.size()),
                () -> Assertions.assertEquals(employees.get(0).getEmployeeId().getId(), employeeModelsMapper.get(0).getEmployeeId()),
                () -> Assertions.assertEquals(employees.get(0).getFirstName(), employeeModelsMapper.get(0).getFirstName()),
                () -> Assertions.assertEquals(employees.get(0).getLastName(), employeeModelsMapper.get(0).getLastName()),
                () -> Assertions.assertEquals(employees.get(0).getSalary(), employeeModelsMapper.get(0).getSalary()),
                () -> Assertions.assertEquals(employees.get(0).getFullTime(), employeeModelsMapper.get(0).getFullTime()),
                () -> Assertions.assertEquals(employees.get(0).getDepartmentId(), employeeModelsMapper.get(0).getDepartmentId()));
    }

    // testEmployeeToEmployeeModelsMapping()
    // Create EmployeeModel List "EMPLOYEE_MODEL_TEST_BUILDER" from EmployeeModelTestBuilder -> asList
    private final List<EmployeeModel> EMPLOYEE_MODEL_TEST_BUILDER = Arrays.asList(EmployeeModelTestBuilder.builder().build());
    // Create Employee List "EMPLOYEES_MODEL" from EmployeeModelMapper at idx 0 -> asList
    private final List<Employee> EMPLOYEES_MODEL = Arrays.asList(EmployeeModelMapper.map(EMPLOYEE_MODEL_TEST_BUILDER.get(0)));
    // Create EmployeeModel List "EMPLOYEE_MODELS_MAPPER" from EmployeeModelsMapper
    private final List<EmployeeModel> EMPLOYEE_MODELS_MAPPER = EmployeeModelsMapper.map(EMPLOYEES_MODEL);

    @Test
    @DisplayName("Test EmployeeModelTestBuilder with EmployeeModelsMapper")
    public void testEmployeeModelToEmployeeModelsMapping() {
        Assertions.assertNotNull(EMPLOYEES_MODEL);
        Assertions.assertNotNull(EMPLOYEE_MODELS_MAPPER);

        Assertions.assertAll(
                () -> Assumptions.assumeTrue(EMPLOYEES_MODEL.size() == 1),
                () -> Assumptions.assumeTrue(EMPLOYEES_MODEL.size() == EMPLOYEE_MODELS_MAPPER.size()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getEmployeeId().getId(), EMPLOYEE_MODELS_MAPPER.get(0).getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getFirstName(), EMPLOYEE_MODELS_MAPPER.get(0).getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getLastName(), EMPLOYEE_MODELS_MAPPER.get(0).getLastName()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getSalary(), EMPLOYEE_MODELS_MAPPER.get(0).getSalary()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getFullTime(), EMPLOYEE_MODELS_MAPPER.get(0).getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEES_MODEL.get(0).getDepartmentId(), EMPLOYEE_MODELS_MAPPER.get(0).getDepartmentId()));
    }
}
