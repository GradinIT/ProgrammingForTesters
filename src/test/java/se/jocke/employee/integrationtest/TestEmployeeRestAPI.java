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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getALL() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates everything for employee to (.+)$")
    public void updateNameOfEmployee(String employeeFirstName) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(employeeFirstName)
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(2500000, 2))
                .departmentId(1)
                .build());
    }

    @Then("^the employee is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeFirstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^employees first name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Coding", employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            emps.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .fullTime(Boolean.valueOf(given.get(i +3)))
                    .salary(BigDecimal.valueOf(Double.parseDouble(given.get(i + 4))))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }
        return emps;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error is {int} : [Entity with id {int} not found]")
    public void checkTheErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}
