package se.jocke.department.integrationtest;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/department/departmentTest.feature")
public class DepartmentIntegrationTest {
}