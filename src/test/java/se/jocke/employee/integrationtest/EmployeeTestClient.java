package se.jocke.employee.integrationtest;

import com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jocke.config.PersistenceConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.RestServiceApplication;
import se.jocke.employee.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CucumberContextConfiguration
@SpringBootTest(classes = {RestServiceApplication.class, LiquibaseConfigurer.class, PersistenceConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EmployeeTestClient {
    private static final String BASE_URL = "http://localhost:8082/";
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public static Optional<EmployeeModel> deleteEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.DELETE,
                null);
        EmployeeModel result = restTemplate.exchange(BASE_URL+"employee/", HttpMethod.DELETE, requestEntity, EmployeeModel.class).getBody();
        return Optional.of(result);
    }
    public static Optional<EmployeeModel> createEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        EmployeeModel result = restTemplate.postForObject(BASE_URL+"employee/", employeeModel, EmployeeModel.class);
        return Optional.of(result);
    }
    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.PUT, null);
        ResponseEntity<EmployeeModel> response = restTemplate.exchange(BASE_URL+"employee/", HttpMethod.PUT, requestEntity, EmployeeModel.class);
        return Optional.of(response.getBody());
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
        ResponseEntity responseEntity = restTemplate.exchange(BASE_URL+"employee/",
                HttpMethod.GET,
                null,
                List.class);
        List list = (List) responseEntity.getBody();
        List<EmployeeModel> departmentModels = new ArrayList<>();
        list.stream().forEach(o -> {
            departmentModels.add(gson.fromJson(o.toString(), EmployeeModel.class));
        });
        return Optional.ofNullable(departmentModels);
    }
}
