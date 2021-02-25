package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

@DisplayName("When we create employee model")
public class TestCreateEmployeeModel {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    @Test
    @DisplayName("Given that all parameters are correct")
    public void testCreateEmployeeModel() {

        EmployeeModel employeeModel = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId())
        );
    }

    @Nested
    @DisplayName("Given that we build employee model without a NonNull parameter")
    class testNullPointerException {

        @Test
        @DisplayName("When we try to build employee model without employee ID")
        public void testCreateEmployeeModelWithoutEmployeeID() {
            Assertions.assertThrows(NullPointerException.class,
                    () -> EmployeeModel.builder()
                    .firstName(EMPLOYEE_MODEL.getFirstName())
                    .lastName(EMPLOYEE_MODEL.getLastName())
                    .salary(EMPLOYEE_MODEL.getSalary())
                    .fullTime(EMPLOYEE_MODEL.getFullTime())
                    .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                    .build()
            );
        }

        @Test
        @DisplayName("When we try to build employee model without last name")
        public void testCreateEmployeeModelWithOnlyLastName() {
            Assertions.assertThrows(NullPointerException.class,
                    () -> EmployeeModel.builder()
                            .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                            .firstName(EMPLOYEE_MODEL.getFirstName())
                            .salary(EMPLOYEE_MODEL.getSalary())
                            .fullTime(EMPLOYEE_MODEL.getFullTime())
                            .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                            .build()
            );
        }
    }
}
