package se.jensen.exercise.department;

import lombok.SneakyThrows;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import se.jensen.test.category.IntegrationTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DepartmentRestApiTest implements IntegrationTest {
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
    public void a_testGetAllDepartments() {
        Optional<List<DepartmentModel>> departments = DepartmentRestServiceClient.getAllDepartments();

        Assert.assertTrue(departments.isPresent());
        Assert.assertEquals(3, departments.get().size());
    }

    @Test
    public void b_testGetDepartmentById () {
        Optional<DepartmentModel> departmentModels = DepartmentRestServiceClient.getDepartmentById(1);
        Assert.assertTrue(departmentModels.isPresent());
        Assert.assertEquals(Integer.valueOf(1), departmentModels.get().getDepartmentId());
        Assert.assertEquals("Development", departmentModels.get().getDepartmentName());
    }

    @Test
    public void c_testCreateDepartment () {
        DepartmentModel newDepartment = DepartmentModel.builder()
                .departmentId(50)
                .departmentName("New Department")
                .build();
        DepartmentModel stored = DepartmentRestServiceClient.createDepartment(newDepartment).get();
        Assert.assertNotNull(stored);
        Assert.assertEquals(Integer.valueOf(50), stored.getDepartmentId());
        Assert.assertEquals("New Department", stored.getDepartmentName());
    }

    @Test
    public void d_testUpdateDepartment() {
        DepartmentModel updatedDepartment = DepartmentModel.builder()
                .departmentId(50)
                .departmentName("Updated Department")
                .build();

        DepartmentModel stored = DepartmentRestServiceClient.updateDepartment(updatedDepartment).get();
        Assert.assertNotNull(stored);
        Assert.assertEquals(Integer.valueOf(50), stored.getDepartmentId());
        Assert.assertEquals("Updated Department", stored.getDepartmentName());
    }

    @Test
    public void e_testDeleteDepartment() {
        DepartmentModel updatedDepartment = DepartmentModel.builder()
                .departmentId(50)
                .departmentName("Updated Department")
                .build();
        DepartmentModel stored = DepartmentRestServiceClient.deleteDepartment(updatedDepartment).get();
        Assert.assertNotNull(stored);

        try {
            DepartmentRestServiceClient.getDepartmentById(stored.getDepartmentId());
            fail();
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(404, e.getRawStatusCode());
            Assert.assertEquals("404 : [Entity with id 50 not found]", e.getMessage());
        }

    }

    @Test
    public void f_testErrorHandling() {
        try {
            Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(50);
            fail("Expected Exception Not Thrown");
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND,e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 50 not found]", e.getMessage());
        }
    }

}
