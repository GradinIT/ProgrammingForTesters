package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import se.jensen.exercise.department.client.TestThatNullPointerExceptionIsThrown;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatDepartmentIsStoredInDatabase.class,
        TestDepartmentService.class
})
public class DepartmentTestSuite {
}
