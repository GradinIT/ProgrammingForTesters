package se.jensen.exercise.employee.client;

import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRestServiceClient {
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public static Optional<List<EmployeeModel>> getAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:18080/employee/",
                    HttpMethod.GET,
                    null,
                    List.class);
            List list = (List) responseEntity.getBody();
            List<EmployeeModel> employeeModels = new ArrayList<>();
            list.stream().forEach(o -> {
                employeeModels.add(gson.fromJson(o.toString(), EmployeeModel.class));
            });
            return Optional.ofNullable(employeeModels);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<EmployeeModel> getEmployeeById(Integer employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:18080/employee/" + employeeId,
                    HttpMethod.GET,
                    null,
                    EmployeeModel.class);
            return Optional.ofNullable(EmployeeModel.class.cast(responseEntity.getBody()));
        } catch (Exception e) {
            throw e;
        }
    }

    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {
        try {
            final String uri = "http://localhost:18080/employee";

            RestTemplate restTemplate = new RestTemplate();
            RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.PUT, URI.create(uri));
            ResponseEntity<EmployeeModel> response = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, EmployeeModel.class);
            return Optional.of(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<EmployeeModel> createEmployee(EmployeeModel employeeModel) {
        try {
            final String uri = "http://localhost:18080/employee";
            RestTemplate restTemplate = new RestTemplate();
            EmployeeModel result = restTemplate.postForObject(uri, employeeModel, EmployeeModel.class);
            return Optional.of(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<EmployeeModel> deleteEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            RequestEntity<EmployeeModel> requestEntity = new RequestEntity<EmployeeModel>(employeeModel, HttpMethod.DELETE,
                    new URI("http://localhost:18080/employee/"));
            employeeModel = restTemplate.exchange(requestEntity, EmployeeModel.class).getBody();
            return Optional.of(employeeModel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
