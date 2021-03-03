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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    private static final int NUMBER_OF_SALARY_DECIMALS = 2;

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^The client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^The client receives (\\d+) employees$")
    public void TheClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("The client updates attributes for employee {int} to {word}, {word}, {word}, {word}, {int}")
    public void updateNameOfEmployee(Integer employeeId, String firstName, String lastName, String salary, String fullTime, int departmentId) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .salary(BigDecimal.valueOf(Double.parseDouble(salary)))
                .fullTime(Boolean.parseBoolean(fullTime))
                .departmentId(departmentId)
                .build());
    }
    @When("The attributes for employeeId {int} are updated to {word}, {word}, {word}, {word}, {int}")
    public void nameOfEmployeeIsUpdated(Integer employeeId, String firstName, String lastName, String salary, String fullTime, Integer departmentId)throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(employeeId);
        Assert.assertEquals(firstName, employee.get().getFirstName());
        Assert.assertEquals(lastName, employee.get().getLastName());
        Assert.assertEquals(BigDecimal.valueOf(Double.parseDouble(salary)).setScale(NUMBER_OF_SALARY_DECIMALS), employee.get().getSalary().setScale(NUMBER_OF_SALARY_DECIMALS));
        Assert.assertEquals(Boolean.parseBoolean(fullTime), employee.get().getFullTime());
        Assert.assertEquals(departmentId,employee.get().getDepartmentId());
    }

    @When("^The client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("The name is {word} {word}")
    public void nameOfEmployeeIs(String firstname, String lastname) throws Throwable {
        Assert.assertEquals(firstname, employee.get().getFirstName());
        Assert.assertEquals(lastname, employee.get().getLastName());
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; ) {
            employees.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i++)))
                    .firstName(given.get(i++))
                    .lastName(given.get(i++))
                    .salary(BigDecimal.valueOf(Float.parseFloat(given.get(i++))))
                    .fullTime(Boolean.parseBoolean(given.get(i++)))
                    .departmentId(Integer.parseInt(given.get(i++)))
                    .build());
        }
        return employees;
    }
    @Given("^The employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    @When("The client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        EmployeeModel empModel = getEmployeeById(employeeId).get();
        deleteEmployee(empModel);
    }

    Throwable exceptionThatWasThrown;

    @Then("The employee {int} is deleted, checked by failed getId")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("The error message is {int} : [Entity with id {int} not found]")
    public void checkEmployeeErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

    EmployeeModel myEmplModel;

    @When("The client tries to delete non-existant employee with id {int}")
    public void checkEmployeeDeleteNonExistant(Integer testEmployeeId) {
        myEmplModel = (EmployeeModel.builder()
                .employeeId(testEmployeeId)
                .firstName("a")
                .lastName("b")
                .salary(BigDecimal.valueOf(12312))
                .fullTime(true)
                .departmentId(-123)
                .build());
    }

    @Then("The delete should be OK although non-existant object, that is idempotent delete")
    public void employeeDeleteNonexistant() {
        // Check that this delete is idempotent and does not cause an error/exception,
        // and returns the same object, although it does not exist
        Optional<EmployeeModel> emplModelResult = deleteEmployee(myEmplModel);
        Assertions.assertEquals(true, emplModelResult.get().equals(myEmplModel));
    }
}
