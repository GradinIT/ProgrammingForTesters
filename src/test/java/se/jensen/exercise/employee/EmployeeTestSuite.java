package se.jensen.exercise.employee;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestThatEmployeeIsCreated.class,
        TestThatNullPointerExceptionIsThrown.class,
        TestThatEmployeeIsStoredInDatabase.class,
        TestEmployeeService.class,
        TestEmployeeModelMapper.class
})
public class EmployeeTestSuite {

}
