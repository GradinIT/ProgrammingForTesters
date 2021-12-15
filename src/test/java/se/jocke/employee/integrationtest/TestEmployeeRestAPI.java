package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    // Scenario: client gets all employees
    @When("^the client calls \\/employee$") //regexp style
    public void getAllRequestedEmployees() {
        employees = getAllEmployees();
    }
    @Then("the client receives {int} employees")
    public void theClientReceivesEmployees(int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, getAllEmployees().get().size());
    }


    //Scenario: client updates firstname for employee 1
    //When the client updates firstname to "Runar" for employee 1
    @When("the client updates firstname to {string} for employee {int}")
    public void updateFirstNameOfEmployee(String firstname, int employeeId)  {
        employee = getEmployeeById(employeeId);
        updateEmployee(EmployeeModel.builder()
                .employeeId(employee.get().getEmployeeId())
                .firstName(firstname)
                .lastName(employee.get().getLastName())
                .salary(employee.get().getSalary())
                .fullTime(employee.get().getFullTime())
                .departmentId(employee.get().getDepartmentId())
                .build());
    }
    @Then("the firstname is updated to {string}")
    public void firstNameOfEmployeeNIsUpdated(String firstName)  {
        Optional<EmployeeModel> employee= getEmployeeById(1); //should we include id in step instead?
        Assert.assertEquals(firstName, employee.get().getFirstName());
    }

    //Next Scenario: Employee firstname is "Runar"
    @When("the client gets employee {int}")
    public void the_client_gets_employee(int employeeId) {

        employee = getEmployeeById(employeeId);
    }
    @Then("firstname is {string}")
    public void firstname_is_runar(String firstName) {
        Assertions.assertEquals(firstName,employee.get().getFirstName());
    }
    //Note: next Scenario to change back "Runar" to "firstName1" for employeeId 1 will reuse previous code

    //Scenario: Delete Employee (not included in feature due to error)
    @Given("the employees")
    public void the_employees(DataTable dataTable) {
        makeEmployeeList(dataTable.asList())
                .stream()
                .forEach( employeeModel -> createEmployee(employeeModel));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for(int i = 0 ; i < given.size();) {
            employeeModels.add( EmployeeModel.builder()
                    .employeeId(Integer.valueOf(given.get(i++)))
                    .firstName(given.get(i++))
                    .lastName(given.get(i++))
                    .salary(new BigDecimal(given.get(i++)))
                    .fullTime(Boolean.valueOf(given.get(i++)))
                    .departmentId(Integer.parseInt(given.get(i++)))
                    .build());
        }
        return employeeModels;
    }

    @When("the client deletes employee {int}")
    public void theClientDeletesEmployee(Integer employeeId) {

        deleteEmployee(getEmployeeById(employeeId).get());
    }
    private Throwable exceptionThatWasThrown;


    @Then("the employee {int} is deleted")
    public void theEmployeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error message is {int} : [\"Entity with id {int} not found\"]")
    public void checkErrorMessageIs(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id "+employeeId+" not found]", exceptionThatWasThrown.getMessage());
   }
//  new Scenario: Create Employee
@Given("new employee")
public void employee(DataTable dataTable) {
    makeEmployeeList(dataTable.asList())
            .stream()
            .forEach(employeeModel -> createEmployee(employeeModel));
}
    @Then("the employee {int} exists")
    public void theEmployeeExists(Integer employeeId) {
        Optional<EmployeeModel> inDatabase = getEmployeeById(employeeId);
        Assertions.assertEquals(Boolean.TRUE,inDatabase.isPresent()); // we check that the new employee is present in the database
        deleteEmployee(inDatabase.get()); // we delete the test-employee to restore the database.

    }
}
