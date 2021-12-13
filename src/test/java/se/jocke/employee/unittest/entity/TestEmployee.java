package se.jocke.employee.unittest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployee {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    //Testar att employee blir skapad
    public void testThatEmployeeIsCreated () {
        //skapar employee med hjälp av employee buildern
        Employee employee = Employee.builder()
                //hämtar alla parametrar i EPLOYEE in till employee
                .departmentId(EMPLOYEE.getDepartmentId())
                .employeeId(EMPLOYEE.getEmployeeId())
                .fullTime(EMPLOYEE.getFullTime())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .build();

        //kollar EMPLOYEE är samma som employee
        Assertions.assertEquals(EMPLOYEE,employee);
        //jämför att firstname stämmer
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        //jämför att departmentid stämmer
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        //skapar en employye to string---
        String EMPLOYEE_TO_STRING_VALUE = EMPLOYEE.toString();
        //skapar en employye to string---
        String employee_to_string_value = employee.toString();
        //jämför värden för att se att det är samma
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);


    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingDepartmentId() {
        //Testar att vi får ett null värde när den anropar endast department id
        Assertions.assertThrows(NullPointerException.class ,
                //kör en parameter i buildern
                () -> Employee.builder().departmentId(EMPLOYEE.getDepartmentId()).build());

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeLastName() {
        //Testar att vi får ett null värde när den anropar  allt förutom lastname
        Assertions.assertThrows(NullPointerException.class ,
                () -> Employee.builder().employeeId(EMPLOYEE.getEmployeeId())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .firstName(EMPLOYEE.getFirstName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .salary(EMPLOYEE.getSalary())
                      //  .lastName(EMPLOYEE.getLastName()) - testar att denna inte är med
                        .build());

    }
}

