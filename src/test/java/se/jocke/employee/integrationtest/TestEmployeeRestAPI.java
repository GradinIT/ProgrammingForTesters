package se.jocke.employee.integrationtest;

import se.jocke.employee.api.EmployeeModel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;



    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



    @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
