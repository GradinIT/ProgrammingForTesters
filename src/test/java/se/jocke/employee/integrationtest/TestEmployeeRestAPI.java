package se.jocke.employee.integrationtest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;
    private Integer empID = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable{
        employees = getAllEmployees(empID);
    }
    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }




    @When("^the client updates firstName for employee to (.+)$")
    public void updateFirstNameOfEmployee(String firstName) throws Throwable {

        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(firstName).lastName("Codersson").fullTime(true).salary(BigDecimal.valueOf(25000.0)).departmentId(1).build());

    }
    @Then("the firstName is updated to (.+)$")
    public void firstNameOfEmployeeIsUpdated(String employeeFirstName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());




    }
    @When("^the client updates lastName for employee to (.+)$")
    public void updateLastNameOfEmployee(String lastName) throws Throwable {
        updateEmployee(EmployeeModel.builder().employeeId(1).lastName(lastName).firstName("Coder").fullTime(true).salary(BigDecimal.valueOf(25000.0)).departmentId(1).build());

    }
    @Then("the lastName is updated to (.+)$")
    public void lastNameOfEmployeeIsUpdated(String employeeLastName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeLastName, employee.get().getLastName());

    }
}
