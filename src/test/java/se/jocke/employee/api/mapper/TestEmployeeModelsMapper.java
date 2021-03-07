package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelsMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test employee models mapper list")
public class TestEmployeeModelsMapper {

    @Test
    @DisplayName("When we want to map a list of <Employee> to list of <Employeemodel>")
    public void testEmployeeModelsMapper() {
        List<Employee> employees = Collections.singletonList(EmployeeTestBuilder.builder().build());
        List<EmployeeModel> employeeModelList = EmployeeModelsMapper.map(employees);

        Assertions.assertAll(
                () -> assertFalse(employeeModelList.isEmpty()),
                () -> assertNotNull(employeeModelList),
                () -> assertEquals(1, employeeModelList.size())
        );
    }
}
