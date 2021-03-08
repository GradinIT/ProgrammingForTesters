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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        employees = getAllEmployees();
        Assert.assertEquals(numberOfEmployees, employees.get().size());
        System.out.println("-------------------" + numberOfEmployees);

    }


    @When("^the client updates name for employee to (.+)$")
    public void updateNameOfEmployee(String firstName) throws Throwable {
        employees = getAllEmployees();
        updateEmployee(EmployeeModel.builder()
                .employeeId(employees.get().get(0).getEmployeeId())
                .firstName(firstName)
                .lastName(employees.get().get(0).getLastName())
                .salary(employees.get().get(0).getSalary())
                .fullTime(employees.get().get(0).getFullTime())
                .departmentId(employees.get().get(0).getDepartmentId())
                .build());

    }

    @Then("this name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String firstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(firstName, employee.get().getFirstName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^this name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());
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
                    .salary(BigDecimal.valueOf(Double.parseDouble(given.get(i + 3))))
                    .fullTime(Boolean.parseBoolean(given.get(i + 4)))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());

        }
        System.out.println("-------------------" + emps);
        return emps;
    }

    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("^the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getTheEmployeeById(employeeId);

        });
    }

    @And("Error {int}, the employee {int} not found")
    public void checkErrorMessageEmployee(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());

    }


}