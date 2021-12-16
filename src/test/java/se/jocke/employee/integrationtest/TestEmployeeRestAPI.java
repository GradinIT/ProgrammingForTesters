package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEmployeeRestAPI extends EmployeeTestClient {
    private List<EmployeeModel> employees;
    private EmployeeModel employee;


    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
        employees = getAllEmployees().get();
    }

    @Then("the client receives {int} employees")
    public void the_client_receives_employees(Integer numberOfEmployees) {
        Assertions.assertEquals(numberOfEmployees, employees.size());
    }


    @When("the client gets employee {int}")
    public void the_client_gets_employee(Integer getEmployee) {
        employee = getEmployeeById(getEmployee).get();
    }

    @Then("^employeename is (.+)$")
    public void employeename_is_runar(String employeeFirstName) {

        Assert.assertEquals(employeeFirstName, employee.getFirstName());
    }


    @When("^the client updates firstname for employee to (.+)$")
    public void the_client_updates_firstname_for_employee_to_runar(String employeeFirstName) {
        employee = getEmployeeById(1).get();
        EmployeeModel update = EmployeeModel.builder()
                .departmentId(employee.getDepartmentId())
                .employeeId(employee.getEmployeeId())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .firstName(employeeFirstName)
                .build();
        employee = updateEmployee(update).get();
    }

    @Then("^the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_runar(String firstName) {

        Assertions.assertEquals(firstName, employee.getFirstName());
    }

    @Given("^the employees$")
    public void the_employees(DataTable dataTable) {
        makeEmployeeList(dataTable.asList())
                .stream()
                .forEach(employeeModel -> createEmployee(employeeModel));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
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
    public void theClientDeletesEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    private Throwable exceptionThatWasThrown;


    @Then("Employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error message is {int} : [\"Entity with id {int} not found\"]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}
