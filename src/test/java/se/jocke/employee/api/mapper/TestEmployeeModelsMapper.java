package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.api.mapper.EmployeeModelsMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class TestEmployeeModelsMapper {
    private final List<Employee> EMPLOYEE_LIST = EmployeeTestBuilder.buildList();
    private final List<EmployeeModel> EMPLOYEE_MODEL_LIST = EmployeeModelTestBuilder.buildList();
    @Test
    public void testEmployeesToEmployeeModelsMapping(){
        List<EmployeeModel> employeeModels = EmployeeModelsMapper.map(EMPLOYEE_LIST);

        Assertions.assertAll(
                ()->assertEquals(EMPLOYEE_MODEL_LIST, employeeModels)
        );
    }

   
}
