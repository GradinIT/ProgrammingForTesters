package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

import static org.junit.Assert.fail;

public class TestThatDepartmentIsCreated implements UnitTest {
    @Test
    public void toTestThatDepartmentIsCreated() {
        Integer departmentId = 2;
        String departmentName = "Junit";
        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentId,department.getDepartmentId());
        Assert.assertEquals(departmentName,department.getDepartmentName());
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullValueNotAllowedForDepartmentId(){
        Department.builder()
                .departmentId(null)
                .departmentName("Junit")
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullValueNotAllowedForDepartmentName(){
        Department.builder()
                .departmentId(2)
                .departmentName(null)
                .build();
    }
    @Test
    public void testThatToStringReturnProperly(){
        String expectedToString = DepartmentTestBuilder.build().toString();
        Department department = DepartmentTestBuilder.build();
        Assert.assertEquals(expectedToString,department.toString());

    }
    @Test
        public void testThatLombokMessageIsCorrectForDepartmentName() {
            Integer DEPARTMENT_ID = 2;
            String DEPARTMENT_NAME = null;

            try {
                Department department = Department.builder()
                        .departmentId(DEPARTMENT_ID)
                        .departmentName(DEPARTMENT_NAME)
                        .build();
                fail("Expected exception not thrown");
            } catch (NullPointerException nullPointerException) {
                Assert.assertEquals("departmentName is marked non-null but is null", nullPointerException.getMessage());
            } catch (Exception e) {
                fail("Expected exception not thrown");
            }

    }
    @Test
    public void testThatLombokMessageIsCorrectForDepartmentId() {
        Integer DEPARTMENT_ID = null;
        String DEPARTMENT_NAME = "Junit";

        try {
            Department department = Department.builder()
                    .departmentId(DEPARTMENT_ID)
                    .departmentName(DEPARTMENT_NAME)
                    .build();

            fail("Expected exception not thrown");
        } catch (NullPointerException nullPointerException) {
            Assert.assertEquals("departmentId is marked non-null but is null", nullPointerException.getMessage());
        } catch (Exception e) {
            fail("Expected exception not thrown");
        }

    }
}