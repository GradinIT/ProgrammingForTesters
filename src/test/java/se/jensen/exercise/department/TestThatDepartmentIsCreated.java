package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.entity.DepartmentTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestThatDepartmentIsCreated {
        Department department = Department.builder()
                .departmentName("Finance")
                .departmentId(2)
                .build();

        @Test
        public void testDepartmentIsCreated (){
            System.out.println("testing whether department finance is created");
            Assert.assertNotNull(department);
            assertEquals(2, department.getDepartmentId());
            assertEquals("Finance", department.getDepartmentName());
        }
}
