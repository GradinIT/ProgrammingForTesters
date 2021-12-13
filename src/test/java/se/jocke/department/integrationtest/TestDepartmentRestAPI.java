package se.jocke.department.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.department.api.DepartmentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDepartmentRestAPI extends DepartmentTestClient {
    Optional<List<DepartmentModel>> departments = null;
    Optional<DepartmentModel> department = null;


    @When("^the client calls /department$")
    public void getAll()  {
        departments = getAllDepartments();
    }

    @Then("^the client receives (\\d+) departments$")
    public void theClientGotAllDepartments(int numberOfDepartments) {
        Assert.assertEquals(numberOfDepartments, departments.get().size());
    }

    @When("^the client updates departmentname for department to (.+)$")
    public void updateNameOfDepartment(String departmentName)  {
        updateDepartment(DepartmentModel.builder().departmentId(1).departmentName(departmentName).build());
    }

    @Then("the departmentname is updated to (.+)$")
    public void nameOfDepartmentIsUpdated(String departmentName)  {
        Optional<DepartmentModel> department = getDepartmentById(1);
        Assert.assertEquals(departmentName, department.get().getDepartmentName());
    }

    @When("^the client gets department (\\d+)$")
    public void getTheDepartmentById(Integer departmentId)  {
        department = getDepartmentById(departmentId);
    }

    @Then("^departmentname is (.+)$")
    public void nameOfDepartmentIs(String departmentName)  {
        Assert.assertEquals(departmentName, department.get().getDepartmentName());
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

    @When("the client deletes department {int}")
    public void deleteDepartment(Integer departmentId) {
        deleteDepartment(getDepartmentById(departmentId).get());
    }

    private Throwable exceptionThatWasThrown;

    @Then("the department {int} is deleted")
    public void departmentIsDeleted(Integer departmentId) {
        exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> {
            getDepartmentById(departmentId);
        });
    }
    @And("the error message is {int} : [\"Entity with id {int} not found\"]")
    public void checkErrorMessage(Integer errorCode, Integer departmentId) {
        Assertions.assertEquals(errorCode + " : [Entity with id "+departmentId+" not found]", exceptionThatWasThrown.getMessage());
    }

}
