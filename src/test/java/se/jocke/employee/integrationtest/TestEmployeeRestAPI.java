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
    Optional<EmployeeModel> employee = null;

    @When("^calls /employee$")
    public void getAll() throws Throwable{
        employees = getAllEmployees();
    }

    @Then("^receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees,employees.get().size());
    }

    @When("^updates lastName for employee to (.+)$")
    public void updateNameOfEmployee(String employeeLastName) throws Throwable{

        EmployeeModel employeeModel = getEmployeeById(1).get();

        updateEmployee(EmployeeModel.builder()
                .employeeId(employeeModel.getEmployeeId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeLastName)         //Error: java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because "this.employee" is null
                .salary(employeeModel.getSalary())                 // Why: employee given null when created. I have to build a client in this method to use. First I tried to build
                .fullTime(employeeModel.getFullTime())              // an instance at the top where employee was declared, but it did not work, or I changed course in
                .departmentId(employeeModel.getDepartmentId())      //the middle, can't remember which. I try it this way instead.
                .build());
    }

    @Then("the lastName is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeLastName) throws Throwable{
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeLastName, employee.get().getLastName());
    }

    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable{
        employee = getEmployeeById(employeeId);
    }

    @Then("^the lastName is$")               //Error: org.junit.ComparisonFailure:  Expected :lastName1 Actual:Development
    public void nameOfEmployeeIs() throws Throwable{            //Why?: Because I had written the wrong annotation @When instead of @Then
        Assert.assertEquals("Everest", employee.get().getLastName());
    }

    @Given(("^the employees"))
    public void givenEmployees(DataTable employees){
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees
                .forEach(TestClient::createEmployee); //using Method reference instead, courtesy IntelliJ hints.
        /*  original lambda!
        *         listOfEmployees.stream()
                .forEach(employee -> createEmployee(employee)); */
    }

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

    @When("deletes employee {int}") // correct lines: Scenario: deletes employee 55 When deletes employee 56 When deletes employee 57 Then the employee 1 is deleted
    public void deleteEmployee(Integer employeeId){

        //deleteEmployee(getEmployeeById(employeeId).get()); Error. Why? The program
        //Still error, correcting the command after @When
        deleteEmployee(EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName("")
                .lastName("")
                .departmentId(1)
                .salary(BigDecimal.valueOf(20000.00))
                .fullTime(false)
                .build());
    }

    Throwable exceptionThatWasThrown; //Jocke used this in Lindas method's try-catch block instead of the
                                        // asssertThrows in this following @Then block.

    @Then("the employee {int} is deleted")  //4/3-21 Replacing this with Lindas try-catch-block instead.
    public void employeeIsDeleted(Integer employeeId){
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () ->{  //5/3-21 Going back to this method. We know that the getByID-method triggers an exception.
            getTheEmployeeById(employeeId);                                             // we place that exception in the Throwable object, to be handled in the @And method.
        });
    }

    /*@Then("the employee {int} is deleted")  // This method controls that the employee actually has been deleted. It tries to find the employee by id. If it does, the try block
    public void employeeIsDeleted(Integer employeeId){      //is activated. Then the deletion has failed. If the employee id can NOT be found, the program will throw an exception,
        try {                                                   // and enter the catch block. BUT, this is what we actually want, because this shows that the deletion has succeeded.
            getEmployeeById(employeeId);
            Assert.fail("employee id: " + employeeId + "not deleted");
        }
        catch (Exception e){
            System.out.println("Ciccis exception thrown is called: "+e);
           exceptionThatWasThrown = e;                                         //The assert.Equals did not work to begin with, because Exception e was not handled. Adding this line
            Assert.assertEquals("404 : [Entity with id 55 not found]",e.getMessage());  //addressed that problem.
        }
    }*/


    // For comparison, not to activate: @Then("the employee error message is {int} : [Entity with id {int} not found]")
    @And("the employee error message is {int} : [Entity with id {int} not found]")  // This @And-block does the same as the above @Then blocks catch block. Why use double???
    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
