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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employees$")
    public void getAll() {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfDepartments) {
        employees.ifPresent(employeeModels -> Assert.assertEquals(numberOfDepartments, employeeModels.size()));
    }

    @When("^the client updates name for employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) {
        Optional <List<EmployeeModel>> employees = getAllEmployees();
        updateEmployee(EmployeeModel.builder()
                .employeeId(employees.get().get(0).getEmployeeId())
                .firstName(employeeName)
                .lastName(employees.get().get(0).getLastName())
                .salary(employees.get().get(0).getSalary())
                .fullTime(employees.get().get(0).getFullTime())
                .departmentId(employees.get().get(0).getDepartmentId())
                .build());
    }

    @Then("the first name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the first name is$")
    public void nameOfEmployeeIs() {
        employee.ifPresent(employeeModel -> Assert.assertEquals("Kalle", employeeModel.getFirstName()));
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfDepartments = makeEmployeeList(employees.asList());
        listOfDepartments.forEach(TestClient::createEmployee);
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            emps.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .salary(new BigDecimal(given.get(i+3)).setScale(2))
                    .fullTime(Boolean.valueOf(given.get(i+4)))
                    .departmentId(Integer.parseInt(given.get(i+5))).build());
        }
        return emps;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        if (getEmployeeById(employeeId).isPresent())
            TestEmployeeRestAPI.deleteEmployee(getEmployeeById(employeeId).get());
    }
    Throwable exceptionThatWasThrown;
    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> getEmployeeById(departmentId));
    }
    @And("the error message for employee is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer departmentId) {
        Assertions.assertEquals(errorCode+" : [Entity with id " + departmentId +" not found]",exceptionThatWasThrown.getMessage());
    }

}
