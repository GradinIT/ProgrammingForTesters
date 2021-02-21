package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {

    /*  private final DepartmentModel DEPARTMENT_MODEL = DepartmentModelTestBuilder.builder().build();
     */

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    /* @Test
    public void testCreateDepartmentModel() {
        DepartmentModel department = DepartmentModel.builder()
                .departmentId(DEPARTMENT_MODEL.getDepartmentId())
                .departmentName(DEPARTMENT_MODEL.getDepartmentName())
                .build();

        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentName(), department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentId(), department.getDepartmentId());
        Assertions.assertEquals(DEPARTMENT_MODEL,department);
    }*/

    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employee = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL, employee);
    }

    /*  @Test
    public void testCreateDepartmentModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            DepartmentModel.builder().departmentName(DEPARTMENT_MODEL.getDepartmentName()).build();
        });
    }*/

    @Test
    public void testCreateEmployeeModelThrowsException () {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });
    }

}
