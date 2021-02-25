package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.jocke.TestClient.*;

public class TestEmployeeRestAPI {
    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    //Om den innehåller den här infon, ska detta köras
    //mappar emot when annotationen i departmenttest.feature, då vet cucumber motorna att det är den som ska köras

    //CHECK!
    @When("^the client calls /employees$")
    public void getAll() throws Throwable {
        //get kommer från testclient, finns även en koppling från service mappen(tjänsten körs)
        //är en globen, kollar i feature efter annotationer, tar med infon, stämmer det? Ja det stämmer,
        //då är det denna metod som ska köras. Innehåller den samma integer, då blir det rätt eller fel
        employees = getAllEmployees();

    }

    //reguljärt uttryck, läser ut integern i strängen, då kommer fyran in
    //sedan hämtar vi den.
    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }


    //ERROR

    //first name "([^"]*)" and , first name "([^"]*)" and "([^"]*)"$
    @When("now the client updates the last name <Carlsson>")
    public void updateNameOfEmployee(String employeeLastName) throws Throwable {
//      updateEmployee(EmployeeModel.builder().employeeId(1).firstName(employeeFirstName).build());
        updateEmployee(EmployeeModel.builder().employeeId(1).lastName(employeeLastName).build());


    }

    //first name "([^"]*)" and
    @Then("now the name is updated to (.+)$")
    public void nameOfEmployeeIsUpdated(String employeeLastName) throws Throwable {
        Optional<EmployeeModel> employee = getEmployeeById(1);
//        Assert.assertEquals(employeeFirstName, employee.get().getFirstName());
        Assert.assertEquals(employeeLastName, employee.get().getLastName());

    }
    
    //CHECK!
    @When("^now the client gets employees (\\d+)$")
    public void getTheEmployeeById(Integer employeeId) throws Throwable {
        employee = getEmployeeById(employeeId);
    }

    @Then("^now the name of employee is$")
    public void nameOfEmployeeIs() throws Throwable {
        Assert.assertEquals("firstName1", employee.get().getFirstName());

    }

    //Ska denna kasta ett exception??
    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> employee = new ArrayList<>();
        for(int i = 0 ; i < given.size() - 1 ; i +=2) {
            employee.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(1))).firstName(given.get(i+1)).lastName(given.get(i+1)).build());
        }
        return employee;
    }

    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeId) {
        deleteEmployee(getEmployeeById(employeeId).get());
    }

    private void deleteEmployee(EmployeeModel model) {
    }

    @Then("^the employee (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeId){
        Throwable exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getEmployeeById(employeeId);
        });
        assertEquals("404 : [Entity with id 55 not found]",exceptionThatWasThrown.getMessage());
    }
}
