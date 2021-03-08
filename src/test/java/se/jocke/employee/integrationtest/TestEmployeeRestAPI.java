package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.api.EmployeeModel;
import se.jocke.entity.Employee;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {
    Optional<List<EmployeeModel>> employeeModels = null;
    Optional<Employee> employee = null;


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employeeModels = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllDepartments(int numberOfDepartments) throws Throwable {
        Assert.assertEquals(numberOfDepartments, employeeModels.get().size());
    }

}
