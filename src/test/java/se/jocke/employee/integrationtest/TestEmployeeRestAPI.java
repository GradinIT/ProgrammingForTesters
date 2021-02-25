package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient {
    
    Optional<List<EmployeeModel>> optionalEmployeeModelLists = null; // Bytt till ett tydligare namn
    Optional<EmployeeModel> optionalEmpModel = null;

    @When("") // "^the client calls /department$"
    public void getAll() throws Throwable {
        optionalEmployeeModelLists = getAllEmployees(1); // Varför ange ETT id för att hämta ALLA?
    }
    @Then("") // "^the client receives (\\d+) departments$"
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assertions.assertEquals(numberOfEmployees, optionalEmployeeModelLists.get().size());
    }

}
