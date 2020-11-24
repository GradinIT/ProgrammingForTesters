package se.jensen.exercise.department.client;

import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.DepartmentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRestServiceClient {
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

    public static Optional<DepartmentModel> getDepartmentById(Integer DepartmentId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/department/" + DepartmentId,
                HttpMethod.GET,
                null,
                DepartmentModel.class);
        return Optional.ofNullable(DepartmentModel.class.cast(responseEntity.getBody()));

    }

    public static Optional<DepartmentModel> updateDepartment(DepartmentModel DepartmentModel) {
        final String uri = "http://localhost:8080/department";
        RestTemplate restTemplate = new RestTemplate();
        DepartmentModel result = restTemplate.postForObject(uri, DepartmentModel, DepartmentModel.class);
        return Optional.of(result);
    }

    public static Optional<DepartmentModel> createDepartment(DepartmentModel DepartmentModel) {
        final String uri = "http://localhost:8080/department";
        RestTemplate restTemplate = new RestTemplate();
        DepartmentModel result = restTemplate.postForObject(uri, DepartmentModel, DepartmentModel.class);
        return Optional.of(result);
    }

    public static Optional<DepartmentModel> deleteDepartment(DepartmentModel DepartmentModel) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<DepartmentModel> requestEntity = new RequestEntity<DepartmentModel>(DepartmentModel, HttpMethod.DELETE,
                null);
        DepartmentModel = restTemplate.exchange("http://localhost:8080/department/", HttpMethod.DELETE, requestEntity, DepartmentModel.class).getBody();
        return Optional.of(DepartmentModel);

    }
}
