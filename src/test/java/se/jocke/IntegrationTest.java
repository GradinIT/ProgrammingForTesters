package se.jocke;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/department/departmentTest.feature", "src/test/resources/employee/employeeTest.feature"})
public class IntegrationTest {
}