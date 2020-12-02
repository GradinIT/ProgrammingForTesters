package se.jensen.exercise.department;

import org.junit.Assert;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import se.jensen.entity.Department;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

public class TestThatDepartmentIsCreated {
    @Test
    public void test() {
        Integer departmentId = 1;
        String departmentName = "Accounts";


        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertEquals(departmentId, department.getDepartmentId());
        Assert.assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    @DisplayName("Checks if the department is created")
    public void CheckIfTheDepartmentIsCreated() {
        String departmentName = "Intellij";
        Integer departmentId = 2020;

        Department department = Department.builder()
                .departmentName(departmentName)
                .departmentId(departmentId)
                .build();

        Assert.assertEquals(departmentName, department.getDepartmentName());
        Assert.assertEquals(departmentId, department.getDepartmentId());
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Checks if the departments name is Null")
    public void CheckIfTheDepartmentNameIsNull(){
        Department.builder()
                .departmentName(null)
                .departmentId(1)
                .build();
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Checks if the departments Id is Null")
    public void CheckIfTheDepartmentIdIsNull(){
        Department.builder()
                .departmentName("Intellij")
                .departmentId(null)
                .build();
    }
}