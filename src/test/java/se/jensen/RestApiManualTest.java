package se.jensen;

import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.api.EmployeeModel;
import se.jensen.dao.EntityNotFoundException;

import javax.ws.rs.core.Application;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
public class RestApiManualTest {
    private static ConfigurableApplicationContext applicationContext;
    @SneakyThrows
    @BeforeClass
    public static void startUp() {
        String[] args = {};
        applicationContext = SpringApplication.run(RestServiceApplication.class, args);
    }
    @AfterClass
    public static void shutDown(){
        SpringApplication.exit(applicationContext);
    }
    @Test
    public void testGetAllEmployees() {
        List<EmployeeModel> allEmployees = RestServiceClient.getAllEmployees().get();
        Assert.assertNotNull(allEmployees);
        Assert.assertEquals(4, allEmployees.size());
    }
    @Test
    public void testGetEmployeesById() {
        List<EmployeeModel> allEmployees = RestServiceClient.getAllEmployees().get();
        Assert.assertNotNull(allEmployees);
        for (EmployeeModel employeeModel : allEmployees) {
            EmployeeModel employeeModel1 = RestServiceClient.getEmployeeById(employeeModel.getEmployeeId()).get();
            Assert.assertNotNull(employeeModel1);
            Assert.assertEquals(employeeModel.getEmployeeId(), employeeModel1.getEmployeeId());
            Assert.assertEquals(employeeModel.getFirstName(), employeeModel1.getFirstName());
            Assert.assertEquals(employeeModel.getLastName(), employeeModel1.getLastName());
            Assert.assertEquals(employeeModel.getFullTime(), employeeModel1.getFullTime());
        }
    }
    @Test
    public void testCreateEmployee() {
        EmployeeModel newEmployee = EmployeeModel.builder()
                .setEmployeeId(10)
                .setFirstName("Number-10")
                .setLastName("Number-10")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(Boolean.TRUE)
                .build();

        EmployeeModel stored = RestServiceClient.createEmployee(newEmployee).get();
        Assert.assertNotNull(stored);
    }
    @Test
    public void testDeleteEmployee() {
        EmployeeModel newEmployee = EmployeeModel.builder()
                .setEmployeeId(10)
                .setFirstName("Number-10")
                .setLastName("Number-10")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(Boolean.TRUE)
                .build();

        EmployeeModel stored = RestServiceClient.deleteEmployee(newEmployee).get();
        Assert.assertNotNull(stored);
        try {
            RestServiceClient.getEmployeeById(stored.getEmployeeId());
            fail();
        }
        catch ( HttpClientErrorException e) {
            Assert.assertEquals(404,e.getRawStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]",e.getMessage());
        }
    }
}
