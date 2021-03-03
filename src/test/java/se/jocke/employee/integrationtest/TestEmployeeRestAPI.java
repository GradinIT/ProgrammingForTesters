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
import static se.jocke.TestClient.*;

public class TestEmployeeRestAPI extends TestClient{

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^The client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^The client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("The client updates attributes for employee to {word},{word},{word},{word},{int}")
    public void updateNameOfEmployee(String employeeName,String lastname,String salary,String bool, int dep) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(1).
                firstName(employeeName)
                .lastName(lastname)
                .salary(BigDecimal.valueOf(Integer.parseInt(salary)))
                .fullTime(Boolean.parseBoolean(bool))
                .departmentId(dep)
                .build());
    }

    @Then("The attribute is updated to {word},{word},{word},{word},{int}")
    public void nameOfEmployeeIsUpdated(String employeeName,String employeeLastname,String salary,String fulltime,Integer depId) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
        Assert.assertEquals(employeeLastname, employee.get().getLastName());
        Assert.assertEquals(BigDecimal.valueOf(Integer.parseInt(salary)), employee.get().getSalary());
        Assert.assertEquals(Boolean.parseBoolean(fulltime), employee.get().getFullTime());
        Assert.assertEquals(depId, employee.get().getDepartmentId());

    }

    @When("^The client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^The name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Alex3", employee.get().getFirstName());
    }

    @Given("^The employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> empls = new ArrayList<>();
        for (int i = 0; i < given.size() - 1;) {
            empls.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i++)))
                    .firstName(given.get(i++))
                    .lastName(given.get(i++))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i++))))
                    .fullTime(Boolean.parseBoolean(given.get(i++)))
                    .departmentId(Integer.parseInt(given.get(i++)))
                    .build());
        }
        return empls;
    }

    @When("The client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());

    }

    Throwable exceptionThatWasThrown;

    @Then("The employee {int} is deleted, checked by failed getId")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("The error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessageEmployee(Integer errorCode, Integer employeeId) {
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
        // Check that this delete is idempotent and does not cause an error/exception, although it does not exist
        Optional<EmployeeModel> emplModelResult = deleteEmployee(myEmplModel);
        Assertions.assertEquals(true,emplModelResult.get().equals(myEmplModel));
    }
}