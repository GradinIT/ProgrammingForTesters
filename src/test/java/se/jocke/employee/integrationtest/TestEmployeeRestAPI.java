package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;


    @When("^the client calls /employee$")
    public void getAll(){
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees){
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    @When("^the client updates first name for employee to (.+)$")
    public void updateFirstNameOfEmployee(String employeeName){
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
    }

    @Then("^the first name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeName){
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }
    @When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId){
        employee = getEmployeeById(employeeId);
    }

    @Then("^the first name is$")
    public void nameOfEmployeesFirstNameIs(){
        Assert.assertEquals("Johanna", employee.get().getFirstName());
    }

    @When("^the client updates last name for employee to (.+)$")
    public void updateNameOfEmployeeTo(String employeeName){
        updateEmployee(EmployeeModel.builder().employeeId(1).lastName(employeeName).build());
    }

    @Then("^the last name is updated to (.+)$")
    public void lastNameOfEmployeeIsUpdated(String employeeName){
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertEquals(employeeName, employee.get().getLastName());
    }

    @When("^the client updates salary for employee to (.+)$")
    public void updateSalaryOfEmployeeTo(BigDecimal salary){
        updateEmployee(EmployeeModel.builder().employeeId(1).salary(salary).build());
    }

    @Then("^the salary is updated to (.+)$")
    public void salaryOfEmployeeIsUpdated(BigDecimal salary){
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertEquals(salary, employee.get().getSalary());
    }

}
