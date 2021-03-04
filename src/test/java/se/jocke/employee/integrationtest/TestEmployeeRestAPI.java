package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
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

    @Then("^the last name is$")
    public void nameOfEmployeesLastNameIs(){
        Assert.assertEquals("Salmi", employee.get().getLastName());
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

    @Given("^the employee$")
    public void givenDepartments(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }
    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 6) {
            employees.add(se.jocke.api.EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .fullTime(Boolean.valueOf(given.get(i + 3)))
                    .salary(BigDecimal.valueOf(i + 4))
                    .departmentId(i + 5)
                    .build());
        }
        return employees;
    }

    @When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) {
        deleteDepartment(getDepartmentById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
    public void employeeIsDeleted(Integer employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getTheEmployeeById(employeeId);
        });
    }

/*    @When("^the client updates salary for employee to (.+)$")
    public void updateSalaryOfEmployeeTo(BigDecimal salary){
        updateEmployee(EmployeeModel.builder().employeeId(1).salary(salary).build());
    }

    @Then("^the salary is updated to (.+)$")
    public void salaryOfEmployeeIsUpdated(BigDecimal salary){
        Optional<EmployeeModel> employee = getEmployeeById(1);
        Assertions.assertEquals(salary, employee.get().getSalary());
    }*/



}
