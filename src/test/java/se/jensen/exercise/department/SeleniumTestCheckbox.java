package se.jensen.exercise.department;


import lombok.SneakyThrows;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SeleniumTestCheckbox {

    private static ConfigurableApplicationContext applicationContext;

    String SITE_URL = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";

    //declare driver
    private static WebDriver driver;

    public WebDriverWait wait;

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @BeforeClass
    public static void setUpp()
    {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);

        //WebDriverManager.chromedriver().setup();

       // set properties with the path to chromedriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        //initialize driver
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        sleep(1000);
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
        SpringApplication.exit(applicationContext);
    }

    @Test
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assert.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        Assert.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    public void b_SingleCheckbox()
    {
        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));
        singleCheckbox.click();
        sleep(1000);

        WebElement successMessage = driver.findElement(By.id("txtAge"));
        Assert.assertTrue(successMessage.isDisplayed());

        sleep(1000);

        Assert.assertTrue(successMessage.getText().contains("Check box is checked"));
    }

    @Test
    public void c_multiplyCheckbox_CheckAllBoxes()
    {
        WebElement checkButton = driver.findElement(By.id("check1"));
        checkButton.click();
        sleep(1000);
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@class='cb1-element']"));

        checkButton = driver.findElement(By.id("check1"));
        sleep(1000);

        Assert.assertTrue(checkButton.getAttribute("value").contains("Uncheck All"));
        Assert.assertTrue(checkBoxes.stream().allMatch(box -> box.isSelected()));
    }

    @Test
    public void d_multiplyCheckbox_UncheckAllBoxes()
    {
        WebElement checkButton = driver.findElement(By.id("check1"));
        checkButton.click();
        sleep(1000);
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@class='cb1-element']"));

        checkButton = driver.findElement(By.id("check1"));
        sleep(1000);

        Assert.assertTrue(checkButton.getAttribute("value").contains("Check All"));
        Assert.assertTrue(checkBoxes.stream().noneMatch(box -> box.isSelected()));
    }


    @Test
    public void e_UncheckTwoBoxes()
    {


        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@class='cb1-element']"));

        checkBoxes.stream().forEach(box -> box.click());
        sleep(1000);

        checkBoxes.stream().limit(2).forEach(box -> box.click());

        sleep(1000);

        WebElement checkButton = driver.findElement(By.id("check1"));
        sleep(1000);

        Assert.assertTrue(checkButton.getAttribute("value").contains("Check All"));
    }

}
