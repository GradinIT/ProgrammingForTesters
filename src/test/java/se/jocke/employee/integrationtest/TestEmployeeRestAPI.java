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

    @When("^the client calls all employees$")
            public void getAll() throws Throwable {
                employees = getAllEmployees();
            };

    @Then("^the client receives (\\d+) employee$")
            public void receviedAllEmployess(int numberOfEmployees) throws Throwable {
                Assert.assertEquals(numberOfEmployees,employees.get().size());
            }

    @When("^the client calls employee by id (\\d+)$")
            public void receiveEmployee(int employeeId) throws Throwable {
                employee = getEmployeeById(employeeId);
    };

    @Then("^the client receives name (.+)$")
            public void receviedEmployee(String name) throws Throwable {
                Assert.assertEquals(name,employee.get().getFirstName());
    }
}
