package se.jocke.employee.integrationtest;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestEmployeeRestAPI extends EmployeeTestClient {

    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
    }
    @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer int1) {
    }

    @Given("the employees")
    public void the_employees(io.cucumber.datatable.DataTable dataTable) {
    }
    @When("the client gets employee {int}")
    public void the_client_gets_employee(Integer int1) {
    }
    @Then("firstname is (.+)")
    public void firstname_is_runar() {
    }
    @When("the client updates firstname for employee to (.+)")
    public void the_client_updates_firstname_for_employee_to_runar() {
    }
    @Then("the firstname is updated to (.+)")
    public void the_firstname_is_updated_to_runar() {
    }

}
