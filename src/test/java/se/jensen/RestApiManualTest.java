package se.jensen;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;

public class RestApiManualTest {
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/4",
                    HttpMethod.GET,
                    null,
                    EmployeeModel.class);

            System.out.println((EmployeeModel) responseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        EmployeeModel model = EmployeeModel.builder()
                .setEmployeeId(15)
                .setFirstName("Gunther")
                .setLastName("Swanson")
                .setSalary(BigDecimal.valueOf(10000.0))
                .setFullTime(true)
                .build();
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        String json = gson.toJson(model);
        System.out.println(json);
        HttpEntity<String> request =
                new HttpEntity<String>(json, headers);

        EmployeeModel employeeModel =
                restTemplate.postForObject("http://localhost:8080/employee", request, EmployeeModel.class);
        System.out.println(employeeModel);
    }
}
