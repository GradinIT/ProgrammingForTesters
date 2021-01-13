package se.jensen.selenium;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.RestServiceApplication;
import se.jensen.api.EmployeeModel;
import se.jensen.test.category.ManualTest;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})
@Category(ManualTest.class)
public class EmployeeSeleniumTest {
    private static WebDriver driver;

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
        String driverPath = "/Users/jocke/chromedriver";
        System.out.println(driverPath);
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(220, 10));
        driver.manage().window().setSize(new Dimension(1000, 650));
    }

    @AfterClass
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
        driver.close();
    }

    @Test
    public void testGetById() throws Exception {
        this.driver.get("http://localhost:8080/employee/1");
        String text = driver.findElement(By.cssSelector("pre")).getText();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeModel employee = gson.fromJson(text, EmployeeModel.class);
        Assert.assertNotNull(employee);
        Assert.assertEquals("firstName1", employee.getFirstName());
    }
}