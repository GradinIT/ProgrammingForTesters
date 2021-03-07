package se.jocke.employee.integrationtest;



import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
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
import static org.mockito.ArgumentMatchers.*;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable{
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates firstname for employee to (.+)$")
    public void updateFirstNameOfEmployee(String employeeFirstName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1)
                .firstName(employeeFirstName)
                .lastName(anyString())
                .fullTime(anyBoolean())
                .salary(BigDecimal.valueOf(anyDouble()))
                .departmentId(anyInt())
                .build());
    }

    @Then("the firstname is updated to (.+)$")
    public void firstNameOfEmployeeIsUpdated(String employeeFirstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());
    }

    @When("the client gets employee {int}")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the firstname is$")
    public void firstNameOfEmployeeIs() {
        Assert.assertEquals("Adam", employee.get().getFirstName());
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
                    .fullTime(Boolean.parseBoolean(given.get(i + 3)))
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

    @And("the error message is {int} : [Employee with id {int} not found]")
    public void checkErrorMessages(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]",exceptionThatWasThrown.getMessage());
    }
}
