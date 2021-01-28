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
import se.jensen.test.category.IntegrationTest;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(IntegrationTest.class)

public class TestDepartmentRestServiceClient {
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

    @Test public void TestCreate(){
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentName("ekonomi")
                .departmentId(22)
                .build();
        Optional<DepartmentModel> createdmodel = DepartmentRestServiceClient.createDepartment(departmentModel);

        Assert.assertTrue(createdmodel.isPresent());
        Assert.assertEquals(departmentModel.getDepartmentId(),createdmodel.get().getDepartmentId());
        Assert.assertEquals(departmentModel.getDepartmentName(),createdmodel.get().getDepartmentName());

    }
    @Test public void TestUpdate(){
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentName("sales")
                .departmentId(2)
                .build();
        Optional<DepartmentModel> updatemodel = DepartmentRestServiceClient.updateDepartment(departmentModel);

        Assert.assertTrue(updatemodel.isPresent());
        Assert.assertEquals(departmentModel.getDepartmentId(),updatemodel.get().getDepartmentId());
        Assert.assertEquals(departmentModel.getDepartmentName(),updatemodel.get().getDepartmentName());

    }
    @Test public void GetDepartmentId(){
        Optional <DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(2);
        Assert.assertTrue(departmentModel.isPresent());
    }

    @Test public void ErrorHandlingRaw(){
        try {
            Optional <DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(90);
        }
        catch (HttpClientErrorException exception){
            Assert.assertEquals(404,exception.getRawStatusCode());
            Assert.assertEquals("404 : [Entity with id 90 not found]",exception.getMessage());

        }
    }
    @Test public void TestErrorHandling(){
        try {
            Optional <DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(10);
            fail("Expected Exception Not Thrown");
        }
        catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }


    }

    @Test public void GetAllDepartment(){
        Optional<List<DepartmentModel>> departmentModel = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(departmentModel.isPresent());
    }
    @Test public void TestDelete(){

        Optional<DepartmentModel> optionalDepartmentModel = DepartmentRestServiceClient.getDepartmentById(3);

        DepartmentModel departmentModel = optionalDepartmentModel.get();

         DepartmentRestServiceClient.deleteDepartment(departmentModel);

        Optional<List<DepartmentModel>> listdepartmentModel = DepartmentRestServiceClient.getAllDepartments();



        Assert.assertEquals(3,listdepartmentModel.get().size());

    }



}
