package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

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

}
