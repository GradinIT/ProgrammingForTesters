package se.jocke.employee.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jocke.employee.unittests.api.EmployeeModel;
import se.jocke.employee.unittests.api.mapper.EmployeeModelMapper;
import se.jocke.employee.unittests.api.mapper.EmployeeModelsMapper;
import se.jocke.employee.service.EmployeeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class EmployeeRestService {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        EmployeeModel employeeModel = EmployeeModelMapper.map(employeeService.getEmployeeById(employeeId));
        return new ResponseEntity<EmployeeModel>(employeeModel, HttpStatus.OK);
    }
    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeModel>> getEmployees() {
        List<EmployeeModel> employeeModels = EmployeeModelsMapper.map(employeeService.getAllEmployees());
        return new ResponseEntity<List<EmployeeModel>>(employeeModels, HttpStatus.OK);
    }
    @PostMapping("/employee")
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel){
        EmployeeModel response = EmployeeModelMapper.map(employeeService.createEmployee(EmployeeModelMapper.map(employeeModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
    @PutMapping("/employee")
    public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employeeModel){
        EmployeeModel response = EmployeeModelMapper.map(employeeService.updateEmployee(EmployeeModelMapper.map(employeeModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
    @DeleteMapping("/employee")
    public ResponseEntity<EmployeeModel> deleteEmployee(@RequestBody EmployeeModel employeeModel){
        EmployeeModel response = EmployeeModelMapper.map(employeeService.removeEmployee(EmployeeModelMapper.map(employeeModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
}
