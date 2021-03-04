package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import liquibase.dbdoc.AuthorWriter;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.DepartmentModel;
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
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }
    // Söker alla employees!

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
    // Hämtar alla employees!

    @When("^the client updates employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(employees.get().get(0).getEmployeeId())
                .firstName(employeeName)
                .lastName(employees.get().get(0).getLastName())
                .salary(employees.get().get(0).getSalary())
                .fullTime(employees.get().get(0).getFullTime())
                .departmentId(employees.get().get(0).getDepartmentId())
                .build());
    }
    // Söker på employees först namn för att uppdatera den!

    @Then("the employee is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }
    // Uppdaterar employees first name!

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the name of employee is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Nico", employee.get().getFirstName());
    }
    // Hämtar employee med namn:....... genom id!

    @Given("^the employees")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }
    // Skapar Employees!

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).firstName(given.get(i + 1)).build());
        }
        return emps;
    }// Lägger till employees i en lista!
    // I rad 97 så borde man kanske ändra till employeeId() istället för firstName() så att de blir unikt!!! men då måste man göra om!

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }// Söker på employee genom id som ska tas bort!

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }// Tar bort employee med id = ...!

    @And("this error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}
