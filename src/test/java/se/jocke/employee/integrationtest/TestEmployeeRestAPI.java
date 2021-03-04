package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
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
    Optional<EmployeeModel> optionalEmployeeModel = null;
    Optional<List<EmployeeModel>> optionalEmployeeModelList = null;

    @When("the client calls /employee")
    public void getAll() {
        optionalEmployeeModelList = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, optionalEmployeeModelList.get().size());
    }

    @When("the client updates first name of employee (\\d+) to (.+)$")
    public EmployeeModel theClientUpdatesFirstName(int employeeId, String firstName) {
        optionalEmployeeModel = getEmployeeById(employeeId);
        EmployeeModel updatedEmployeeModel = null;

        if (optionalEmployeeModel.isPresent()) {
            updatedEmployeeModel = EmployeeModel.builder()
                    .employeeId(employeeId)
                    .firstName(firstName)
                    .lastName(optionalEmployeeModel.get().getLastName())
                    .salary(optionalEmployeeModel.get().getSalary())
                    .fullTime(optionalEmployeeModel.get().getFullTime())
                    .departmentId(optionalEmployeeModel.get().getDepartmentId())
                    .build();
            updateEmployee(updatedEmployeeModel);
        }
        return updatedEmployeeModel;
    }

    @Then("^the name of employee (\\d+) is updated to (.+)$")
    public void theNameIsUpdated(int employeeId, String firstName) {
        optionalEmployeeModelList = getAllEmployees();
        EmployeeModel updatedEmployee = theClientUpdatesFirstName(employeeId, firstName);
        Assert.assertEquals(updatedEmployee, getEmployeeById(employeeId).get());
    }

    @And("^the total number of employees is still (\\d+)$")
    public void checkTheListSizeOfEmployees(int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, optionalEmployeeModelList.get().size());
    }

    private List<EmployeeModel> makeEmployeesList(List<String> given) {
        List<se.jocke.api.EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            employees.add(se.jocke.api.EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .salary(new BigDecimal(given.get(i + 3)))
                    .fullTime(Boolean.valueOf(given.get(i + 4)))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }
        return employees;
    }

    @Given("^the employee$")
    public void givenEmployees(DataTable employeeDataTable) {
        List<EmployeeModel> listOfEmployees = makeEmployeesList(employeeDataTable.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    @When("the client deletes employee {int}")
    public void theClientDeletesEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("employee {int} is deleted")
    public void employeeIsDeleted(int employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    Throwable exceptionThatWasThrown2;

    @When("the client tries to get employee {int}")
    public void searchForNonExistentEmployee(Integer employeeId) {

        exceptionThatWasThrown2 = assertThrows(HttpClientErrorException.class, () -> optionalEmployeeModel = getEmployeeById(employeeId));

    }

    @Then("employee {int} not found exception is thrown")
    public void throwNotFoundException(Integer employeeId) {
        assertEquals("404 : [Entity with id "+employeeId+" not found]", exceptionThatWasThrown2.getMessage());
    }

    @When("the client creates employee {int}")
    public void theClientCreatesEmployee(int employeeId) {
        createEmployee(getEmployeeById(employeeId).get());
    }

    @But("the employeeId {int} already exists")
    public void theEmployeeIsNotAllreadyInDataBase(int employeeId) {

    }

    @Then("the errormessage is {int} : [Entity with id {int} already in databse]")
    public void checkErrorMessage(int errorCode, int employeeId) {
        Assertions.assertEquals(errorCode+" : [Entity with id " + employeeId +" not found]",exceptionThatWasThrown.getMessage());
            }
}
