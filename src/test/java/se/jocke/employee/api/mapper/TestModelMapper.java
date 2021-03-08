package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {

    // byt ut department mot employee


    // Skapar test objekt utav Employee och EmployeeModel med hjälp utav testbuilders.
    private final Employee employeeTest = EmployeeTestBuilder.builder().build();
    private final EmployeeModel employeeModelTest = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testEmployeeToEmployeeModelMapping() {
        // skapar objekt utav EmployeeModel med modelMapper och lägger in test värderna
        EmployeeModel model = EmployeeModelMapper.map(employeeTest);
        // Testar om testBuilder objektet och modelMapper objektet inehåller samma information
        Assertions.assertAll(
                () -> assertEquals(employeeTest.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(employeeTest.getLastName(), model.getLastName())
        );
    }

    @Test
    public void testEmployeeModelToEmployeeMapping() {
        // skapar objekt utav Employee med modelMapper och lägger in test värderna
        Employee employee = EmployeeModelMapper.map(employeeModelTest);
        // Testar om testbuilder objektet och modelMapper objektet inehåller samma information
        Assertions.assertAll(
                () -> assertEquals(employeeModelTest.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(employeeModelTest.getLastName(), employee.getLastName())
        );
    }
}
