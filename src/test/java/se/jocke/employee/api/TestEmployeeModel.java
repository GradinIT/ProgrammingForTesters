package se.jocke.employee.api;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.yaml.snakeyaml.events.Event;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class TestEmployeeModel {
    private static final Employee EMPLOYEE_MODEL = EmployeeTestBuilder.build();


    @Test
    public void testThatEmployeeIsCreated (){
        EmployeeModel employeeModel = EmployeeModel.builder()
               .employeeId(EMPLOYEE_MODEL.getEmployeeId().getId()) // varför är den en builder? måste ha med .getId()?
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime()) // alla .deklartioner måste vara med annars är dom null
                .build();  // kom ihåg att skriva .build() när man skapar en builder

       /* String EMPLOYEEMODEL_STRING_VALUE = EMPLOYEE_MODEL.toString();
        String emplyeemodel_string_value = employeeModel.toString(); // behövs ej? varför testas? */
        Assertions.assertEquals(EMPLOYEE_MODEL.toString(), employeeModel.toString());

        Assertions.assertEquals(EMPLOYEE_MODEL, employeeModel); //jämför hårdkodade EMPLOYEE med emplyeen skapad med builder ovan
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employeeModel.getSalary());
    }

    @Test

    public void testThatNullCheckWorks(){
        Assertions.assertThrows(NullPointerException.class , () -> Employee.builder()
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .salary(EMPLOYEE_MODEL.getSalary())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build());
    }


    @Test
    public void testThatNullpointerisRaisedWhenOnlyProvidingSalary() {
        Assertions.assertThrows(NullPointerException.class, //kollar att vi får ett nullpointexception
                () -> Employee.builder().salary(EMPLOYEE_MODEL.getSalary()).build()); //När vi kör .builder på EMPLOYEE med bara salary

    }

    @Test
    @DisplayName("TillhOrEjUppgiftKollaEmplyeeFinns")
    public void testarAttEmplyeeHarEttNamnTilldelat (){
        assumeTrue(EMPLOYEE_MODEL.getFirstName() != null);
        assertTrue(EMPLOYEE_MODEL.getFullTime());


    }


}
