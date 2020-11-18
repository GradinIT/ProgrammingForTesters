package se.jensen.web.service;

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
import se.jensen.api.DepartmentModel;
import se.jensen.api.EmployeeModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.api.mapper.EmployeeModelMapper;
import se.jensen.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentRestService {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentModel>> getDepartments() {
        List<DepartmentModel> departmentModels = DepartmentModelsMapper.map(departmentService.getDepartments());
        return new ResponseEntity<List<DepartmentModel>>(departmentModels, HttpStatus.OK);
    }
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<DepartmentModel> getDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        return new ResponseEntity<DepartmentModel>(DepartmentModelMapper.map(departmentService.getDepartmentById(departmentId)),HttpStatus.OK);
    }
    @PostMapping("/department")
    public ResponseEntity<DepartmentModel> create(@RequestBody DepartmentModel departmentModel){
        DepartmentModel response = DepartmentModelMapper.map(departmentService.create(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<DepartmentModel>(response,HttpStatus.OK);
    }
    @PutMapping("/department")
    public ResponseEntity<DepartmentModel> updateEmployee(@RequestBody DepartmentModel departmentModel){
        DepartmentModel response = DepartmentModelMapper.map(departmentService.update(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<DepartmentModel>(response,HttpStatus.OK);
    }
    @DeleteMapping("/department")
    public ResponseEntity<DepartmentModel> deleteEmployee(@RequestBody DepartmentModel departmentModel){
        DepartmentModel response = DepartmentModelMapper.map(departmentService.remove(DepartmentModelMapper.map(departmentModel)));
        return new ResponseEntity<DepartmentModel>(response,HttpStatus.OK);
    }
}
