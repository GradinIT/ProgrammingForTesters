package se.jensen.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.api.EmployeeModel;
import se.jensen.api.mapper.EmployeeModelMapper;
import se.jensen.service.EmployeeService;


@RestController
public class EmployeeRestService {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        EmployeeModel employeeModel = EmployeeModelMapper.map(employeeService.getEmployeeById(employeeId));
        return new ResponseEntity<EmployeeModel>(employeeModel, HttpStatus.OK);
    }
}
