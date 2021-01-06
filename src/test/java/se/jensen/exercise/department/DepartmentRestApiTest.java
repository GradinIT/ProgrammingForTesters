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

import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(ManualTest.class)
public class DepartmentRestApiTest {
    private static ConfigurableApplicationContext applicationContext;

    @SneakyThrows
    @BeforeClass
    public static void startUp() {
        String[] args = {};
        SpringApplication springApp = new SpringApplication(RestServiceApplication.class);
        springApp.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        springApp.setBannerMode(Banner.Mode.CONSOLE);
        springApp.setLogStartupInfo(false);
        applicationContext = springApp.run(args);
    }

    @AfterClass
    public static void shutDown() { SpringApplication.exit(applicationContext); }

    /*@Test
    public void testToCreate() {
        DepartmentModel addDepartment = DepartmentModel.builder()
                .departmentId(33)
                .departmentName("The NEW Department")
                .build();
        DepartmentModel newDepartment = DepartmentRestServiceClient.createDepartment(addDepartment).get();
        Assert.assertNotNull(newDepartment);
        Assert.assertEquals(Integer.valueOf(33), newDepartment.getDepartmentId());
        Assert.assertEquals("The NEW Department", newDepartment.getDepartmentName());
        Assert.assertEquals(addDepartment, newDepartment); //can we use this instead of above?
    }*/

    @Test
    public void testToUpdate() {
        //TODO
    }

    @Test
    public void testToDelete() {
        //TODO
    }

    @Test
    public void testToGetAll() {
        Optional<List<DepartmentModel>> departments = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(departments.isPresent());
    }

    @Test
    public void testToGetById() {
        Optional<DepartmentModel> departments = DepartmentRestServiceClient.getDepartmentById(1);
        Assert.assertTrue(departments.isPresent());
    }

    @Test
    public void testErrorHandling() {
        try {
            Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(10);
            fail("Expected exception not thrown.");
        }
        catch (HttpClientErrorException exception) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", exception.getMessage());
        }
    }
}