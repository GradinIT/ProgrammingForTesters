package se.jensen.exercise.department;

import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;

import java.util.Collections;

public class DepartmentRestApiTestsSetUpp {

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
}
