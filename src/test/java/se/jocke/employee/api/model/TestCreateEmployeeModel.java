package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import static org.mockito.Mockito.when;

public class TestCreateEmployeeModel {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();


        @Test
        public void testEmployeeValues () {
            EmployeeModel testModel = EmployeeModel.builder()
                    .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                    .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                    .firstName(EMPLOYEE_MODEL.getFirstName())
                    .lastName(EMPLOYEE_MODEL.getLastName())
                    .fullTime(EMPLOYEE_MODEL.getFullTime())
                    .salary(EMPLOYEE_MODEL.getSalary()).build();


            Assertions.assertAll(
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), testModel.getEmployeeId()),
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), testModel.getDepartmentId()),
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), testModel.getFirstName()),
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), testModel.getLastName()),
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), testModel.getFullTime()),
                    () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), testModel.getSalary())
            );
        }

}
