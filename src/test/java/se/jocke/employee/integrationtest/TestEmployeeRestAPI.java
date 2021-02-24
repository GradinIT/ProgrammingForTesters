package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> optionalEmpModels = null; // Bytt till ett tydligare namn
    Optional<EmployeeModel> optionalEmpModel = null;

    @When("") // "^the client calls /department$"
    public void getAll() throws Throwable {
        optionalEmpModels = getAllEmployeees(1); // Varför ange ETT id för att hämta ALLA?
    }
    @Then("") // "^the client receives (\\d+) departments$"
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, optionalEmpModels.get().size());
    }

    @When("") //"^the client updates name for department to (.+)$"
    public void updateFirstName(String firstName) throws Throwable { // När petar jag in firstName?
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName("firstName1").build()); // fler attribut?
    }
    @Then("") // "the name is updated to (.+)$"
    public void firstNameIsUpdated(String firstName) throws Throwable {
        Optional<EmployeeModel> optionalEmpModel = getEmployeeById(1); // Varför skapa en ny när den finns som klassvariabel?
        Assert.assertEquals(firstName, optionalEmpModel.get().getFirstName());
    }

    @When("") // "^the client gets department (\\d+)$"
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        optionalEmpModel = getEmployeeById(employeeId);
    }
    @Then("") // "^the name is$"
    public void firstNameIs() throws Throwable {
        Assert.assertEquals("TestNamn", optionalEmpModel.get().getFirstName());
    }

    @Given("") // "^the departments$"
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmpModels = makeEmployeeList(employees.asList());
        listOfEmpModels.stream().forEach(empModel -> createEmployee(empModel));
    }
    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size()-1; i += 2) {
            emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).firstName(given.get(i+1)).build()); // fler attribut?
        }
        return emps;
    }

    @When("") // "^the client deletes department (\\d+)$"
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get()); // Varför ropar den på sig själv?
    }
    @Then("") // "^the department (\\d+) is deleted$"
    public void employeeIsDeleted(Integer employeeId) {
        Throwable exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
        assertEquals("404 : [Entity with id 55 not found]", exceptionThatWasThrown.getMessage()); // Varifrån kommer 55?
    }
}
