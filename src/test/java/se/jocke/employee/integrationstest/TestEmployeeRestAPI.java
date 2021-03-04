package se.jocke.employee.integrationstest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.api.DepartmentModel;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployeees();
    }
    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
    @When("^the client updates name for employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
    }
    @Then("the name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName,employee.get().getFirstName());
    }
    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }
    @Then("^the name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Robert",employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }
    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> deps = new ArrayList<>();
        for(int i = 0 ; i < given.size() - 1 ; i +=2) {
            deps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).firstName(given.get(i+1)).build());
        }
        return deps;
    }
    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId){
        deleteEmployee(EmployeeModel.builder().employeeId(employeeId).firstName("").build());
    }
    @Then("^the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId){
        Throwable exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getTheEmployeeById(employeeId);;
        });

        assertEquals("404 : [Entity with id 55 not found]",exceptionThatWasThrown.getMessage());
    }

}

