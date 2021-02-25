package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import java.util.List;
import java.util.Optional;


public class TestEmployeeRestAPI extends TestClient{
        Optional<List<EmployeeModel>> emloyees = null;
        Optional<EmployeeModel> employee = null;


        @When("^the client calls /employee$")
        public void getAll() throws Throwable {
            emloyees = getAllEmployees(1);
        }

        @Then("^the client receives (\\d+) employees$")
        public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
            Assert.assertEquals(numberOfEmployees, emloyees.get().size());
        }

        @When("^the client updates name for employee to (.+)$")
        public void updateNameOfEmployee(String employeeName) throws Throwable {
            updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeName).build());
        }

        @Then("the name isn updated to (.+)$")
        public void firstNameOfEmployeeIsUpdated(String employeeName) throws Throwable {
            Optional<EmployeeModel> employee = getEmployeeById(1);
            Assert.assertEquals(employeeName, employee.get().getFirstName());
            Assert.assertEquals(test, employee.get().getLastName());
        }
    }

