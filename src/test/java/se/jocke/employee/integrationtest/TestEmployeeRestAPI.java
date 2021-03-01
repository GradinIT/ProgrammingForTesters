package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient{
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

@When("^Client calls for/Employee$")
    public void getAll() throws Throwable {
    //employees.getAllEmployees();
}
    @Then("^the client receives (\\d+) employees$")
            public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
                Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

@When("^the clinent updated name for employee to (.+)$")
    public void nameOfEmployeeUpdated() throws Throwable{

}
}
