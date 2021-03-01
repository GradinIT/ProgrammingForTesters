package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hibernate.type.BigDecimalType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployeeRestAPI extends TestClient{
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

@When("^Client calls for/Employees$")
    public void getAll() throws Throwable {
    employees = getAllEmployees();
}

@Then("^the client receives (\\d+) employees$")
            public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable{
                Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

@When("^the client updated name for employee to (.+)$")
    public void nameOfEmployeeUpdated(String firstName) throws Throwable{
        updateEmployee(EmployeeModel.builder().employeeId(1).firstName(firstName).build());
}

@Then("^the name updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String firstName) throws Throwable{
    Optional<EmployeeModel> employee = getEmployeeById(1);
    Assert.assertEquals(firstName, employee.get().getFirstName());
}

@When("^the client gets employee (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
    employee = getEmployeeById(employeeId);
}

@Then("^the name is$")
    public void nameOfEmployeeIs() throws Throwable {
    Assert.assertEquals("fistName2", employee.get().getFirstName());
}

@Given("^the Employees$")
    public void givenEmployees(DataTable employeesTable) throws Throwable {
    List<EmployeeModel> listOfEmployees = makeEmployeesList(employeesTable.asList());
    listOfEmployees.stream().forEach(employee -> createEmployee(employee));
}

private List<EmployeeModel> makeEmployeesList(List<String> given) {
    List<EmployeeModel> emps = new ArrayList<>();
    for(int i = 0; i < given.size(); i+=2){
        emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i)))
        .firstName(given.get(i + 1))
                .lastName(given.get(i+2))
                .salary(BigDecimal.valueOf(Long.parseLong(given.get(i + 3))))
                .fullTime(Boolean.valueOf(given.get(i + 4)))
                .build());
    }
    return emps;
}

@When("the client deletes employee {int}")
    public void deleteEmployee(Integer employeeId) { deleteEmployee(getEmployeeById(employeeId).get()); }

    Throwable exceptionThatWasThrown;

@Then("the employee {int} was deleted")
    public void employeeIsDeleted(Integer employeeId) {
    exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
        getEmployeeById(employeeId);
    });
}

@And("the error message is {int} : [Entity with id {int} not found!]")
    public void checkErrorMessage(Integer errorCode, Integer employeeId){
    Assertions.assertEquals(errorCode + " : [Entity with id " + employeeId + " not found!]",
            exceptionThatWasThrown.getMessage());
}
}
