package se.jocke.department.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.api.DepartmentModel; // Den enda mainklassen som körs här
import se.jocke.TestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Många metoder som inte används ännu, kanske senare i
 * IntegrationTest, TestClient eller TestDepartmentSuit?
 *
 * Använder sig på något sätt av departmentTest.feature (i resources), men HUR?
 */

public class TestDepartmentRestAPI extends TestClient {
    Optional<List<DepartmentModel>> departments = null;
    Optional<DepartmentModel> department = null;


    @When("^the client calls /department$")
    // När klienten skriver /department i webläsaren?
    public void getAll() throws Throwable {
        // används just nu ej
        departments = getAllDepartments();
    }
    @Then("^the client receives (\\d+) departments$")
    // Vilket språk/vad kommunicerar med vad? Gherkin
    public void theClientGotAllDepartments(int numberOfDepartments) throws Throwable {
        Assert.assertEquals(numberOfDepartments, departments.get().size());
    }

    @When("^the client updates name for department to (.+)$")
    public void updateNameOfDepartment(String departmentName) throws Throwable {
        updateDepartment(DepartmentModel.builder().departmentId(1).departmentName(departmentName).build());
        // Vart kommer ettan ifrån? Bara testexempel?
    }
    @Then("the name is updated to (.+)$")
    public void nameOfDepartmentIsUpdated(String departmentName) throws Throwable {
        Optional<DepartmentModel> department = getDepartmentById(1);
        // Varför skapa en ny när den finns som klassvariabel?
        Assert.assertEquals(departmentName, department.get().getDepartmentName());
    }

    @When("^the client gets department (\\d+)$")
    public void getTheDepartmentById(Integer departmentId) throws Throwable {
        department = getDepartmentById(departmentId);
    }

    @Then("^the name is$")
    public void nameOfDepartmentIs() throws Throwable {
        Assert.assertEquals("Coding", department.get().getDepartmentName());
    }

    @Given("^the departments$")
    public void givenDepartments(DataTable departments) {
        List<DepartmentModel> listOfDepartments = makeDepartmentList(departments.asList());
        listOfDepartments.stream().forEach(department -> createDepartment(department));
    }

    private List<DepartmentModel> makeDepartmentList(List<String> given) {
        List<DepartmentModel> deps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
            deps.add(DepartmentModel.builder().departmentId(Integer.parseInt(given.get(i))).departmentName(given.get(i + 1)).build());
        }
        return deps;
    }

    @When("^the client deletes department (\\d+)$")
    public void deleteDepartment(Integer departmentId){
        deleteDepartment(getDepartmentById(departmentId).get()); // Varför ropar den på sig själv?

    }

    Throwable exceptionThatWasThrown;

    @Then("the department {int} is deleted")
    public void departmentIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(departmentId);
        });
    }

    @And("the error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer departmentId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + departmentId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
