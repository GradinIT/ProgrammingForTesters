package se.jensen.cucumber.department;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(strict = true,features = "src/test/resources/department")
public class TestRunner {

}