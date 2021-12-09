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

    @When("the client updates firstName for employee to Bert")
    public void the_client_updates_first_name_for_employee_to_bert() {
    }


    @Then("the firstName is updated to Bert")
    public void the_first_name_is_updated_to_bert() {
    }



}
