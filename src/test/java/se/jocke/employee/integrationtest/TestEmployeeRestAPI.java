package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @When("^client updates name for employee to (.+) lastname (.+)$")
    public void updateNameOfEmployee(String firstName, String lastName) {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(firstName)
                .lastName(lastName)
                .salary(BigDecimal.valueOf(25000).setScale(2))
                .fullTime(true)
                .departmentId(1)
                .build());
        //System.out.println("HÄR ÄR ALLA" + getAllEmployees());
    }

    @Then("^the name of employee is updated to (.+) lastname (.+)$")
    public void nameOfEmployeeUsUpdated(String firstName, String lastName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(firstName, employee.get().getFirstName()),
                () -> assertEquals(lastName, employee.get().getLastName())
        );
    }


    @When("client gets employee {int}")
    public void clientGetsEmployee(int employeeID) {
        employee = getEmployeeById(employeeID);
    }

    @Then("^employee first name is (.+)$")
    public void employeeFirstNameIs(String firstName) {
        assertEquals(firstName, employee.get().getFirstName());
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

    Throwable thrownException;

    @Then("employee {int} is deleted")
    public void employeeIsDeleted(int employeeId) {
            thrownException = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("error message is {int} : [Entity with id {int} not found]")
    public void errorMessageIsEntityWithIdNotFound(int arg0, int arg1) {
        assertEquals(arg0 + " : [Entity with id " + arg1 +" not found]", thrownException.getMessage());
    }

}
