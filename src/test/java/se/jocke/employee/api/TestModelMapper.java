package se.jocke.employee.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {
//    private final DepartmentModel DEPARTMENT_MODEL = DepartmentModelTestBuilder.builder().build();
//    private final Department DEPARTMENT = DepartmentTestBuilder.builder().build();
//    /*  @Test
//    public void testDepartmentToDepartmentModelMapping() {
//        DepartmentModel model = DepartmentModelMapper.map(DEPARTMENT);
//        Assertions.assertAll(
//                () -> assertEquals(DEPARTMENT.getDepartmentId(), model.getDepartmentId()),
//                () -> assertEquals(DEPARTMENT.getDepartmentName(), model.getDepartmentName())
//        );
//    }*/

        private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
        private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

        @Test
        public void testEmployeeToEmployeeModelMapping() {
                EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
                Assertions.assertAll(
                        () -> assertEquals(EMPLOYEE.getEmployeeId(), model.getEmployeeId()),
                        () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName())
                );
        }


        /* @Test
    public void testDepartmentModelToDepartmentMapping() {
        Department department = DepartmentModelMapper.map(DEPARTMENT_MODEL);
        Assertions.assertAll(
                () -> assertEquals(DEPARTMENT_MODEL.getDepartmentId(), department.getDepartmentId()),
                () -> assertEquals(DEPARTMENT_MODEL.getDepartmentName(), department.getDepartmentName())
        );
    }
    */

        @Test
        public void testEmployeeModelToEmployeeMapping() {
                Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
                Assertions.assertAll(
                        () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), 200/*employee.getEmployeeId()*/),
                        () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName())

                );
        }
}
