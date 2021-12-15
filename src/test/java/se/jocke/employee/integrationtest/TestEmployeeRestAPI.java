package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.department.api.DepartmentModel;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends EmployeeTestClient {
    List<EmployeeModel> employees;
    List<EmployeeModel> employee;
    EmployeeModel employeeModel;

    @When("the client calls \\/employee")
    public void the_client_calls_employee() {
        Optional<List<EmployeeModel>> employeeModels = getAllEmployees();
        employees = employeeModels.get();
    }

    @Then("the client receives {int} employees")
    public void the_client_receives_employees(int expectedNumberOfEmployees) {
        Assert.assertEquals(expectedNumberOfEmployees, employees.size());
    }

    @When("the client gets employee {int}")
    public void the_client_gets_employee(Integer employeeById) {
        employeeModel = getEmployeeById(employeeById).get();
    }
    @Then("firstname is (.+)")
    public void firstname_is_runar(String employeeFirstName) {
        Assert.assertEquals(employeeFirstName, employeeModel.getFirstName());

    }

    /*@When("the client updates lastname for employee {int} to Runar")
    public void the_client_updates_firstname_for_employee_to_runar(String arg1, String arg2) {
        updateEmployee(EmployeeModel
                .builder()
                .employeeId(1)
                .firstName(employeeFirstName)
                .lastName(employeeLastName)
                .build());
    }*/

    @Then("the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_runar(String employeeFirstName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());
    }

    @Given("the employees")
    public void the_employees(DataTable dataTable) {
        makeDepartmentList(dataTable.asList())
                .stream()
                .forEach( employeeModel -> createEmployee(employeeModel));
    }

    private List<EmployeeModel> makeDepartmentList(List<String> given) {
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
    public void theClientDeletesEmployee(Integer employeeID) { deleteEmployee(getEmployeeById(employeeID).get());
    }
    private Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void theEmployeeIsDeleted(int employeeID) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeID);
        });
    }

    @And("the client gets error message {string}")
    public void the_error_message_is(Integer errorCode, String employeeID) {
        Assertions.assertEquals(errorCode + ": [Entity with id"+employeeID+" not found]", exceptionThatWasThrown.getMessage());
    }









    @Then("the employee {int} exists")
    public void theEmployeeExists(int arg0) {

    }
    @And("the client gets message {string}")
    public void theClientGetsMessage(String arg0) {
        
    }


    @Given("new employee")
    public void newEmployee(DataTable dataTable) {
        makeDepartmentList(dataTable.asList())
                .stream()
                .forEach(employeeModel -> createEmployee(employeeModel));
    }

    @When("the client updates lastname for employee {int} to Carola")
    public void theClientUpdatesLastnameForEmployeeToCarola(int arg0) {
    }

}
