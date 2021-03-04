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

    Optional<List<EmployeeModel>> employees = Optional.empty();
    Optional<EmployeeModel> employee = Optional.empty();

    @When("^the client calls /employee$")
    public void clientGetsAllEmployees() throws Exception {
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void clientReceivesAllEmployees(int numOfEmployees) {
        employees.ifPresent(employeeList -> Assert.assertEquals(numOfEmployees, employeeList.size()));
    }

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

    @Then("the employee name is updated to {string} {string}")
    public void theFirstNameIsUpdatedToSven(String firstName, String lastName) {
        Optional<EmployeeModel> employeeModel = getEmployeeById(1);
        employeeModel.ifPresent(model -> Assert.assertEquals(firstName, model.getFirstName()));
        employeeModel.ifPresent(model -> Assert.assertEquals(lastName, model.getLastName()));
    }

    @When("the client gets employee {int}")
    public void theClientGetsEmployeeById(Integer id) {
        employee = getEmployeeById(id);
    }

    @Then("the name is Sven Svensson")
    public void theNameOfEmployee() {
        employee.ifPresent(model -> Assert.assertEquals("Sven", model.getFirstName()));
        employee.ifPresent(model -> Assert.assertEquals("Svensson", model.getLastName()));
    }

    @Given("the employees")
    public void givenEmployees(DataTable employeesFromTable) {
        List<EmployeeModel> listOfEmployee = makeEmployeeList(employeesFromTable.asList());
        listOfEmployee.forEach(TestClient::createEmployee);
    }

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

    @When("the client deletes employee {int}")
    public void deleteEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable thrownNotFoundException;

    @Then("the employee {int} is deleted")
    public void theEmployeeIsDeleted(int employeeId) {
        thrownNotFoundException = Assert.assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", thrownNotFoundException.getMessage());
    }

}
