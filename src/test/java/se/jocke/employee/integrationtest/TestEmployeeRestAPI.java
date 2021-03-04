package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
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
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    /* MALL
    public class TestDepartmentRestAPI extends TestClient {
    Optional<List<DepartmentModel>> departments = null;
    Optional<DepartmentModel> department = null;
*/

    /* MALL
    * @When("^the client calls /department$")
    public void getAll() throws Throwable {
        departments = getAllDepartments();
    }*/

    @When("^calls /employee$")
    public void getAll() throws Throwable{
        employees = getAllEmployees();
    }

    /* MALL
    * @Then("^the client receives (\\d+) departments$")
    public void theClientGotAllDepartments(int numberOfDepartments) throws Throwable {
        Assert.assertEquals(numberOfDepartments, departments.get().size());
    }*/

    @Then("^receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees,employees.get().size());
    }

    /*  @When("^the client updates name for department to (.+)$")
    public void updateNameOfDepartment(String departmentName) throws Throwable {
        updateDepartment(DepartmentModel.builder().departmentId(1).departmentName(departmentName).build());
    } */

    @When("^updates lastName for employee to (.+)$")
    public void updateNameOfEmployee(String employeeFirstName) throws Throwable{
        updateEmployee(EmployeeModel.builder()
                .employeeId(1)
                .firstName(employeeFirstName)
                .lastName(employee.get().getLastName())
                .salary(employee.get().getSalary())
                .fullTime(employee.get().getFullTime())
                .departmentId(employee.get().getDepartmentId())
                .build());
    }

    /*MALL
    *  @When("^the client gets department (\\d+)$")
    public void getTheDepartmentById(Integer departmentId) throws Throwable {
        department = getDepartmentById(departmentId);
    }*/

    @When("^gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable{
        employee = getEmployeeById(employeeId);
    }

    /* MALL
    * @Then("^the name is$")
    public void nameOfDepartmentIs() throws Throwable {
        Assert.assertEquals("Coding", department.get().getDepartmentName());
    }*/

    @When("^the lastName of employee is$")
    public void nameOfEmployeeIs() throws Throwable{
        Assert.assertEquals("Everest", employee.get().getLastName());
    }

    /*MALL
    * @Given("^the departments$")
    public void givenDepartments(DataTable departments) {
        List<DepartmentModel> listOfDepartments = makeDepartmentList(departments.asList());
        listOfDepartments.stream().forEach(department -> createDepartment(department));
    }*/

    @Given(("^the employees"))
    public void givenEmployees(DataTable employees){
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream()
                .forEach(employee -> createEmployee(employee));
    }

    /*MALL
    * private List<DepartmentModel> makeDepartmentList(List<String> given) {
        List<DepartmentModel> deps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
            deps.add(DepartmentModel.builder()
            * .departmentId(Integer.parseInt(given.get(i)))
            * .departmentName(given.get(i + 1))
            * .build());
        }
        return deps;
    }*/

    private List<EmployeeModel> makeEmployeeList(List<String> given){
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size()-1; i +=6){
            emps.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i+1))
                    .lastName(given.get(i+2))
                    .salary(BigDecimal.valueOf(Long.parseLong(given.get(i+3))))
                    .fullTime(Boolean.parseBoolean(given.get(i+4)))
                    .departmentId(Integer.parseInt(given.get(i+5)))
                    .build());
        }
        return emps;
    }

    /* MALL
    * @When("the client deletes department {int}")
    public void deleteDepartment(Integer departmentId) {
        deleteDepartment(getDepartmentById(departmentId).get());
    }

    Throwable exceptionThatWasThrown;*/

    @When("deletes employee {int}")
    public void deleteEmployee(Integer employeeId){
        deleteEmployee(getEmployeeById(employeeId).get());
    }
    Throwable exceptionThatWasThrown;

    /*MALL
    * @Then("the department {int} is deleted")
    public void departmentIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(departmentId);
        });
    }*/

    @Then("employee {int} is deleted")
    public void employeeIsDeleted(Integer emploeeId){
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () ->{
            getTheEmployeeById(emploeeId);
        });
    }

    @And("error message for employee is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
