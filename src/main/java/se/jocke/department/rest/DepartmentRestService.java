package se.jocke.department.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.jocke.department.unittest.api.EmployeeModel;
import se.jocke.department.unittest.api.mapper.DepartmentModelMapper;
import se.jocke.department.unittest.api.mapper.DepartmentModelsMapper;
import se.jocke.department.unittest.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentRestService {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<EmployeeModel>> getDepartments() {
        List<EmployeeModel> departmentModels = DepartmentModelsMapper.map(departmentService.getDepartments());
        return new ResponseEntity<List<EmployeeModel>>(departmentModels, HttpStatus.OK);
    }
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<EmployeeModel> getDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        return new ResponseEntity<EmployeeModel>(DepartmentModelMapper.map(departmentService.getDepartmentById(departmentId)),HttpStatus.OK);
    }
    @PostMapping("/department")
    public ResponseEntity<EmployeeModel> create(@RequestBody EmployeeModel departmentModel){
        EmployeeModel response = DepartmentModelMapper.map(departmentService.create(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
    @PutMapping("/department")
    public ResponseEntity<EmployeeModel> update(@RequestBody EmployeeModel departmentModel){
        EmployeeModel response = DepartmentModelMapper.map(departmentService.update(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
    @DeleteMapping("/department")
    public ResponseEntity<EmployeeModel> delete(@RequestBody EmployeeModel departmentModel){
        EmployeeModel response = DepartmentModelMapper.map(departmentService.remove(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<EmployeeModel>(response,HttpStatus.OK);
    }
}
