package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;
    Throwable exceptionThatWasThrown;

    @When("the client calls /employee")
    public void getAll() {
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void allEmployeesSize(int numberOfEmployees)
    {
        Assertions.assertEquals(numberOfEmployees , employees.get().size());

    }

    @When("^the client updates firstName for employeeId (\\d+) to (.*)$")
    public void updateFirstName(Integer employeeId, String firstName){

        employee = getEmployeeById(employeeId);

        EmployeeModel updatedEmployee = EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(employee.get().getLastName())
                .fullTime(employee.get().getFullTime())
                .salary(employee.get().getSalary())
                .departmentId(employee.get().getDepartmentId())
                .build();
        employee = updateEmployee(updatedEmployee);
    }

    @Then("^the firstName is updated to (.*)$")
    public void firstNameWasUpdated(String firstName){

        Assertions.assertEquals(firstName, employee.get().getFirstName());
    }

    @When("the client gets employeeId {int}")
    public void getTheEmployeeById(Integer employeeId){
             employee = getEmployeeById(employeeId);
    }

    @Then("the firstName is {string}")
    public void assertingUpdatedFirstName(String firstName){
        Assertions.assertEquals(firstName, employee.get().getFirstName());
    }

    @When("the client updates salary for employeeId {int} to {bigdecimal}")
    public void clientUpdatesSalary(Integer employeeId, BigDecimal salary){
        employee = getEmployeeById(employeeId);
        EmployeeModel employeeModel = employee.get();
        EmployeeModel updatedEmployee = EmployeeModel.builder()
                .employeeId(employeeModel.getEmployeeId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .salary(salary)
                .fullTime(employeeModel.getFullTime())
                .departmentId(employeeModel.getDepartmentId())
                .build();
        employee = updateEmployee(updatedEmployee);
    }

    @Then("the salary is updated to {bigdecimal}")
    public void assertUpdatedSalary(BigDecimal salary){
        Assertions.assertEquals(salary ,employee.get().getSalary());
    }

    @Given("the employee")
    public void givenEmployees(DataTable dataTable){

        for (int i = 0; i < dataTable.height(); i++) {
            List<String> listOfData = dataTable.row(i);

            EmployeeModel employee = EmployeeModel.builder()
                    .employeeId(Integer.parseInt(listOfData.get(0)))
                    .firstName(listOfData.get(1))
                    .lastName(listOfData.get(2))
                    .salary(BigDecimal.valueOf(Double.valueOf(listOfData.get(3))))
                    .fullTime(Boolean.getBoolean(listOfData.get(4)))
                    .departmentId(Integer.parseInt(listOfData.get(5)))
                    .build();

            createEmployee(employee);
        }

    }
    @When("the client deletes employee {int}")
    public void whenDeleteEmployee(Integer employeeId){
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    @Then("the employee {int} is deleted")
    public void thenEmployeeWasDeleted(Integer employeeId){
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the employee error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

    @When("the client creates an employee with id {int}")
    public void whenCreatingEmployee(Integer employeeId){
        EmployeeModel employeeModel = EmployeeModelTestBuilder.builder().employeeId(employeeId).build();
        createEmployee(employeeModel);
    }

    @Then("the employee with id {int} exists")
    public void thenEmployeeExists(Integer employeeId){

        Assertions.assertTrue(getEmployeeById(employeeId).isPresent());
    }

    @When("the client creates a duplicate employee with id {int}")
    public void whenCreatingDuplicate(Integer employeeId){
        employee = Optional.of(EmployeeModelTestBuilder.builder().employeeId(employeeId).build());

    }
    @Then("the client receives an error")
    public void thenExceptionIsThrown(){
        exceptionThatWasThrown = Assertions.assertThrows(HttpServerErrorException.class, () ->{
            createEmployee(employee.get());
        });
    }

}
