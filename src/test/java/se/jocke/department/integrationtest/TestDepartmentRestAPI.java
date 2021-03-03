package se.jocke.department.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.DepartmentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDepartmentRestAPI extends TestClient {
    Optional<List<DepartmentModel>> departments = null;
    Optional<DepartmentModel> department = null;


    @When("^D the client calls /department$")
    public void getAll() throws Throwable {
        departments = getAllDepartments();
    }

    @Then("^D the client receives (\\d+) departments$")
    public void theClientGotAllDepartments(int numberOfDepartments) throws Throwable {
        Assert.assertEquals(numberOfDepartments, departments.get().size());
    }

    @When("^D the client updates name for department to (.+)$")
    public void updateNameOfDepartment(String departmentName) throws Throwable {
        updateDepartment(DepartmentModel.builder().departmentId(1).departmentName(departmentName).build());
    }

    @Then("D the name is updated to (.+)$")
    public void nameOfDepartmentIsUpdated(String departmentName) throws Throwable {
        Optional<DepartmentModel> department = getDepartmentById(1);
        Assert.assertEquals(departmentName, department.get().getDepartmentName());
    }

    @When("^D the client gets department (\\d+)$")
    public void getTheDepartmentById(Integer departmentId) throws Throwable {
        department = getDepartmentById(departmentId);
    }

    @Then("^D the name is$")
    public void nameOfDepartmentIs() throws Throwable {
        Assert.assertEquals("Coding", department.get().getDepartmentName());
    }

    @Given("^D the departments$")
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

    @When("D the client deletes department {int}")
    public void deleteDepartment(Integer departmentId) {
        deleteDepartment(getDepartmentById(departmentId).get());
    }

    Throwable exceptionThatWasThrown;

    @Then("D the department {int} is deleted")
    public void departmentIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(departmentId);
        });
    }

    @And("D the error message is {int} : [Entity with id {int} not found]")
    public void checkErrorMessage(Integer errorCode, Integer departmentId) {
        Assertions.assertEquals(errorCode + " : [Entity with id " + departmentId + " not found]", exceptionThatWasThrown.getMessage());
    }

}
