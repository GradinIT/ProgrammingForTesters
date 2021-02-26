package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import static org.junit.Assert.assertEquals;

public class TestModelMapper {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModel() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                //Skriver getId() för att få ut rätt ID, då EmployeeID extendar till Entity och därifrån vidare till EntityID-
                //samt att när model mappas så ropar den redan då på getId() och därav ropas den endast från Employee här
                () -> Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(),model.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(),model.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE.getLastName(),model.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE.getSalary(),model.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE.getFullTime(),model.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE.getDepartmentId(),model.getDepartmentId())
        );
    }

    @Test
    public void testEmployeeModelToEmployee() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                //Detta blir samma fall bara att vi vänder på det, hämtar getID från Employee men inte från Employee_Model
                //då employee-model är inte byggd efter interfacet och är bara istället utan koppling
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),employee.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(),employee.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employee.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId())
        );
    }
}
