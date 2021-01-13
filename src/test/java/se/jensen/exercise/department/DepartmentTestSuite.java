package se.jensen.exercise.department;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatDepartmentIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class, TestDepartmentService.class
})
public class DepartmentTestSuite {
}
