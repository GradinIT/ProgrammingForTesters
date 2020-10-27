package se.jensen;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;
import se.jensen.entity.Employee;

public class RestApiManualTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/4",
                    HttpMethod.GET,
                    null,
                    EmployeeModel.class);

            System.out.println((EmployeeModel)responseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
