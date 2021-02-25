package se.jocke.employee.integrationtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {

    Optional<List<EmployeeModel>> employeeModelOptional = null;
    Optional<List<Employee>> employeeOptional = null;

    @When("^the client calls /employee$")
    public void getAll() {
        employeeModelOptional = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void theClientGetAllEmployees(Integer numOfEmployees) {
        Assertions.assertEquals(numOfEmployees, employeeModelOptional.get().size());
    }

}
