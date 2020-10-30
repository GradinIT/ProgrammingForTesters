package se.jensen;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.List;

public class RestApiManualTest {
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/",
                    HttpMethod.GET,
                    null,
                    List.class);

            List employeeModels =  (List)responseEntity.getBody();
            System.out.println(employeeModels);

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
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        EmployeeModel employeeModel =
                restTemplate.postForObject("http://localhost:8080/employee", request, EmployeeModel.class);
        System.out.println(employeeModel);
    }
}
