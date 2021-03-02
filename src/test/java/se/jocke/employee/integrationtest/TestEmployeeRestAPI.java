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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestEmployeeRestAPI extends TestClient{
        Optional<List<EmployeeModel>> emloyees = null;
        Optional<EmployeeModel> employeee = null;


        @When("^the client calls /employee$")
        public void getAll() throws Throwable {
            emloyees = getAllEmployees();
        }

        @Then("^the client receives (\\d+) employees$")
        public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
            Assert.assertEquals(numberOfEmployees, emloyees.get().size());
        }

        @When("the client updates information for employee to {word}, {word}, {double}, {} and {int}")
        public void updateNameOfEmployee(String employeeFirstName, String employeeLastName, double employeeSalary, boolean fullTimeYoN, int departmentId) throws Throwable {
            updateEmployee(EmployeeModel.builder().employeeId(2)
                    .firstName(employeeFirstName)
                    .lastName(employeeLastName)
                    .salary(BigDecimal.valueOf(employeeSalary))
                    .departmentId(departmentId)
                    .fullTime(fullTimeYoN)
                    .build());
        }

        @Then("the employee information is updated to {word}, {word}, {double}, {} and {int}")
        public void employeeInfoIsUpdatedTo(String employeeFirstName, String employeeLastName, double employeeSalary, boolean fullTimeYoN, int departmentId) throws Throwable {
            Optional<EmployeeModel> employee = getEmployeeById(2);
            Assertions.assertAll(
                () -> assertEquals(employeeFirstName, employee.get().getFirstName()),
                () -> assertEquals(employeeLastName, employee.get().getLastName()),
                () -> assertEquals(BigDecimal.valueOf(employeeSalary), employee.get().getSalary()),
                () -> assertEquals(Optional.of(departmentId), Optional.of(employee.get().getDepartmentId())),
                () -> assertEquals(fullTimeYoN,employee.get().getFullTime())
            );
        }

    @When("^the client gets employees (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employeee = getEmployeeById(employeeId);
    }

    @Then("^the information on the employee is$")
    public void employeeInfo() throws Throwable {
        Assertions.assertAll(
                () -> assertEquals(2,employeee.get().getEmployeeId()),
                () -> assertEquals("Hello",employeee.get().getFirstName()),
                () -> assertEquals("Gea",employeee.get().getLastName()),
                () -> assertEquals(BigDecimal.valueOf(30000.00),employeee.get().getSalary()),
                () -> assertEquals(true,employeee.get().getFullTime()),
                () -> assertEquals(10,employeee.get().getDepartmentId())
        );
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emp = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            emp.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i+1))
                    .lastName(given.get(i+2))
                    .fullTime(Boolean.parseBoolean(given.get(i+3)))
                    .departmentId(Integer.parseInt(given.get(i+4)))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i+5))))
                    .build());
        }
        return emp;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId).get().getEmployeeId();
        });
    }

    @And("the error message is {int} : [Entity with id {int} was not found]")
    public void errorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }
    }

