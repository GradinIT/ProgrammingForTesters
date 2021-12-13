package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {
    private List<EmployeeModel> employees;
    private EmployeeModel employeeModel;

    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
    }

    @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer numberOfEmployees) {
    }

    @When("the client gets employee {int}")
    public void the_client_gets_employee(Integer employeeId) {
    }

    @Then("^firstname is (.+)$")
    public void firstname_is(String firstname) {
    }

    @When("^the client updates firstname for employee whit id (\\d+) to (.+)$")
    public void the_client_updates_firstname_for_employee_to_runar(Integer emplyeeId, String firstName) {
    }

    @Then("^the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_runar(String firstName) {
    }

    @Given("test data")
    public void the_employees(DataTable dataTable) {
        makeDepartmentList(dataTable.asList())
                .stream()
                .forEach(employeeModel -> createEmployee(employeeModel));
    }

    private List<EmployeeModel> makeDepartmentList(List<String> given) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (int i = 0; i < given.size(); ) {
            employeeModels.add(EmployeeModel.builder()
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

    @When("the client deletes employee {int}")
    public void the_client_deletes_employee(Integer employeeId) {
    }

    @Then("the employee {int} is deleted")
    public void the_employee_is_deleted(Integer employeeId) {
    }

    @Then("the error message is {int} : [{string}]")
    public void the_error_message_is(Integer errorCode, String errorMessage) {
    }

    @Given("employee")
    public void employee(DataTable dataTable) {
        makeDepartmentList(dataTable.asList())
                .stream()
                .forEach(employeeModel -> createEmployee(employeeModel));
    }

    @Then("the employee {int} exsists")
    public void the_employee_exsists(Integer employeeId) {
        Optional<EmployeeModel> inDatabase = getEmployeeById(employeeId);
        Assertions.assertEquals(Boolean.TRUE,inDatabase.isPresent());
        deleteEmployee(inDatabase.get());
    }

}
