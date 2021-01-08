package se.jensen.exercise.department.client;

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
import se.jensen.api.DepartmentModel;

import se.jensen.test.category.IntegrationTest;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(IntegrationTest.class)

public class TestDepartmentRestGetbyIDIT {
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
        Optional<DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(1);
        Assert.assertTrue(departmentModel.isPresent());
    }

    @Test
    public void testErrorMessage() {
        try {
            Optional<DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(2);
        }
        catch(HttpClientErrorException exception) {
            Assert.assertEquals(404,exception.getRawStatusCode());
            Assert.assertEquals("404 : [Entity with id 90 not found]",exception.getMessage());
        }
    }

}
