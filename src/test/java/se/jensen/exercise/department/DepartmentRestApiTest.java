package se.jensen.exercise.department;


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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;
import se.jensen.test.category.ManualTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(ManualTest.class)
public class DepartmentRestApiTest {
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
    public static void shutDown() { SpringApplication.exit(applicationContext); }

    @Test
    public void getAllDepartments() {
        Optional<List<DepartmentModel>> departments = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(departments.isPresent());
        Assert.assertEquals(3, departments.get().stream().count());
    }

    @Test
    public void getDepartmentById() {
        //TODO
        DepartmentRestServiceClient.getDepartmentById(1);
    }

    @Test
    public void createDepartment() {
        //TODO
    }

    @Test
    public void updateDepartment() {
        //TODO
    }

    @Test
    public void testErrorHandling() {
        try {
            //Optional<DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(10);
            DepartmentRestServiceClient.getDepartmentById(10);
            fail("Expected Exception Not Thrown");
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }
    }
}
