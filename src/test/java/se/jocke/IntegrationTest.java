package se.jocke;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/employee/employeeTest.feature")
//Lägg till employee-feature med ett , tecken
public class IntegrationTest {
}