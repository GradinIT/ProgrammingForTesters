package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates first name for employee to (.+)$")
    public void updateFirstNameOfEmployee(String employeeName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
    }

    @Then("^the name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }
    @When("^the client gets department (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the name is$")
    public void nameOfEmployeesFirstNameIs() throws Throwable {
        Assert.assertEquals("Coding", employee.get().getFirstName());
    }

    @When("^the client updates name for employee to (.+)$")
    public void updateNameOfEmployeeTo(String employeeName) throws Throwable{
        updateEmployee(EmployeeModel.builder().employeeId(1).lastName(employeeName).build());
    }

    @Then("^the name is updated to (.+)$")
    public void lastNameOfEmployeeIsUpdated(String employeeName) throws Throwable{
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertEquals(employeeName, employee.get().getFirstName());
    }

}
