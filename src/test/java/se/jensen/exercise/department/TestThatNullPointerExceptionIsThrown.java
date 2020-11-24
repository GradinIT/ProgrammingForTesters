package se.jensen.exercise.department;

import org.junit.*;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;

import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatNullPointerExceptionIsThrown {

    @BeforeClass
    public static void beforeAllTestMessage()
    {
        System.out.println("----- Test of Department properties can be null -----");
    }

    @Before
    public void beforeEveryTestMessage ()
    {
        System.out.println("Test started");
    }

    @Test
    public void testThatDepartmentIdCanBeNull ()
    {
        System.out.println("Test that department id can be null");

        Department department = Department.builder()
                .departmentId(null)
                .departmentName("Testers")
                .build();
        Assert.assertEquals(null, department.getDepartmentId());
    }

    @Test
    public void testThatDepartmentNameCanBeNull ()
    {
        System.out.println("Test that department name can be null");

        Department department = Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
        Assert.assertEquals(null, department.getDepartmentName());
    }
    @After
    public void afterEveryTestMessage ()
    {
        System.out.println("Test finished\n");
    }

    @AfterClass
    public static void afterAllTestsMessage ()
    {
        System.out.println("----- All tests finished -----");
    }
}

