package se.jocke.employee.integrationtest;
import io.cucumber.datatable.DataTable;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TestEmployeeRestAPI extends EmployeeTestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;
    @When("the client calls employee")
    public void getAll() { employees = getAllEmployees();}
    // fungerar med {int} och utan $ och ^
    @Then("^the client receives (\\d+) employees$")
    public void the_client_receives_employees(int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
    @When("^the client updates firstname for employee (\\d+) to (.+)$")
    public void the_client_updates_firstname_for_employee_to_runar(Integer employeeId, String firstName) {
        employee = Optional.of(getEmployeeById(employeeId).get());
        EmployeeModel update = EmployeeModel.builder()
                .employeeId(employee.get().getEmployeeId())
                .departmentId(employee.get().getDepartmentId())
                .lastName(employee.get().getLastName())
                .salary(employee.get().getSalary())
                .fullTime(employee.get().getFullTime())
                .firstName(firstName)
                .build();
        employee = Optional.of(updateEmployee(update).get());
    }
    @Then("the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_runar(String firstName) {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(firstName, employee.get().getFirstName());
    }
    @When("^the client gets employee (\\d+)$")
    public void the_client_gets_employee(Integer employeeId) {
        employee = getEmployeeById(employeeId);
    }
    @Then("^firstname is (.+)$")
    public void firstname_is_runar(String employeeName) {
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }
    /*
    @Given("the employees")
    public void the_employees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) { //kanske ändring
            employeeModels.add(EmployeeModel.builder()
                            .employeeId(Integer.parseInt(given.get(i))) //kanske ändring
                            .firstName(given.get(i+1))
                            .lastName(given.get(i+1))
                            .salary(new BigDecimal(given.get(i+1)))
                            .fullTime(Boolean.valueOf(given.get(i+1)))
                            .departmentId(Integer.parseInt(given.get(i)))
                    .build());
        }
        return employeeModels;
    }







    Blev över?
    @When("the client calls findById")
    public void the_client_calls_find_by_id() {
    }
    @Then("the client receives id {int}")
    public void the_client_receives_id(Integer int1) {
    }
    */

    @Given("^the employees$")
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
    @Then("the error message is {int} : [\"Entity with id {int} not found\"]")
    public void the_error_message_is(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id "+employeeId+" not found]", exceptionThatWasThrown.getMessage());
    }
}

