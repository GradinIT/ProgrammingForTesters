package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

   // när vi har anropat alla employees, then size is.

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




}
