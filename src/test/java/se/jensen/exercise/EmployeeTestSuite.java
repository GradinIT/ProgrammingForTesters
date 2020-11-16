package se.jensen.exercise;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCreateEmployee.class,
        TestNullPointerException.class,
        TestEmployeeService.class
})
public class EmployeeTestSuite {
}
