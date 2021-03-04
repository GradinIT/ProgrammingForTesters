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
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null; /*Optional.ofNullable(EmployeeModel.builder()  //23 | Kebne  | Kajse   | 45000 | true  | 1 |
            .employeeId(23)
            .firstName("Kebne")
            .lastName("Kajse")
            .salary(BigDecimal.valueOf(45000.00))
            .fullTime(true)
            .departmentId(1)
            .build());*/

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
    public void updateNameOfEmployee(String employeeLastName) throws Throwable{

        EmployeeModel employeeModel = getEmployeeById(1).get();

        updateEmployee(EmployeeModel.builder()
                .employeeId(employeeModel.getEmployeeId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeLastName)         //Error: java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because "this.employee" is null
                .salary(employeeModel.getSalary())                 // Why: employee given null when created. I have to build a client in this method to use. First I tried to build
                .fullTime(employeeModel.getFullTime())              // an instance at the top where employee was declared, but it did not work, or I changed course in the middle.
                .departmentId(employeeModel.getDepartmentId())      //I try it this way instead.
                .build());
    }

    /* MALL
    * @Then("the name is updated to (.+)$")
    public void nameOfDepartmentIsUpdated(String departmentName) throws Throwable {
    Optional<DepartmentModel> department = getDepartmentById(1);
        Assert.assertEquals(departmentName, department.get().getDepartmentName());  */

    @Then("the lastName is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeLastName) throws Throwable{
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeLastName, employee.get().getLastName());
    }

    /*MALL
    *  @When("^the client gets department (\\d+)$")
    public void getTheDepartmentById(Integer departmentId) throws Throwable {
        department = getDepartmentById(departmentId);
    }*/

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable{
        employee = getEmployeeById(employeeId);
    }

    /* MALL
    * @Then("^the name is$")
    public void nameOfDepartmentIs() throws Throwable {
        Assert.assertEquals("Coding", department.get().getDepartmentName());
    }*/

    @Then("^the lastName is$")               //Error: org.junit.ComparisonFailure:  Expected :lastName1 Actual:Development
    public void nameOfEmployeeIs() throws Throwable{            //Why?: Because I had written the wrong annotation @When instead of @Then
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
                    .departmentId(i+5)
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

    @When("deletes employee {int}") // correct lines: Scenario: deletes employee 55 When deletes employee 56 When deletes employee 57 Then the employee 1 is deleted
    public void deleteEmployee(Integer employeeId){

        //deleteEmployee(getEmployeeById(employeeId).get()); Error. Why? The program
                                                            //Still error, correcting the command after @When
        deleteEmployee(EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName("")
                .lastName("")
                .departmentId(1234)
                .salary(BigDecimal.ONE)
                .fullTime(false)
                .build());
    }
     Throwable exceptionThatWasThrown; //Jocke changed this in Lindas method to a try-catch block instead

    /*MALL
    * @Then("the department {int} is deleted")
    public void departmentIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(departmentId);
        });
    }*/
/*

    @Then("the employee {int} is deleted")  //Replacing this with Lindas try-catch-block instead
    public void employeeIsDeleted(Integer employeeId){
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () ->{
            getTheEmployeeById(employeeId);
        });
    }
*/
    @Then("the employee {int} is deleted")  //Correct line: Then the employee 55 is deleted
    public void employeeIsDeleted(Integer employeeId){
        try {
            getEmployeeById(employeeId);
            Assert.fail("employee id: " + employeeId + "not deleted");
        }
        catch (Exception e){
            exceptionThatWasThrown = e;
            Assert.assertEquals("404 : [Entity with id 55 not found]",e.getMessage());
        }
    }
   // For comparison, not to activate @Then("the employee error message is {int} : [Entity with id {int} not found]")
    @And("the employee error message is {int} : [Entity with id {int} not found]")  // Deal with this later
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
