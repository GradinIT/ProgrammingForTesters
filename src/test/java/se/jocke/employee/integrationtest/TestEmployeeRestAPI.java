package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employees$")
    public void getAll() throws Throwable {
        employees = getAllEmployeees(1);
    }
    @Then("the client receives {int} employees")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
}
