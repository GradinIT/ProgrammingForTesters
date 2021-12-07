package se.jocke.employee.unittest.api.mapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployeeModelMapper {

    // here we create a new object with a name of EMPLOYEE and use it with Builder.build().
    private static final Employee EMPLOYEE= EmployeeTestBuilder.build();

    //here we create a new object with a name of EMPLOYEE_MODEL and use it with Builder.build() in order to get value from class Employee
    // which is in src/main/java/se/jocke/employee/entity/Employee.java.


    // here we collect all data.
    //Employee(employeeId=EntityID(id=2), firstName=Fagel, lastName=Holk, salary=222222, fullTime=true, departmentId=55)
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .departmentId(EMPLOYEE.getDepartmentId())
            .employeeId(EMPLOYEE.getEmployeeId().getId())
            .fullTime(EMPLOYEE.getFullTime())
            .salary(EMPLOYEE.getSalary())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .build();


    // here we will test that the values Employee Model Is Equal To Employee
    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {


        //EmployeeModelMapper have the values which are in main-java which we will test
        // employeeModel are the values which we get from EmployeeModelMapper (se/jocke/employee/api/mapper/EmployeeModelMapper.java)

        //it maps the Employee class to the EmployeeModel class ... basically type conversion(when you assign one data type to another one) between the two types
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);

        //test that values of EMPLOYEE_MODEL and employeeModel are same or not.
        // EMPLOYEE_MODEL are the values which we get at line 22.
        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);
        // test that DepartmentId in EMPLOYEE is equal to DepartmentId in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(),employeeModel.getDepartmentId());
        // test that EmployeeId in EMPLOYEE is equal to EmployeeId in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(),employeeModel.getEmployeeId()); // getId is class created(src/main/java/se/jocke/common/entity/EntityID.java)

        // test that FullTime in EMPLOYEE is equal to FullTime in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employeeModel.getFullTime());
        // test that Salary in EMPLOYEE is equal to Salary in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getSalary(),employeeModel.getSalary());
        // test that FirstName in EMPLOYEE is equal to FirstName in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getFirstName(),employeeModel.getFirstName());
        // test that LastName in EMPLOYEE is equal to LastName in employeeModel or not.
        Assertions.assertEquals(EMPLOYEE.getLastName(), employeeModel.getLastName());

    }


    // here we will test that the values Employee Is Equal To Employee Model

    @Test
    public void testThatEmployeeIsEqualToEmployeeModel() {

        //it maps the EMPLOYEE_MODEL class to the EmployeeModel class ... basically type conversion(when you assign one data type to another one) between the two types
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);

        //test that values of EMPLOYEE and employee are same or not.
        // EMPLOYEE are the values which we get at line 14.
        Assertions.assertEquals(EMPLOYEE,employee);

        // test that DepartmentId in EMPLOYEE_MODEL is equal to DepartmentId in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId());

        // test that EmployeeId in EMPLOYEE_MODEL is equal to EmployeeId in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId()); // getId is class created(src/main/java/se/jocke/common/entity/EntityID.java)

        // test that FullTime in EMPLOYEE_MODEL is equal to FullTime in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime());

        // test that Salary in EMPLOYEE_MODEL is equal to Salary in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employee.getSalary());

        // test that FirstName in EMPLOYEE_MODEL is equal to FirstName in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName());

        // test that LastName in EMPLOYEE_MODEL is equal to LastName in employee or not.
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName());

    }

}
