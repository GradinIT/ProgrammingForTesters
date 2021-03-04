package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.jocke.TestClient.*;

public class TestEmployeeRestAPI {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employees$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();

    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^now the client updates the employee to (.+) and (.+) and (.+) and (.+) and (.+)$")
    public void updateOfEmployee(String firstName, String lastName, Boolean fullTime, BigDecimal bigDecimal, Integer departmentId) throws Throwable {
      EmployeeModel employeeModel = getEmployeeById(1).get();
      updateEmployee(EmployeeModel.builder()
              .employeeId(employeeModel.getEmployeeId())
              .firstName(firstName)
              .lastName(lastName)
              .fullTime(fullTime)
              .salary(bigDecimal)
              .departmentId(departmentId)
              .build());
    }

    @Then("^now the the employee is updated to (.+) and (.+) and (.+) and (.+) and (.+)$")
    public void firstNameOfEmployeeIsUpdated(String firstName, String lastName, Boolean fullTime, BigDecimal bigDecimal, Integer departmentId) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(firstName, employee.get().getFirstName());
        Assert.assertEquals(lastName, employee.get().getLastName());
        Assert.assertEquals(fullTime, employee.get().getFullTime());
        Assert.assertEquals(bigDecimal, employee.get().getSalary());
        Assert.assertEquals(departmentId, employee.get().getDepartmentId());
    }

    @When("^now the client gets employees (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^now the name of employee is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Kalle", employee.get().getFirstName());

    }

    @And("^the size of employee list is checked$")
    public void checkSizeOfEmployeeList() {
        Assert.assertEquals(3, getAllEmployees().get().size());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employee = new ArrayList<>();
        for(int i = 0 ; i < given.size() - 1 ; i +=6) {
            employee.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i+1))
                    .lastName(given.get(i+2))
                    .departmentId(Integer.parseInt(given.get(i +3)))
                    .salary(new BigDecimal(((given.get(i + 4)))))
                    .fullTime(Boolean.valueOf(given.get(i +5)))
                    .build());
        }
        return employee;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployeeFromDB(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId){
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("it has been an error message {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId){
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
}
