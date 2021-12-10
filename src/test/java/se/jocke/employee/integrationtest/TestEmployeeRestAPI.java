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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEmployeeRestAPI extends EmployeeTestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("the client calls \\/employee")
    public void the_client_calls_employee() { employees = getAllEmployees();
    }
    @Then("the client receives {int} employees")
    public void the_client_receives_employees(int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @Given("the employees")
    public void the_employees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(department -> createEmployee(department));
    }
    // kanske försöka skapa lista på EmployeeId eller annat värde
    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
            emps.add(EmployeeModel.builder().departmentId(Integer.parseInt(given.get(i))).firstName(given.get(i + 1)).build());
        }
        return emps;
    }

    @When("the client gets employee (\\d+)")
    public void the_client_gets_employeeId(int employeeId) {
        employee = getEmployeeById(employeeId);
    }
    @Then("firstname is (.+)")
    // .+ är String
    public void firstname_is_pelle(String employeeFirstName)
    // sätter namnet till pelle i cucumberkoden
    {
        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());
    }

    @When("the client updates firstname for employee to (.+)")
    public void the_client_updates_firstname_for_employee_to_runar(String employeeFirstName) {
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeFirstName).build());
    }
    @Then("the firstname is updated to (.+)")
    public void the_firstname_is_updated_to_runar(String employeeName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }
   /* @When("the client calls findById")
    public void the_client_calls_find_by_id() {
    }
    @Then("the client receives id {int}")
    public void the_client_receives_id(Integer int1) {
    }
    */
    @When("the client deletes employee {int}")
    public void the_client_deletes_employee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }
        private Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void the_employee_is_deleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
    });
    }
    @And("the error message is {int} : [\"Entity with id {int} not found\"]")
    public void the_error_message_is(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode +
                " : [Entity with id "+employeeId+" not found]", exceptionThatWasThrown.getMessage());

}
}
