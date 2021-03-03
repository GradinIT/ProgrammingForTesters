package se.jocke.employee.integrationtest;

import io.cucumber.java.en.When;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;

import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends TestClient {
    Optional<List<EmployeeModel>> employeeListOptional = null;
    Optional<EmployeeModel> employee = null;

    /* MALL
    public class TestDepartmentRestAPI extends TestClient {
    Optional<List<DepartmentModel>> departments = null;
    Optional<DepartmentModel> department = null;
*/


}
