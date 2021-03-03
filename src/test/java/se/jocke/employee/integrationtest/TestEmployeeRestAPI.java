package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.api.EmployeeModel;
import se.jocke.TestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }
    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }
    @When("^the client updates name for department to (.+)$")
    public void updateNameOfEmployee(String employeeName) throws Throwable {
        updateEmployee(EmployeeModel.builder().build().builder().employeeId(1).firstName(employeeName).build());
    }
    @Then("the name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName,employee.get().getFirstName());
        Assert.assertEquals(employeeName,employee.get().getLastName());
    }
    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }
    @Then("^the name is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("Coding",employee.get().getFirstName());
        Assert.assertEquals("Coding",employee.get().getLastName());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployee = makeEmployeeList(employees.asList());
        listOfEmployee.stream().forEach(employee -> createEmployee(employee));
    }
    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employeeModelList = new ArrayList<>();

        for(int i = 0 ; i < given.size() - 1 ; i +=2) {
            employeeModelList.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i+1))
                    .lastName(given.get(i+2))
                    .salary(BigDecimal.valueOf(i+3))
                    .fullTime(Boolean.valueOf(given.get(i+4)))
                    .departmentId(i+5)
                    .build());

        }
        return employeeModelList;
    }
    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId){
        deleteEmployee(EmployeeModel.builder().employeeId(employeeId).firstName("").lastName("").build());
    }
    @Then("^the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId){
        try {
            getEmployeeById(employeeId);
            Assert.fail("employee id: " + employeeId + "not deleted");
        }
        catch (Exception e){
            Assert.assertEquals("404 : [Entity with id 55 not found]",e.getMessage());
        }
    }
}

