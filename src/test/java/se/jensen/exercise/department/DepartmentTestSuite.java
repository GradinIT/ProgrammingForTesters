package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DepartmentDaoTest.class,
        DepartmentRestApiTest.class,
        TestDepartmentModelMapper.class,
        TestDepartmentService.class,
        TestThatDepartmentIsCreated.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestThatNullPointerExceptionIsThrown.class,
})
public class DepartmentTestSuite {
}
