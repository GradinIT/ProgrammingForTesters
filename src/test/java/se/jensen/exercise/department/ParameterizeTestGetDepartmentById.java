package se.jensen.exercise.department;

import lombok.SneakyThrows;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RunWith(Parameterized.class)
public class ParameterizeTestGetDepartmentById {

    private Integer departmentID;
    private String expectedDepartmentName;

    //Constructor
    public ParameterizeTestGetDepartmentById(Integer departmentID, String expectedDepartmentName)
    {
        this.departmentID = departmentID;
        this.expectedDepartmentName = expectedDepartmentName;
    }

    //Parameters
    @Parameterized.Parameters(name = "Test with departmentId {index}")

    public static Iterable<Object[]> dataForTest()
    {
        return Arrays.asList(new Object[][]
        {
            {1, "Development"},
            {2, "Sales"},
            {3, "Management"},
        });
    }

//---------------------------------------------------------------------------------
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
    public void testGetDepartmentById() {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(departmentID);
        Assert.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();

        Assert.assertEquals(Integer.valueOf(departmentID), departmentModel.getDepartmentId());
        Assert.assertEquals(expectedDepartmentName, departmentModel.getDepartmentName());
    }
}
