package se.jensen.exercise.employee;

import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.api.EmployeeModel;
import se.jensen.exercise.employee.client.EmployeeRestServiceClient;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
public class EmployeeRestApiTest {
    private static ConfigurableApplicationContext applicationContext;

    @SneakyThrows
    @BeforeClass
    public static void startUp() {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "18080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);
    }

    @AfterClass
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
    }

    @Test
    public void testGetAllEmployees() {
        Optional<List<EmployeeModel>> employees = EmployeeRestServiceClient.getAllEmployees();
        Assert.assertTrue(employees.isPresent());
        Assert.assertEquals(3, employees.get().stream().count());
    }

    @Test
    public void testGetEmployeeById() {
        Optional<EmployeeModel> employee = EmployeeRestServiceClient.getEmployeeById(1);
        Assert.assertTrue(employee.isPresent());
        EmployeeModel employeeModel = employee.get();
        Assert.assertEquals("firstName1", employeeModel.getFirstName());
        Assert.assertEquals("lastName1", employeeModel.getLastName());
        Assert.assertEquals(BigDecimal.valueOf(25000.00).intValue(), employeeModel.getSalary().intValue());
        Assert.assertEquals(Integer.valueOf(1), employeeModel.getDepartmentId());
        Assert.assertEquals(true, employeeModel.getFullTime());
    }

    @Test
    public void testErrorHandling() {
        try {
            Optional<EmployeeModel> employee = EmployeeRestServiceClient.getEmployeeById(10);
            fail("Expected Exception Not Thrown");
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }
    }
}