package se.jocke.employee.integrationtest;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import se.jocke.employee.api.EmployeeModel;

import java.util.List;
import java.util.Optional;


public class TestEmployeeRestAPI extends EmployeeTestClient {
    private Optional<List<EmployeeModel>> employees = null;

    @When("^the client calls /employee$")
    public void getAll() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void theClientGotAllEmployees(int numberOfEmployees) throws Throwable {
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

    public void updateNameOfEmployee(String employeeName) throws Throwable {
    }

    public void nameOfEmployeeIsUpdated(String employeeName) throws Throwable {
    }

    public void getTheEmployeeId(Integer employeeId) throws Throwable {
    }

    public void nameOfEmployeeIs() throws Throwable {
    }

    public void givenEmployees(DataTable employee) throws Throwable {
    }

//    private List<EmployeeModel> makeEmployeeList(List<String> given) {
//        List<EmployeeModel> emp = new ArrayList<>();
//        for (int i = 0; i < given.size() - 1; i += 2) {
//            emp.add(EmployeeModel.builder()
//                    .employeeId(Integer.parseInt(given.get(i + 1)))
//                    .firstName("" + i)
//                    .lastName("" + i)
//                    .fullTime(true)
//                    .salary(new BigDecimal(25000))
//                    .departmentId(i + 1)
//                    .build());
//        }
//        return emp;
//    }

    public void deleteEmployee(Integer employeeId) throws Throwable {
    }

//    private Throwable exceptionThatWasThrown;

    public void employeeIsDeleted(Integer employeeId) throws Throwable {
    }

    public void checkErrorMessage(Integer errorCode, Integer employeeId) {
    }
}