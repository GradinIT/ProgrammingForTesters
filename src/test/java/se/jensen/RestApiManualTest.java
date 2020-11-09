package se.jensen;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.jensen.eazy.inheritence.Car;

import java.util.List;

public class RestApiManualTest {
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.getForEntity("http://localhost:8080/car/TORE", Car.class);
        System.out.println(response.getBody().getId());


        ResponseEntity<List> listResponse = restTemplate.exchange("http://localhost:8080/car", HttpMethod.GET,null,List.class);
        System.out.println(response.getBody());
    }
}
