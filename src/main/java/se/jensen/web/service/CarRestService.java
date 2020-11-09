package se.jensen.web.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.eazy.inheritence.Car;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarRestService {

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") String id) {
        return new ResponseEntity<Car>(new Car(200,id),HttpStatus.OK);
    }

    @GetMapping("/car")
    public ResponseEntity<List<Car>> getCarById() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(200,"123456"));
        cars.add(new Car(120,"aaaa"));
        cars.add(new Car(350,"bbbb"));
        cars.add(new Car(55,"cccc"));
        cars.add(new Car(10,"RunarsBil"));
        return new ResponseEntity<List<Car>>(cars,HttpStatus.OK);
    }
}
