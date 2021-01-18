package se.jensen.cucumber.department;

import com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CucumberContextConfiguration
@SpringBootTest(classes = RestServiceApplication.class)
public class TestClient {
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public static Optional<List<DepartmentModel>> getAllDepartments() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/department/",
                HttpMethod.GET,
                null,
                List.class);
        List list = (List) responseEntity.getBody();
        List<DepartmentModel> departmentModels = new ArrayList<>();
        list.stream().forEach(o -> {
            departmentModels.add(gson.fromJson(o.toString(), DepartmentModel.class));
        });
        return Optional.ofNullable(departmentModels);

    }

    public static Optional<DepartmentModel> getDepartmentById(Integer departmentId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/department/" + departmentId,
                HttpMethod.GET,
                null,
                DepartmentModel.class);
        return Optional.ofNullable(DepartmentModel.class.cast(responseEntity.getBody()));

    }

    public static Optional<DepartmentModel> updateDepartment(DepartmentModel departmentModel) {
        final String uri = "http://localhost:8080/department";
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<DepartmentModel> requestEntity = new RequestEntity<DepartmentModel>(departmentModel, HttpMethod.PUT, null);
        ResponseEntity<DepartmentModel> response = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, DepartmentModel.class);
        return Optional.of(response.getBody());
    }

    public static Optional<DepartmentModel> createDepartment(DepartmentModel departmentModel) {
        final String uri = "http://localhost:8080/department";
        RestTemplate restTemplate = new RestTemplate();
        DepartmentModel result = restTemplate.postForObject(uri, departmentModel, DepartmentModel.class);
        return Optional.of(result);
    }

    public static Optional<DepartmentModel> deleteDepartment(DepartmentModel departmentModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<DepartmentModel> requestEntity = new RequestEntity<DepartmentModel>(departmentModel, HttpMethod.DELETE,
                null);
        DepartmentModel result = restTemplate.exchange("http://localhost:8080/department/", HttpMethod.DELETE, requestEntity, DepartmentModel.class).getBody();
        return Optional.of(result);

    }
}
