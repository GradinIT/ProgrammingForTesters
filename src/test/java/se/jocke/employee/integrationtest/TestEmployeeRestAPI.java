package se.jocke.employee.integrationtest;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.employee.api.EmployeeModel;

import java.util.List;
import java.util.Optional;


public class TestEmployeeRestAPI extends EmployeeTestClient {
    private Optional<List<EmployeeModel>> employees = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    public void updateNameOfEmployee(String employeeName) throws Throwable {
        // Emil/Rasmus
    }

    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        // Emil/Rasmus
    }

    public void getTheEmployeeId(Integer employeeId) throws Throwable {
        // Ramin
    }

    public void nameOfEmployeeIs() throws Throwable {
        // Ramin
    }

    public void givenEmployees(DataTable employee) throws Throwable {
        // Oluyinka/Tim
    }

    public void deleteEmployee(Integer employeeId) throws Throwable {
        // Vladde
    }

//    private List<EmployeeModel> makeEmployeeList(List<String> given) {
            // Oluyinka/Tim
//    }

    private Throwable exceptionThatWasThrown;

    public void employeeIsDeleted(Integer employeeId) throws Throwable {
        // Vladde
    }

    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        // Vladde
    }
}