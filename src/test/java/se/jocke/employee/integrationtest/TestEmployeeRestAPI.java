package se.jocke.employee.integrationtest;



import io.cucumber.java.en.When;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;



    @When("^the client calls /employee$")
    public void getAll() throws Throwable{

    }






}
