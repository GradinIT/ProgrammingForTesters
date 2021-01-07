package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DepartmentRestGetAll.class,
        DepartmentRestApiTest.class,
        DepartmentRestGetByIDIT.class,
        TestDepartmentModelMapper.class,
        TestDepartmentService.class,
        TestThatDepartmentIsCreated.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestThatNullPointerExceptionIsThrown.class,
        DepartmentDaoTest.class
})
public class DepartmentTestSuite {
}
