package se.jocke;

import com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CucumberContextConfiguration
@SpringBootTest(classes = {RestServiceApplication.class, LiquibaseConfigurer.class, H2JpaConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestClient {
    private static final String BASE_URL = "http://localhost:8082/";
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public static Optional<List<DepartmentModel>> getAllDepartments() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.exchange(BASE_URL+"department/",
                HttpMethod.GET,
                null,
                List.class);
        List<?> list = (List<?>) responseEntity.getBody();
        List<DepartmentModel> departmentModels = new ArrayList<>();
        assert list != null;
        list.forEach(o -> {
            departmentModels.add(gson.fromJson(o.toString(), DepartmentModel.class));
        });
        return Optional.of(departmentModels);

    }

    public static Optional<DepartmentModel> getDepartmentById(Integer departmentId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(BASE_URL+"department/" + departmentId,
                HttpMethod.GET,
                null,
                DepartmentModel.class);
        return Optional.ofNullable(DepartmentModel.class.cast(responseEntity.getBody()));

    }
    /*public static Optional<EmployeeModel> getEmployeeById(Integer employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(BASE_URL+"employee/" + employeeId,
                HttpMethod.GET,
                null,
                EmployeeModel.class);
        return Optional.ofNullable(EmployeeModel.class.cast(responseEntity.getBody()));
    }*/

    public static Optional<DepartmentModel> updateDepartment(DepartmentModel departmentModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<DepartmentModel> requestEntity = new RequestEntity<>(departmentModel, HttpMethod.PUT, null);
        ResponseEntity<DepartmentModel> response = restTemplate.exchange(BASE_URL+"department/", HttpMethod.PUT, requestEntity, DepartmentModel.class);
        return Optional.ofNullable(response.getBody());
    }

    public static Optional<DepartmentModel> createDepartment(DepartmentModel departmentModel) {
        RestTemplate restTemplate = new RestTemplate();
        DepartmentModel result = restTemplate.postForObject(BASE_URL+"department/", departmentModel, DepartmentModel.class);
        assert result != null;
        return Optional.of(result);
    }

    public static Optional<DepartmentModel> deleteEmployee(DepartmentModel departmentModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<DepartmentModel> requestEntity = new RequestEntity<>(departmentModel, HttpMethod.DELETE,
                null);
        DepartmentModel result = restTemplate.exchange(BASE_URL+"department/", HttpMethod.DELETE, requestEntity, DepartmentModel.class).getBody();
        assert result != null;
        return Optional.of(result);

    }

    public static Optional<EmployeeModel> deleteEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<>(employeeModel, HttpMethod.DELETE,
                null);
        EmployeeModel result = restTemplate.exchange(BASE_URL+"employee/", HttpMethod.DELETE, requestEntity, EmployeeModel.class).getBody();
        assert result != null;
        return Optional.of(result);
    }
    public static Optional<EmployeeModel> createEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        EmployeeModel result = restTemplate.postForObject(BASE_URL+"employee/", employeeModel, EmployeeModel.class);
        assert result != null;
        return Optional.of(result);
    }
    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<>(employeeModel, HttpMethod.PUT, null);
        ResponseEntity<EmployeeModel> response = restTemplate.exchange(BASE_URL+"employee/", HttpMethod.PUT, requestEntity, EmployeeModel.class);
        return Optional.of(Objects.requireNonNull(response.getBody()));
    }

    public static Optional<EmployeeModel> getEmployeeById(Integer employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(BASE_URL+"employee/" + employeeId,
                HttpMethod.GET,
                null,
                EmployeeModel.class);
        return Optional.ofNullable(EmployeeModel.class.cast(responseEntity.getBody()));
    }

    public static Optional<List<EmployeeModel>> getAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.exchange(BASE_URL+"employee/",
                HttpMethod.GET,
                null,
                List.class);
        List<?> list =  (List<?>) responseEntity.getBody();
        List<EmployeeModel> employeeModels = new ArrayList<>();

        assert list != null;
        list.forEach(o -> employeeModels.add(gson.fromJson(o.toString(), EmployeeModel.class)));

        return Optional.of(employeeModels);
    }
}
