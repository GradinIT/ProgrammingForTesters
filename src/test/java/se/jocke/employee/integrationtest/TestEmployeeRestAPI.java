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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;



    @When("client calls all employees")
    public void clientCallsEmployee() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("client receives {int} employees")
    public void clientGotAllEmployees(int numberOfEmployees) {
        assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^client updates name for employee to (.+)$")
    public void updateNameOfEmployee(String employeeName) {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(employeeName)
                .lastName("lastName1")
                .salary(BigDecimal.valueOf(25000).setScale(2))
                .fullTime(true)
                .departmentId(1)
                .build());
    }

    @Then("^the name of employee is updated to (.+)$")
    public void nameOfEmployeeUsUpdated(String employeeName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        assertEquals(employeeName, employee.get().getFirstName());
    }


    @When("client gets employee {int}")
    public void clientGetsEmployee(int arg0) {
        employee = getEmployeeById(arg0);
    }

    @Then("employee first name is")
    public void employeeFirstNameIs() {
        assertEquals("Gunnar", employee.get().getFirstName());
    }

    @Given("all employees")
    public void allEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeListOfEmployees(employees.asList());
        listOfEmployees.stream().forEach(TestClient::createEmployee);

    }

    private List<EmployeeModel> makeListOfEmployees(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for(int i = 0; i < given.size() -1; i += 6) {
            emps.add(EmployeeModel.builder()
            .employeeId(Integer.parseInt(given.get(i)))
            .firstName(given.get(i + 1))
            .lastName(given.get(i + 2))
            .salary(BigDecimal.valueOf(Double.parseDouble(given.get(i + 3))))
            .fullTime(Boolean.valueOf(given.get(i + 4)))
            .departmentId(Integer.parseInt(given.get(i + 5)))
            .build());
        }
        return emps;
    }


    @When("client deletes employee {int}")
    public void clientDeletesEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;
    @Then("employee {int} is deleted")
    public void employeeIsDeleted(int employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("error message is {int} : [Entity with id {int} not found]")
    public void errorMessageIsEntityWithIdNotFound(int arg0, int arg1) {
        assertEquals(arg0 + " : [Entity with id " + arg1 +" not found]", exceptionThatWasThrown.getMessage());
    }

}
