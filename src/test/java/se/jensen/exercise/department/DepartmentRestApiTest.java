package se.jensen.exercise.department;


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
import org.springframework.test.context.junit4.SpringRunner;

import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})

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
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
    }


    @Test
    public void testGetAllDepartments() {
        Optional<List<DepartmentModel>> departments = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(departments.isPresent());
        Assert.assertEquals(3, departments.get().stream().count());
    }

    /*@Test
    public void testGetDepartmentById() {
        Optional <DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(1);

        Assert.assertTrue(department.isPresent());

        DepartmentModel departmentModel = department.get();
        Assert.assertEquals(Integer.valueOf(1),departmentModel.getDepartmentId());
        Assert.assertEquals("Department", departmentModel.getDepartmentName());
    }*/

        @Test
    public void testCreateDepartment() {
        Optional <DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(1);
        Assert.assertTrue(department.isPresent());

        DepartmentModel departmentModel = department.get();
        Assert.assertEquals(Integer.valueOf(1),departmentModel.getDepartmentId());
        Assert.assertEquals("Department", departmentModel.getDepartmentName());
    }

}
