package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Assertions;
import org.mockito.Spy;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;
import se.jocke.TestClient;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.util.ObjectUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.collect.Iterators.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

public class TestEmployeeRestAPI extends TestClient {
    @Spy
    Optional<List<EmployeeModel>> employees = null;
    @Spy
    Optional<EmployeeModel> employee = null;

    @When("^the client calls /employee$")
    public void getAllEmps() throws Throwable {
        employees = getAllEmployeees();
    }

    @Then("^the client receives (\\d+) employees$")
    public void checkGetAllEmployees(int numOfEmployees) {
        Assertions.assertEquals(numOfEmployees, employees.get().size());  //Check if number of employees exist
    }


    @When("^the client calls employee (\\d+)$")
    public void getEmpId(Integer employeeNum) throws Throwable {
        employee = getEmployeeById(employeeNum);
    }

    @Then("^the client receives employeeDetails$")
    public void getEmployeeDetails() {

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.get().getEmployeeId()),
                () -> Assertions.assertEquals(1, employee.get().getDepartmentId()),
                () -> Assertions.assertEquals(employee.get().getSalary(), employee.get().getSalary()),
                () -> Assertions.assertEquals("firstName1", employee.get().getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.get().getLastName()),
                () -> Assertions.assertEquals(true, employee.get().getFullTime())
        );
    }

    @When("^the client deletes employee (\\d+)$")
    public void deleteEmployee(Integer employeeID) {

        deleteEmployee(getEmployeeById(employeeID).get());
    }

    @Then("^the client (\\d+) is deleted$")
    public void employeeIsDeleted(Integer employeeID) {
        Throwable exceptionThatWasThrown = assertThrows(HttpClientErrorException.class, () -> getEmployeeById(employeeID));
        assertEquals("404 : [Entity with id 1 not found]", exceptionThatWasThrown.getMessage());
    }

    @Given("^the employees$")
    public void givenEmployees(DataTable employees) {
        List<EmployeeModel> listOfEmployees = makeEmployeeList(employees.asList());
        listOfEmployees.stream().forEach(employee -> createEmployee(employee));
    }

    private List<EmployeeModel> makeEmployeeList(List<String> given) {
        List<EmployeeModel> emps = new ArrayList<>();
        for (int i = 0; i < given.size() - 1; i += 2) {
           /* emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).firstName(given.get(i + 1))
                    .lastName(given.get(i + 2)).salary(BigDecimal.valueOf(Integer.parseInt(given.get(i + 3))))
                    .fullTime(Boolean.valueOf(given.get(i + 4))).departmentId(Integer.parseInt(given.get(i + 5))).build());*/
            emps.add(EmployeeModel.builder().employeeId(Integer.parseInt(given.get(i))).build());
        }
        return emps;
    }


}
