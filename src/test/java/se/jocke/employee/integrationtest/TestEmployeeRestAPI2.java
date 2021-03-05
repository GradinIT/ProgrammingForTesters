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

public class TestEmployeeRestAPI2 extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employees$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    // Söker alla employees!
    @Then("^the client receives all (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
    // Hämtar alla employees!

    @When("^the client updates first name of employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
    }

    // Söker på employees först namn för att uppdatera den!
    //
    @Then("the first name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }

    // Uppdaterar employees förnamn!
    @When("^the client updates last name of employee to (.+)$")
    public void updateLastNameOfEmployee(String employeeLastName) throws Throwable {
        updateEmployee(EmployeeModel.builder().departmentId(1).lastName(employeeLastName).build());
    }
    // Söker på efternamn för att uppdatera det!
    // Borde jag ändra rad 49 departmentId(1) till något annat, kanske employee id?

    @Then("the last name is updated to (.+)$")
    public void lastNameOfEmployeeIsUpdated(String employeeLastName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeLastName, employee.get().getLastName());
    }
    // Uppdaterar efternamn!

    @When("^the client updates salary of employee to (.+)$")
    public void updateSalaryOfEmployee(BigDecimal employeeSalary) throws Throwable {
        updateEmployee(EmployeeModel.builder().departmentId(1).salary(employeeSalary).build());
    }

    @Then("the salary is updated to (.+)$")
    public void salaryOfEmployeeIsUpdated(BigDecimal employeeSalary) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeSalary, employee.get().getSalary());
    }

    @When("^the client updates contract of employee to full time (.+)$")
    public void updateContractOfEmployee(boolean employment) throws Throwable {
        updateEmployee(EmployeeModel.builder().departmentId(1).fullTime(employment).build());
    }

    @Then("the contract is updated to (.+)$")
    public void contractOfEmployeeIsUpdated(boolean employment) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employment, employee.get().getFullTime());
    }

    @When("^the client gets employee by id (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    //
    @Then("^the name of employee is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Nico", employee.get().getFirstName());
    }

    @Given("^the employees are")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
            emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).firstName(given.get(i + 1)).build());
        }
        return emps;
    }// måste jag lägga till alla 6 variabler tex: efternamn,lön, anställning osv?
     // Lägger till employees i en lista!
     // I rad 97 så borde man kanske ändra till employeeId() istället för firstName() så att de blir unikt!!! men då måste man göra om!

    @When("the client deletes this employee {int}")
    public void deleteDepartment(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is now deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}



