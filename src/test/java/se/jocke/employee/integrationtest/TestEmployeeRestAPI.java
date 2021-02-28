package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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
    Optional<EmployeeModel> optionalEmployeeModel = null;
    Optional<List<EmployeeModel>> optionalEmployeeModelList = null;

    @When("the client calls /employee")
    public void getAll() {
        optionalEmployeeModelList = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, optionalEmployeeModelList.get().size());
    }

    @When("the client updates first name of employee (\\d+) to (.+)$")
    public EmployeeModel theClientUpdatesFirstName(int employeeId, String firstName) {
        optionalEmployeeModel = getEmployeeById(employeeId);
        EmployeeModel updatedEmployeeModel = null;

        if (optionalEmployeeModel.isPresent()) {
            updatedEmployeeModel = EmployeeModel.builder()
                    .employeeId(employeeId)
                    .firstName(firstName)
                    .lastName(optionalEmployeeModel.get().getLastName())
                    .salary(optionalEmployeeModel.get().getSalary())
                    .fullTime(optionalEmployeeModel.get().getFullTime())
                    .departmentId(optionalEmployeeModel.get().getDepartmentId())
                    .build();
            updateEmployee(updatedEmployeeModel);
        }
        return updatedEmployeeModel;
    }

    @Then("^the name of employee (\\d+) is updated to (.+)$")
    public void theNameIsUpdated(int employeeId, String firstName) {
        optionalEmployeeModelList = getAllEmployees();
        EmployeeModel updatedEmployee = theClientUpdatesFirstName(employeeId, firstName);
        Assert.assertEquals(updatedEmployee, getEmployeeById(employeeId).get());

    }

    @And("^number of employees is checked$")
    public void checkTheListSizeOfEmployees() {
        Assert.assertEquals(3, optionalEmployeeModelList.get().size());
    }

    private List<EmployeeModel> makeEmployeesList(List<String> given) {
        List<se.jocke.api.EmployeeModel> employees = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
            employees.add(se.jocke.api.EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 1))
                    .salary(BigDecimal.valueOf(25000))
                    .fullTime(false)
                    .departmentId(2)
                    .build());
        }
        return employees;
    }

    @Given("the employee")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeesList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    @When("the client deletes employee {int}")
    public void theClientDeletesEmployee(int employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("employee {int} is deleted")
    public void departmentIsDeleted(int employeeId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(employeeId);
        });
    }
}
