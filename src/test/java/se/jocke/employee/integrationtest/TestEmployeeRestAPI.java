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

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployeeRestAPI extends TestClient {
    Optional<EmployeeModel> optionalEmployeeModel;
    Optional<List<EmployeeModel>> optionalEmployeeModelList;
    EmployeeModel employeeModel;
    Throwable exceptionThatWasThrown;



    @When("the client calls /employee")
    public void getAll() {
        optionalEmployeeModelList = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, optionalEmployeeModelList.get().size());
    }



    @When("^the client calls employee (\\d+)$")
    public void theClientCallsEmployee(int employeeId) {
        optionalEmployeeModel = getEmployeeById(employeeId);
    }

    @Then("employee {int} is found")
    public void employeeIsFound(int employeeId) {
        assertTrue(optionalEmployeeModel.isPresent());
    }

    @When("the client updates first name of employee (\\d+) to (.+)$")
    public void theClientUpdatesFirstName(int employeeId, String firstName) {

        employeeModel = EmployeeModel.builder()
                    .employeeId(employeeId)
                    .firstName(firstName)
                    .lastName(optionalEmployeeModel.get().getLastName())
                    .salary(optionalEmployeeModel.get().getSalary())
                    .fullTime(optionalEmployeeModel.get().getFullTime())
                    .departmentId(optionalEmployeeModel.get().getDepartmentId())
                    .build();

        updateEmployee(employeeModel);
    }

    @Then("^the name of employee (\\d+) is updated to (.+)$")
    public void theNameIsUpdated(int employeeId, String firstName) {
        Assert.assertEquals(employeeModel.getFirstName(), getEmployeeById(employeeId).get().getFirstName());
    }



    @Given("^the employee$")
    public void givenEmployees(DataTable employeeDataTable) {
        List<EmployeeModel> listOfEmployees = getEmployeesList(employeeDataTable.asList());
        listOfEmployees.forEach(TestClient::createEmployee);
    }

    @And("the total number of employees is {int}")
    public void totalNumberOfEmployyes(int numberOfEmployees) {
        Assertions.assertEquals(numberOfEmployees, getAllEmployees().get().size());
    }

    @When("the client deletes employee {int}")
    public void theClientDeletesEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    @Then("employee {int} is no longer found in search")
    public void employeeIsDeleted(int employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }



    @When("the client tries to get employee {int}")
    public void searchForNonExistentEmployee(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> optionalEmployeeModel = getEmployeeById(employeeId));
    }

    @Then("employee {int} not found exception is thrown")
    public void throwNotFoundException(Integer employeeId) {
        assertEquals("404 : [Entity with id "+employeeId+" not found]", exceptionThatWasThrown.getMessage());
    }



    private List<EmployeeModel> getEmployeesList(List<String> given) {
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
}
