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
import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void a_testGetAllDepartments() {
        Optional<List<DepartmentModel>> departments = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(departments.isPresent());
        Assert.assertEquals(3, departments.get().stream().count());
    }

    @Test
    public void b_testGetDepartmentById() {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(1);
        Assert.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();
        Assert.assertEquals(Integer.valueOf(1), departmentModel.getDepartmentId());
        //Assert.assertEquals(String, departmentModel.getDepartmentName());

    }



    @Test
    public void c_testCreateDepartment() {
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(4)
                .departmentName("Reporting")
                .build();
        Optional<DepartmentModel> department = DepartmentRestServiceClient.createDepartment(departmentModel);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.isPresent());
        DepartmentModel departmentCreate = department.get();
        Assert.assertEquals("Reporting", departmentModel.getDepartmentName());
    }

    @Test
    public void d_testUpdateDepartment() {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.updateDepartment(DepartmentModel.builder()
                .departmentId(3)
                .departmentName("Sales")
                .build());
        Assert.assertNotNull(department);
       // Assert.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();
        Assert.assertEquals("Sales", departmentModel.getDepartmentName());
        System.out.println(departmentModel.getDepartmentName());
    }

    @Test
    public void e_testDeleteDepartment() {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.deleteDepartment(DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Development")
                .build());
        Assert.assertNotNull(department);
        Assert.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();
        Assert.assertEquals(Integer.valueOf(1), departmentModel.getDepartmentId());

    }
    @Test
    public void testErrorHandling() {
        try {
            Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(10);
            fail("Expected Exception Not Thrown");
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }
    }
}
