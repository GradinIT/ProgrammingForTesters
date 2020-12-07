package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.exercise.DepartmentTestBuilder;

import static org.junit.Assert.fail;

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
    @Test(expected = NullPointerException.class)
    public void testThatNullValueNotAllowedForDepartmentID() {
        Department.builder()
                .departmentId(null)
                .departmentName(null)
                .build();

    }
    @Test
    public void testThatToStringReturnsProperly(){
        String expectedToString = DepartmentTestBuilder.build().toString();
        Department department = DepartmentTestBuilder.build();
        Assert.assertEquals(expectedToString,department.toString());
    }

}
