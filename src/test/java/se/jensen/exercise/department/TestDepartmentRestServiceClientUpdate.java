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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.test.category.IntegrationTest;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Collections;
import java.util.Optional;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(IntegrationTest.class)

public class TestDepartmentRestServiceClientUpdate {
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

    @Test public void TestUpdate(){
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentName("update")
                .departmentId(1)
                .build();
        Optional<DepartmentModel> updatemodel = DepartmentRestServiceClient.updateDepartment(departmentModel);

        Assert.assertTrue(updatemodel.isPresent());
        Assert.assertEquals(departmentModel.getDepartmentId(),updatemodel.get().getDepartmentId());
        Assert.assertEquals(departmentModel.getDepartmentName(),updatemodel.get().getDepartmentName());

    }
}
