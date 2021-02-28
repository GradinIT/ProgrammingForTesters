package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.junit.Assert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient { // Varför är den grå fast den används i IntegrationTest?
    
    Optional<List<EmployeeModel>> optionalEmployeeModelLists = null;
    Optional<EmployeeModel> optionalEmployeeModel = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        optionalEmployeeModelLists = getAllEmployees();
    }
    @Then("the client receives {int} employees")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assertions.assertEquals(numberOfEmployees, optionalEmployeeModelLists.get().size());
    }

    @When("^the client updates first name of employee (.+) to (\\+d)$")
    public void updateFirstName(int employeeId, String firstName) {
        optionalEmployeeModel = getEmployeeById(employeeId);

        if (optionalEmployeeModel.isPresent()) {
            EmployeeModel empModel = optionalEmployeeModel.get();
            //updateEmployee()
            EmployeeModel newEmpModel = EmployeeModel.builder()
                    .employeeId(empModel.getEmployeeId())
                    .firstName(firstName)
                    .lastName(empModel.getLastName())
                    .salary(empModel.getSalary())
                    .fullTime(empModel.getFullTime())
                    .departmentId(empModel.getDepartmentId())
                    .build();

            updateEmployee(newEmpModel);

        }

        //departments = getAllDepartments();
        //Assert.assertEquals(4, departments.get().size());

    }


}
