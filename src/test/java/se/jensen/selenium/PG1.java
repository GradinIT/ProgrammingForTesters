package se.jensen.selenium;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import se.jensen.api.EmployeeModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmployeeSeleniumTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String driverPath = EmployeeSeleniumTest.class.getClassLoader().getResource("chromedriver").toURI().getPath();
        System.out.println(driverPath);
        switch (System.getProperty("os.name")) {
            //case "windows":
            default:
                System.setProperty("webdriver.chrome.driver", driverPath);
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(220, 10));
        driver.manage().window().setSize(new Dimension(1000, 650));
    }

    @Test
    public void testSimple() throws Exception {
        this.driver.get("http://localhost:8080/employee");
        String text = driver.findElement(By.cssSelector("pre")).getText();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List employees = gson.fromJson(text, List.class);
        Assert.assertEquals(3, employees.size());
    }
    @Test
    public void testGetById() throws Exception {
        this.driver.get("http://localhost:8080/employee/1");
        String text = driver.findElement(By.cssSelector("pre")).getText();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeModel employee = gson.fromJson(text, EmployeeModel.class);
        Assert.assertNotNull(employee);
        Assert.assertEquals("firstName1",employee.getFirstName());
    }
    @After
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}