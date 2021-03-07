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
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^updates firstname for employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) throws Throwable {
        EmployeeModel employeeModel = getEmployeeById(1).get();

        updateEmployee(EmployeeModel.builder()
                .employeeId(employeeModel.getEmployeeId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .fullTime(employeeModel.getFullTime())
                .salary(employeeModel.getSalary())
                .departmentId(employeeModel.getDepartmentId())
                .build());
    }

    @Then("the firstname is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String empFirstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(empFirstName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the firstname is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employeeModelList = new ArrayList<>();

        for (int i = 0; i < given.size() - 1; i += 6) {
            employeeModelList.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i+1))
                    .lastName(given.get(i+2))
                    .salary(BigDecimal.valueOf(i+3))
                    .fullTime(Boolean.valueOf(given.get(i+4)))
                    .departmentId(i+5)
                    .build());
        }
        return employeeModelList;
    }

    @When("the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(EmployeeModel.builder().employeeId(employeeId)
                .firstName("")
                .lastName("")
                .salary(BigDecimal.ONE)
                .fullTime(false)
                .departmentId(1234)
                .build());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}