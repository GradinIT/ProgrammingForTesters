package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.internal.matchers.Null;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAll() {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void the_client_receives_employees (int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, getAllEmployees().get().size());
    }

    @When("^the client gets (\\d+) employee $")
    public void the_client_gets_employee(Integer int1) {
    }

    @Then("^firstname is (.+)$")
    public void firstname_is_runar() {
    }

    @When("^the client updates firstname for employee to (.+)$")
    public void the_client_updates_firstname_for_employee_to_runar() {
    }

    @Then("^the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_runar() {
    }

    @Given("^the employees$")
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
