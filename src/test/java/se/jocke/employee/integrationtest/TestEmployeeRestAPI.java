package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import liquibase.pro.packaged.T;
import org.junit.Assert;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employees$")
    public void getAllEmployees() throws Throwable {
        employees = getAllEmployees(1); // getAllEmployees don't use parameter try to remove
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates name for employee to (.+)$")
    public void updateFirstNameOfEmployee(String employeeName) throws Throwable{
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
    }

    @Then("^the name is updated to (.+)$")
    public void firstNameOfEmployeeIsUpdated(String employeeName) throws Throwable{
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }

    @When("^the client gets department (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable{
        employee = getEmployeeById(employeeId);
    }

    @Then("^the name is$")
    public void nameOfEmployeeIs() throws Throwable{
        Assert.assertEquals("Egon", employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(TestClient::createEmployee);
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() -1; i += 6) {
            employees.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i +1))
                    .lastName(given.get(i + 2))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i + 3))))
                    .fullTime(Boolean.getBoolean(given.get(i + 4)))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }
        return employees;
    }
    @When()
    public void deleteEmployee() {

    }
    @Then()
    public void employeeIsDeleted() {

    }
    @And()
    public void checkErrorMessage() {

    }
}
