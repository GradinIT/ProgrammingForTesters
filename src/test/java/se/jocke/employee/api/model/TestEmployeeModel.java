package se.jocke.employee.api.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;  // Kolla att detta är rätt
import se.jocke.employee.builder.EmployeeModelTestBuilder;  //???


public class TestEmployeeModel {
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated() {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .salary(EMPLOYEE_MODEL.getSalary())
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL, employeeModel); //en assertionequal för varje. Går det att göra smidigare?
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.toString(), employeeModel.toString());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime());

    }

    @Test // ändra testnamn till onlyprovidingdepartmentid istället för whennotporvidingdep.id
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build());

    }

    @Test //ändra namn till WhenOnlyProvidingFirstName från: WhenNotProvidingDepartmentName
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());

    }
}
