package se.jensen;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.jensen.dao.EmployeeDao;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

}