package se.jensen.exercise.employee;

import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.api.EmployeeModel;
import se.jensen.exercise.employee.client.EmployeeRestServiceClient;
import se.jensen.test.category.IntegrationTest;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(IntegrationTest.class)
public class EmployeeRestGetByIDIT {
    private static ConfigurableApplicationContext applicationContext;

    @SneakyThrows
    @BeforeClass
    public static void startUp() {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);
    }

    @AfterClass
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
    }

    @Test
    public void testHappyFlow() {
        Optional<EmployeeModel> employeeModel = EmployeeRestServiceClient.getEmployeeById(1);
        Assert.assertTrue(employeeModel.isPresent());
    }
    @Test
    public void testErrorMessage() {
        try {
            Optional<EmployeeModel> employeeModel = EmployeeRestServiceClient.getEmployeeById(90);
        }
        catch(HttpClientErrorException exception) {
            Assert.assertEquals(404,exception.getRawStatusCode());
            Assert.assertEquals("404 : [Entity with id 90 not found]",exception.getMessage());
        }
    }
}
