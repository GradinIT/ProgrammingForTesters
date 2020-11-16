package se.jensen;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeFakeDao;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

@Configuration
public class BeanConfiguration {
    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    public EmployeeDao employeeDao() {
        EmployeeFakeDao employeeFakeDao = new EmployeeFakeDao(Maps.newHashMap());
        employeeFakeDao.setTestData();
        return employeeFakeDao;
    }
}