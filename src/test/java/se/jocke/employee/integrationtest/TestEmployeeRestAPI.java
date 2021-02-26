package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.math.BigDecimal;
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
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates name for employee to (.+) (.+)$")
    public void updateNameOfEmployee(String firstName, String lastName) throws Throwable {
        employee = getEmployeeById(1);
        updateEmployee(EmployeeModel.builder().employeeId(1)
                .firstName(firstName)
                .lastName(lastName)
                .salary(employee.get().getSalary())
                .fullTime(employee.get().getFullTime())
                .departmentId(employee.get().getDepartmentId())
                .build());
    }

    @Then("the employee name is updated to (.+) (.+)$")
    public void nameOfEmployeeIsUpdated(String firstName, String lastName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(firstName, employee.get().getFirstName()),
                () -> assertEquals(lastName, employee.get().getLastName())
        );
    }

    @When("the client gets employee {int}")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the info is$")
    public void infoOfEmployeeIs() {
        Assertions.assertAll(
                () -> assertEquals("Ludde", employee.get().getFirstName()),
                () -> assertEquals("Ljungman", employee.get().getLastName()),
                () -> assertEquals(employee.get().getSalary(), employee.get().getSalary()),
                () -> assertEquals(employee.get().getFullTime(), employee.get().getFullTime()),
                () -> assertEquals(employee.get().getDepartmentId(), employee.get().getDepartmentId())
        );
    }

    @Given("the employees")
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
                    .salary(new BigDecimal(given.get(i + 3)))
                    .fullTime(Boolean.valueOf(given.get(i + 4)))
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
            getTheEmployeeById(employeeId);
        });
    }
    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkEmployeeErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}
