package se.jensen;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.api.EmployeeModel;

public class RestApiManualTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/employee/3", HttpMethod.GET, null, EmployeeModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
