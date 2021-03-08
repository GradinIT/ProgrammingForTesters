package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {
    // Bygger en test instans utav EmployeeModel med hjälp utav våran EmployeeModelTestBuilder klass.
    // Denna klass har redan alla värden i sig
    private final EmployeeModel EmployeeTestInstance = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testCreateEmployeeModel() {
        // bygger en instans med hjälp utav våran riktiga builder och jämför den med Test instansen
        // matar in testinstansen värden i den riktiga instansen så vi kan jämnföra.
        // Vi behöver mata in alla värden för att variablerna i buildern är satta som no null

        EmployeeModel employee = EmployeeModel.builder()
                .employeeId(EmployeeTestInstance.getEmployeeId())
                .firstName(EmployeeTestInstance.getFirstName())
                .lastName(EmployeeTestInstance.getLastName())
                .salary(EmployeeTestInstance.getSalary())
                .fullTime(EmployeeTestInstance.getFullTime())
                .departmentId(EmployeeTestInstance.getDepartmentId())
                .build();
        Assertions.assertEquals(EmployeeTestInstance.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EmployeeTestInstance.getLastName(), employee.getLastName());
        Assertions.assertEquals(EmployeeTestInstance.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EmployeeTestInstance,employee);
    }

    @Test
    public void testCreateEmployeeModelThrowsException() {
        // testet kollar så att DepartmentModel skickar ett exeption när en variabel är null
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EmployeeTestInstance.getFirstName()).build();
        });
    }
}




