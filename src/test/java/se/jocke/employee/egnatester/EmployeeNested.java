package se.jocke.employee.egnatester;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class EmployeeNested {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    @DisplayName("TillhOrEjUppgiftKollaEmplyeeFinns")
    public void testarAttEmplyeeHarEttNamnTilldelat (){
        assumeTrue(EMPLOYEE.getFirstName() != null);
        assertTrue(EMPLOYEE.getFullTime());


    }
}
