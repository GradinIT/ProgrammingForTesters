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
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @When("^the client updates name for employee to (.+)$")
    public void updateNameOfEmployee(String name) throws Throwable {
        Optional<List<EmployeeModel>> employees = getAllEmployees();
        //Employee e = new EmployeeModelTestBuilder();
        employees.ifPresent(employeeModels -> updateEmployee(EmployeeModel.builder()
                .employeeId(employeeModels.get(0).getEmployeeId())
                .firstName(name)
                .lastName(employeeModels.get(0).getLastName())
                .salary(employeeModels.get(0).getSalary())
                .fullTime(employeeModels.get(0).getFullTime())
                .departmentId(employeeModels.get(0).getDepartmentId())
                .build()));
    }

    @Then("the employee name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String name) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(name, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the employee name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> deps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            deps.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .salary(new BigDecimal(given.get(i + 3)))
                    .fullTime(Boolean.valueOf(given.get((i + 4))))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }
        return deps;
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

    @And("the error message {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
