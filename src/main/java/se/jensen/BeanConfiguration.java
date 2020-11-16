package se.jensen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.jensen.dao.EmployeeDao;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

@Configuration
public class BeanConfiguration {
    @Autowired
    EmployeeDao primaryDataSource;

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(primaryDataSource);
    }
}