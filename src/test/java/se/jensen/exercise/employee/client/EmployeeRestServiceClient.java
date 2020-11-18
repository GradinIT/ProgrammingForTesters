package se.jensen.exercise.employee.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/",
                    HttpMethod.GET,
                    null,
                    List.class);
            List list = (List) responseEntity.getBody();
            List<EmployeeModel> employeeModels = new ArrayList<>();
            list.stream().forEach( o -> {
                employeeModels.add(gson.fromJson(o.toString(),EmployeeModel.class));
            });
            return Optional.ofNullable(employeeModels);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public static Optional<EmployeeModel> getEmployeeById(Integer employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/"+employeeId,
                    HttpMethod.GET,
                    null,
                    EmployeeModel.class);
            return Optional.ofNullable(EmployeeModel.class.cast(responseEntity.getBody()));
        } catch (Exception e) {
            throw e;
        }
    }

    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {
        final String uri = "http://localhost:8080/employee";
        RestTemplate restTemplate = new RestTemplate();
        EmployeeModel result = restTemplate.postForObject( uri, employeeModel, EmployeeModel.class);
        return Optional.of(result);
    }

    public static Optional<EmployeeModel> createEmployee(EmployeeModel employeeModel) {
        final String uri = "http://localhost:8080/employee";
        RestTemplate restTemplate = new RestTemplate();
        EmployeeModel result = restTemplate.postForObject( uri, employeeModel, EmployeeModel.class);
        return Optional.of(result);
    }
    public static Optional<EmployeeModel> deleteEmployee(EmployeeModel employeeModel) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            RequestEntity<EmployeeModel> requestEntity  =  new RequestEntity<EmployeeModel>(employeeModel,HttpMethod.DELETE,
                    new URI("http://localhost:8080/employee/"));
            employeeModel = restTemplate.exchange(requestEntity,EmployeeModel.class).getBody();
            return Optional.of(employeeModel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
