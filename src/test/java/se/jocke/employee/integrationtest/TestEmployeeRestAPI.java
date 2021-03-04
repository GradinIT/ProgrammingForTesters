package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

   // när vi har anropat alla employees, then size is.

    @When("the client calls /employee")
    public void getAll() {
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void allEmployeesSize(int numberOfEmployees)
    {
        Assertions.assertEquals(numberOfEmployees , employees.get().size());

    }




}
