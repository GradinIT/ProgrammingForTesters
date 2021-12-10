package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.internal.matchers.Null;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends EmployeeTestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAll() {

        employees = getAllEmployees();
    }

    //@Then("the client receives {int} employees")
    @Then("^the client receives (\\d+) employees$")
    public void the_client_receives_employees(int numberOfEmployees) {
        Assert.assertEquals(numberOfEmployees, getAllEmployees().get().size());
    }

    @When("^the client gets (\\d+) employee $")
    public void the_client_gets_employee(Integer employeeId) {
        employee = getEmployeeById(employeeId);
    }

    @Then("^the firstname is (.+)$")
    public void firstname_of_employee1(String employeeName) {
        Assert.assertEquals(employeeName, employee.get().getFirstName());
    }

    @And("^the lastname is (.+)$")
    public void lastname_of_employee1(String employeeName) {
        Assert.assertEquals(employeeName, employee.get().getLastName());
    }

    @And("^the employee salary is (.+)$")
    public void salary_of_employee1(BigDecimal employeeSalary) {
        Assert.assertEquals(employeeSalary, employee.get().getSalary());
    }

    @And("^the employee fulltime status is (.+)$")
    public void fulltime_status_of_employee1(boolean employeeFullTime) {
        Assert.assertEquals(employeeFullTime, employee.get().getFullTime());
    }
    @And("^the employee departmentId is (.+)$")
    public void departmentId_of_employee1(Integer employeeDepartmentId){
        Assert.assertEquals(employeeDepartmentId, employee.get().getEmployeeId());
    }

    @When("^the client updates firstname for employee to (.+)$")
    public void the_client_updates_firstname_for_employee_to_Klas() {
    }

    @Then("^the firstname is updated to (.+)$")
    public void the_firstname_is_updated_to_Klas() {
    }

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
}
