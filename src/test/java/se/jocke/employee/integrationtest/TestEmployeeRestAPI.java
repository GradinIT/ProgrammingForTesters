package se.jocke.employee.integrationtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<EmployeeModel> optionalEmployeeModel = null;
    Optional<List<EmployeeModel>> optionalEmployeeModelList = null;

    @When("the client calls /employee")
    public void getAll() {
        optionalEmployeeModelList = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees,optionalEmployeeModelList.get().size());
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
    public void theNameIsUpdated(int employeeId, String firstName)  {
        optionalEmployeeModelList = getAllEmployees();
        EmployeeModel updatedEmployee = theClientUpdatesFirstName(employeeId,firstName);
        Assert.assertEquals(updatedEmployee, getEmployeeById(employeeId).get());

    }

    @And("^number of employees is checked$")
    public void checkTheListSizeOfEmployees () {
        Assert.assertEquals(3, optionalEmployeeModelList.get().size());
    }
}
