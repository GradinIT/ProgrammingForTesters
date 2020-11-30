package se.jensen.exercise.department;
import org.junit.*;
import org.junit.Test;
import se.jensen.entity.Department;

public class TestThatDepartmentIsCreated {

    @Test
    public void testThatDepartmentIsCreated() {
        Integer DEPARTMENTID = 1;
        String DEPARTMENTNAME = "Department1";

        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
    }

}

