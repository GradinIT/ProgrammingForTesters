package se.jensen.exercise.department;
import se.jensen.entity.DepartmentTestBuilder;

import org.junit.*;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;

import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeTestBuilder;
import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)
public class TestThatDepartmentIsCreated {

    DepartmentTestBuilder departmentTestBuilder = new DepartmentTestBuilder();

    @Test
    public void testThatDepartmentIsCreated() {
        System.out.println("Test that department is created correctly");
        Integer DEPARTMENTID = 1;
        String DEPARTMENTNAME = "newDepartment";

        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
    }

    @Test
    public void testThatToStringReturnsProper() {
        String expectedToString = departmentTestBuilder.build().toString();
        Department department = departmentTestBuilder.build();
        Assert.assertEquals(expectedToString, department.toString());
    }
}

