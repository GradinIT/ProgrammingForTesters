package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional <EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAll() {
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void theClientGetAllEmployees(Integer numOfEmployees) {
        Assertions.assertEquals(numOfEmployees, employees.get().size());
    }

    @When("the client request employee id {int}")
    public void employeeById(Integer employeeId) {
        employee = getEmployeeById(employeeId);
    }

    @Then("the client get employee {int}")
    public void employeeNameIs(Integer employeeId) {
        Assertions.assertEquals(employeeId, employee.get().getEmployeeId());
    }

    @When("the client updates name for employee {int} to {}")
    public void updateNameOfEmployee(Integer employeeId, String name) {
        employee = getEmployeeById(employeeId);

        employee = updateEmployee(EmployeeModel.builder()
                .employeeId(employee.get().getEmployeeId())
                .firstName(name)
                .lastName(employee.get().getLastName())
                .fullTime(employee.get().getFullTime())
                .salary(employee.get().getSalary())
                .departmentId(employee.get().getDepartmentId())
                .build()
        );
    }

    @Then("the name of employee is updated to {}")
    public void theNameIsUpdatedTo(String name) {
        Assertions.assertEquals(name, employee.get().getFirstName());
    }

    @Given("^the employees$")
    public void createEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeList(employees.asList());
        listOfEmployees.stream().forEach(TestClient::createEmployee);
    }

    private List<EmployeeModel> makeList(List<String> emp) {
        List<EmployeeModel> employeeModels = new ArrayList<>();

        for (int i = 0; i < emp.size(); i += 6) {
            employeeModels.add(
                    EmployeeModel.builder()
                    .employeeId(Integer.parseInt(emp.get(i)))
                    .firstName(emp.get(i +1))
                    .lastName(emp.get(i + 2))
                    .fullTime(Boolean.valueOf(emp.get(i +3)))
                    .salary(new BigDecimal(emp.get(i + 4)))
                    .departmentId(Integer.parseInt(emp.get(i + 5)))
                    .build()
            );
        }
        return employeeModels;
    }

}
