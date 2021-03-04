package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.DepartmentModelMapper;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.builder.DepartmentModelTestBuilder;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {

    // byt ut department mot employee


    // Skapar objekt utav Employee och EmployeeModel med hjälp utav en testbuilder.
    private final Employee employeeTest = EmployeeTestBuilder.builder().build();
    private final EmployeeModel employeeModelTest = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testEmployeeToEmployeeModelMapping() {
        // skapar objekt utav EmployeeModel med modelMapper
        EmployeeModel model = EmployeeModelMapper.map(employeeTest);
        // Testar om testBuilder objektet och modelMapper objektet inehåller samma information
        Assertions.assertAll(
                () -> assertEquals(employeeTest.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(employeeTest.getLastName(), model.getLastName())
        );
    }

    @Test
    public void testEmployeeModelToEmployeeMapping() {
        // skapar objekt utav Employee med modelMapper
        Employee employee = EmployeeModelMapper.map(employeeModelTest);
        // Testar om testbuilder objektet och modelMapper objektet inehåller samma information
        Assertions.assertAll(
                () -> assertEquals(employeeModelTest.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(employeeModelTest.getLastName(), employee.getLastName())
        );
    }
}
