package se.jensen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.EmployeeDao;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

@Configuration
public class BeanConfiguration {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }
    @Bean
    public DepartmentService departmentService() {
        return new DepartmentServiceImpl();
    }
}