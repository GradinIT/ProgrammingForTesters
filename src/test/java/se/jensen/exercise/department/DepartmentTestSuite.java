package se.jensen.exercise.department;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class,
        TestDepartmentModelCreated.class,
        TestDepartmentModelMapper.class,
        TestDepartmentModelsMapper.class,
        TestDepartmentDatabaseEntryCreated.class,
        TestDepartmentDatabaseEntryMapper.class,
        TestDepartmentTestBuilder.class,
        DepartmentDaoTest.class,
        DepartmentRestApiTest.class
})
public class  DepartmentTestSuite {


}
