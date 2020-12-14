package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class,
        TestDepartmentServiceExceptions.class,
        TestDepartmentModelMapper.class,
        TestDepartmentDatabaseEntryMapper.class,
        DepartmentRestApiTest.class,
        TestDepartmentDatabaseEntry.class,
        TestDepartmentModelCreated.class,
        TestDepartment.class,
        DepartmentDaoTest.class
})
public class DepartmentTestSuite {
}

