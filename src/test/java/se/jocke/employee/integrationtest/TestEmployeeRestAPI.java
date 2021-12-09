package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestEmployeeRestAPI extends EmployeeTestClient {

    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
    }

    @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer int1) {
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

    @Given("the employees")
    public void the_employees(DataTable dataTable) {
        makeDepartmentList(dataTable.asList())
                .stream()
                .forEach( employeeModel -> createEmployee(employeeModel));
    }

    private List<EmployeeModel> makeDepartmentList(List<String> given) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for(int i = 0 ; i < given.size();) {
            employeeModels.add( EmployeeModel.builder()
                    .employeeId(Integer.valueOf(given.get(i++)))
                    .firstName(given.get(i++))
                    .lastName(given.get(i++))
                    .salary(new BigDecimal(given.get(i++)))
                    .fullTime(Boolean.valueOf(given.get(i++)))
                    .departmentId(Integer.parseInt(given.get(i++)))
                    .build());
        }
        return employeeModels;
    }
}
