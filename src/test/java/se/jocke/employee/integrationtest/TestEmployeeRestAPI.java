package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestEmployeeRestAPI extends EmployeeTestClient {

    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
     }
   @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer int1) {
    }

}
