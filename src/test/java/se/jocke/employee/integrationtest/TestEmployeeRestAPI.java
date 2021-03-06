package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Oscar Bergström 03-03-2021
 */
public class TestEmployeeRestAPI extends TestClient {

    // Integrationtesting REST API for Employee via TestClient.java

    // Fields for storing our list of Employees and single Employee objects. Optionals since they can be null.
    Optional<List<EmployeeModel>> employees = Optional.empty();
    Optional<EmployeeModel> employee = Optional.empty();

    // Getting all Employees from REST-service as a list...
    @When("^the client calls /employee$")
    public void clientGetsAllEmployees() throws Exception {
        employees = getAllEmployees();
    }

    // .. then check that we recieved the correct amount of Employees
    @Then("the client receives {int} employees")
    public void clientReceivesAllEmployees(int numOfEmployees) {
        employees.ifPresent(employeeList -> Assert.assertEquals(numOfEmployees, employeeList.size()));
    }

    // Attempt to update a clients first and last name over REST API...
    @When("the client updates name for employee 1 to {string} {string}")
    public void theClientUpdatesFirstNameForEmployeeToSven(String firstName, String lastName) {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(firstName)
                .lastName(lastName)
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .departmentId(1)
                .build());
    }

    // .. then check that the update succeeded
    @Then("the employee name is updated to {string} {string}")
    public void theFirstNameIsUpdatedToSven(String firstName, String lastName) {
        Optional<EmployeeModel> employeeModel = getEmployeeById(1);
        employeeModel.ifPresent(model -> Assert.assertEquals(firstName, model.getFirstName()));
        employeeModel.ifPresent(model -> Assert.assertEquals(lastName, model.getLastName()));
    }

    // Attempt to retrive a specific Employee by ID
    @When("the client gets employee {int}")
    public void theClientGetsEmployeeById(Integer id) {
        employee = getEmployeeById(id);
    }

    // ... then check that we got the correct employee by name
    @Then("the name is Sven Svensson")
    public void theNameOfEmployee() {
        employee.ifPresent(model -> Assert.assertEquals("Sven", model.getFirstName()));
        employee.ifPresent(model -> Assert.assertEquals("Svensson", model.getLastName()));
    }

    // Test creating some Employees over the REST API with a given table of data from the Cucumber test scenario
    @Given("the employees")
    public void givenEmployees(DataTable employeesFromTable) {
        List<EmployeeModel> listOfEmployee = makeEmployeeList(employeesFromTable.asList());
        listOfEmployee.forEach(TestClient::createEmployee);
    }

    // Method for parsing the raw table from Cucumber into a list of EmployeeModels
    private List<EmployeeModel> makeEmployeeList(List<String> dataTableList) {
        List<EmployeeModel> resultingList = new ArrayList<>();
        for (int i = 0; i < dataTableList.size() -1; i += 6) {
            resultingList.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(dataTableList.get(i)))
                    .firstName(dataTableList.get(i+1))
                    .lastName(dataTableList.get(i+2))
                    .fullTime(Boolean.parseBoolean(dataTableList.get(i+3)))
                    .salary(BigDecimal.valueOf(Integer.parseInt(dataTableList.get(i+4))))
                    .departmentId(Integer.parseInt(dataTableList.get(i+5)))
                    .build());
        }
        return resultingList;
    }

    // Delete the Employees we added via the API from the service
    @When("the client deletes employee {int}")
    public void deleteEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    // Check that the Employees have been deleted by attempting to access them, and catching the Exception
    Throwable thrownNotFoundException;
    @Then("the employee {int} is deleted")
    public void theEmployeeIsDeleted(int employeeId) {
        thrownNotFoundException = Assert.assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    // Verify that the Exception was from the Employee entity missing
    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", thrownNotFoundException.getMessage());
    }

}
