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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEmployeeRestAPI extends EmployeeTestClient {
    private Optional<List<EmployeeModel>> employees = null;
    private Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("the client updates name for employee to (.+) and (.+)$")
    public void updateNameOfEmployee(String firstName, String lastName) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(firstName)
                .lastName(lastName)
                .salary(new BigDecimal(25000))
                .fullTime(true)
                .departmentId(1)
                .build());
    }

    @Then("the name is updated to (.+) and (.+)$")
    public void nameOfEmployeeIsUpdated(String firstname, String lastname) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(firstname, employee.get().getFirstName());
        Assert.assertEquals(lastname, employee.get().getLastName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());
        Assert.assertEquals("lastName1", employee.get().getLastName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) throws Throwable {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emp = new ArrayList<>();
        for (int i = 0; i < given.size(); i += 3) {
            emp.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .fullTime(true)
                    .salary(new BigDecimal(25000))
                    .departmentId(i + 1)
                    .build());
        }
        return emp;
    }


    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    private Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error message is {int} : [\"Employee with id {int} not found\"]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}