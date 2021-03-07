package se.jocke.employee.api.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateEmployeeModel {
        private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();


        @Test
        public void testCreateEmployeeModel() {
            EmployeeModel employee = EmployeeModel.builder()
                    .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                    .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                    .firstName(EMPLOYEE_MODEL.getFirstName())
                    .lastName(EMPLOYEE_MODEL.getLastName())
                    .salary(EMPLOYEE_MODEL.getSalary())
                    .fullTime(EMPLOYEE_MODEL.getFullTime())
                    .build();

            Assertions.assertAll(
                    () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId()),
                    () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId()),
                    () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName()),           //  från EmployeeModelTestBuilder
                    () -> assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName()),
                    () -> assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary()),
                    () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime()),
                    () -> assertEquals(EMPLOYEE_MODEL,employee)
            );
        }

    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }


}
