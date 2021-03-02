package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployeees(1);
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates first name for employee to (.+)$")
    public void updateNameOfEmployee(String firstName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(firstName).build());
    }

    @Then("the employee first name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String firstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(firstName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the name of employee is$")
    public void firstNameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());
    }

    @Given("^the employees$")
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
    }

    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    @Then("^the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId) {
        Throwable exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
        assertEquals("404 : [Entity with id 55 not found]", exceptionThatWasThrown.getMessage());
    }
}
