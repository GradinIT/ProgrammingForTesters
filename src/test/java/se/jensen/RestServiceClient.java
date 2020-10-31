package se.jensen;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestServiceClient {
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<EmployeeModel> updateEmployee(EmployeeModel employeeModel) {

    }

}
