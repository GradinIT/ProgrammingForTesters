package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    @When("^the client calls all employees$")
        public void getAll() {
                employees = getAllEmployees();
            };

    @Then("^the client receives (\\d+) employees$")
        public void receviedAllEmployess(int numberOfEmployees) throws Throwable {
            Assert.assertEquals(numberOfEmployees,employees.get().size());
    }

    @When("^the client creates an employee$")
        public void createEmployee() {
            EmployeeModel empModel = EmployeeModelTestBuilder.builder().build();
            employee = createEmployee(empModel);
    }

    @Then("^the client receives name (.+)$")
    public void receiveName(String name) throws Throwable {
        Assert.assertEquals(name,employee.get().getFirstName());
    }

    @When("^the client calls employee by id (\\d+)$")
        public void receiveEmployee(int employeeId) throws Throwable {
            employee = getEmployeeById(employeeId);
    };

    @Then("^the client receives employee with name (.+)$")
        public void receivedEmployee(String name) throws Throwable {
            Assert.assertEquals(name,employee.get().getFirstName());
    }

    @Given("^the employees$")
        public void givenEmployees(DataTable employees) {
            List<EmployeeModel> modelEmployees = makeEmployeeList(employees.asList());
            modelEmployees.stream().forEach(E -> createEmployee(E));
    }

    @When("^the client deletes employee with ID (\\d+)$")
        public void deleteEmployee(Integer employeeID) {
            deleteEmployee(getEmployeeById(employeeID).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("the employee {int} is deleted")
        public void employeeIsDeleted(Integer employeeId) {
            exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
    }

    @And("the error message is {int} : [Employee with id {int} not found]")
        public void checkErrorMessage(Integer errorCode, Integer employeeId) {
            Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found]", exceptionThatWasThrown.getMessage());
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> empModels = new ArrayList<>();
        for(int i = 0; i < given.size(); i += 6) {
            empModels.add(EmployeeModel.builder()
                    .employeeId(Integer.parseInt(given.get(i)))
                    .firstName(given.get(i + 1))
                    .lastName(given.get(i + 2))
                    .fullTime(Boolean.valueOf(given.get(i)))
                    .salary(new BigDecimal(given.get(i + 4)))
                    .departmentId(Integer.parseInt(given.get(i + 5)))
                    .build());
        }

        return empModels;
    }

    //Ändrar namn genom att sätta samma ID som kastas in och kör en Optional på empmodel,
    //som i sin tur används för att sätta nuvarande värden, men ändrar förnamnet till det -
    //som anges i testfallet, det läser alltså över nuvarande objekt
    @When("^the client updates firstname to (.+) on employee by id (\\d+)$")
        public void updateEmployeeName(String firstname, Integer employeeId) { ;
        Optional<EmployeeModel> empM = getEmployeeById(employeeId);

        updateEmployee(EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName(firstname)
                .lastName(empM.get().getLastName())
                .fullTime(empM.get().getFullTime())
                .salary(empM.get().getSalary())
                .departmentId(empM.get().getDepartmentId())
                .build());
    }

    @Then("^name is changed to (.+) on employee id (\\d+)$")
        public void verifyName(String name, Integer employeeId) {
            Optional<EmployeeModel> empM = getEmployeeById(employeeId);
            Assert.assertEquals(name,empM.get().getFirstName());
    }
}
