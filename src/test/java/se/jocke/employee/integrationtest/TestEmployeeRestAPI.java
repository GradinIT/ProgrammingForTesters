package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import liquibase.pro.packaged.S;
import org.checkerframework.checker.nullness.Opt;
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

    @When("^the client calls /employees$")
    public void getAllEmployees() throws Throwable {
        employees = getAllEmployees(1); // getAllEmployees don't use parameter try to remove
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates name for employee to (.+)$")
    public void updateFirstNameOfEmployee(String employeeName) throws Throwable{
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(employeeName)
                .lastName("lastName1")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .departmentId(1)
                .build());
    }

    @Then("the employees name is updated to (.+)$")
    public void firstNameOfEmployeeIsUpdated(String employeeName) throws Throwable{
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable{
        employee = getEmployeeById(employeeId);
    }

    @Then("^the employees name is$")
    public void nameOfEmployeeIs() throws Throwable{
        Assert.assertEquals("Egon", employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() -1; i += 6) {
            employees.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i +1))
                    .lastName(given.get(i + 2))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i + 3))))
                    .fullTime(Boolean.getBoolean(given.get(i + 4)))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }
        return employees;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkEmployeeErrorMessage(Integer errorCodes, Integer employeeId) {
        Assertions.assertEquals(errorCodes + " : [Entity with id " + employeeId + " not found]", exceptionThrown.getMessage());
    }
}
