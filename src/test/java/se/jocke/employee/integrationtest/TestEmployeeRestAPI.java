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
import se.jocke.api.*;
import se.jocke.api.EmployeeModel;
import se.jocke.api.EmployeeModel;
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


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }


    @When("the client updates attributes for employee {int} to {word}, {word}, {word}, {word}, {int}")
    public void updateNameOfEmployee(int empId, String firstName, String lastname, String bdSalary, String boolFullTime, int depid) throws Throwable {
        updateEmployee(EmployeeModel.builder().
                employeeId(empId)
                .firstName(firstName)
                .lastName(lastname)
                .salary(BigDecimal.valueOf(Double.parseDouble(bdSalary)))
                .fullTime(Boolean.parseBoolean(boolFullTime))
                .departmentId(depid)
                .build());
    }

    @Then("the attributes for employeeId {int} are updated to {word}, {word}, {word}, {word}, {int}")
    public void nameOfEmployeeIsUpdated(int empId, String firstName, String lastName, String bdSalary, String boolFullTime, Integer depid) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(empId);
        Assert.assertEquals(firstName, employee.get().getFirstName());
        Assert.assertEquals(lastName, employee.get().getLastName());
        Assert.assertEquals(new BigDecimal(bdSalary).setScale(2), employee.get().getSalary().setScale(2));
        Assert.assertEquals(new Boolean(boolFullTime), employee.get().getFullTime());
        Assert.assertEquals(depid, employee.get().getDepartmentId());
    }



    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("the name is {word} {word}")
    public void nameOfEmployeeIs(String firstname, String lastname) throws Throwable {
        Assert.assertEquals(firstname, employee.get().getFirstName());
        Assert.assertEquals(lastname, employee.get().getLastName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {

        List<EmployeeModel> emps = new ArrayList<>();

        for (int i = 0; i < given.size() - 1;) {
            emps.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i++)))
                    .firstName(given.get(i++))
                    .lastName(given.get(i++))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i++))))
                    .fullTime(Boolean.parseBoolean(given.get(i++)))
                    .departmentId(Integer.parseInt(given.get(i++)))
                    .build());
        }
        return emps;
    }
    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted, checked by failed getId")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessageEmployee(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

    EmployeeModel myEmplModel;
    @When("The client tries to delete non-existant employee with id {int}")
    public void checkEmployeeDeleteNonExistant(Integer testEmployeeId) {
        myEmplModel = (EmployeeModel.builder()
                .employeeId(testEmployeeId)
                .firstName("a")
                .lastName("b")
                .salary(BigDecimal.valueOf(12312))
                .fullTime(true)
                .departmentId(-123)
                .build());
    }

    @Then("The delete should be OK although non-existant object, that is idempotent delete")
    public void employeeDeleteNonexistant() {
        // Check that this delete is idempotent and does not cause an error/exception, although it does not exist
        Optional<EmployeeModel> emplModelResult = deleteEmployee(myEmplModel);
        Assertions.assertEquals(true,emplModelResult.get().equals(myEmplModel));
    }
}
