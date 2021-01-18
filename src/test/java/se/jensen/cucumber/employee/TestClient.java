package se.jensen.cucumber.employee;

import com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.RestServiceApplication;
import se.jensen.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CucumberContextConfiguration
@SpringBootTest(classes = RestServiceApplication.class)
public class TestClient {
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public static Optional<List<EmployeeModel>> getAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/",
                HttpMethod.GET,
                null,
                List.class);
        List list = (List) responseEntity.getBody();
        List<EmployeeModel> employeeModels = new ArrayList<>();
        list.stream().forEach(o -> {
            employeeModels.add(gson.fromJson(o.toString(), EmployeeModel.class));
        });
        return Optional.ofNullable(employeeModels);

    }

    public static Optional<EmployeeModel> getEmployeeById(Integer employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/" + employeeId,
                HttpMethod.GET,
                null,
                EmployeeModel.class);
        return Optional.ofNullable(EmployeeModel.class.cast(responseEntity.getBody()));
    }

    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {
        final String uri = "http://localhost:8080/employee";
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.PUT, null);
        ResponseEntity<EmployeeModel> response = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, EmployeeModel.class);
        return Optional.of(response.getBody());
    }

    public static Optional<EmployeeModel> createEmployee(EmployeeModel employeeModel) {
        final String uri = "http://localhost:8080/employee";
        RestTemplate restTemplate = new RestTemplate();
        EmployeeModel result = restTemplate.postForObject(uri, employeeModel, EmployeeModel.class);
        return Optional.of(result);
    }

    public static Optional<EmployeeModel> deleteEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.DELETE,
                null);
        employeeModel = restTemplate.exchange("http://localhost:8080/employee/", HttpMethod.DELETE, requestEntity, EmployeeModel.class).getBody();
        return Optional.of(employeeModel);
    }
}
