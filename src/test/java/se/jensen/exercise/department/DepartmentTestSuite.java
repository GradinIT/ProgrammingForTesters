package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestDepartmentDatabaseEntryCreated.class,
        TestDepartmentDatabaseEntryMapperCreated.class,
        TestDepartmentModelCreated.class,
        TestDepartmentModelMapper.class,
        TestDepartmentModelsMapper.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class
})
public class DepartmentTestSuite {
}
