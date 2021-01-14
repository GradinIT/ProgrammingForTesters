package se.jensen.exercise.department;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SeleniumTestInputForms {

    private static ConfigurableApplicationContext applicationContext;


    String SITE_URL = "https://www.seleniumeasy.com/test/basic-first-form-demo.html";
    public static  WebDriver driver;
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

        WebDriverManager.chromedriver().setup();
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement close = driver.findElement(By.id("at-cv-lightbox-close"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("at-cv-lightbox-close")));
        close.click();
        sleep(1000);

        Assert.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    public void b_isPresent()
    {
       WebElement seleniumEasyPicture =  driver.findElement(By.xpath("//img[@role='presentation']"));
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement sponsoredBy =  driver.findElement(By.xpath("//img[@class='cbt']"));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//img[@class='cbt']"))));

       Assert.assertTrue(seleniumEasyPicture.isDisplayed());
       Assert.assertTrue(sponsoredBy.isDisplayed());
    }

    @Test
    public void c_enterMessage()
    {
        WebElement enterMessage = driver.findElement(By.xpath("//input[@placeholder='Please enter your Message']"));
        enterMessage.sendKeys("abc");
        sleep(1000);

        WebElement showMessage = driver.findElement(By.xpath("//button[@onclick='showInput();']"));
        showMessage.click();
        sleep(1000);

        WebElement yourMessage = driver.findElement(By.id("display"));

        Assert.assertEquals("abc", yourMessage.getText());

    }

    @Test
    public void d_enterValues()
    {
        WebElement enterValueA = driver.findElement(By.id(("sum1")));
        enterValueA.sendKeys("1");
        sleep(1000);

        WebElement enterValueB = driver.findElement(By.id(("sum2")));
        enterValueB.sendKeys("2");
        sleep(1000);

        WebElement getTotal = driver.findElement(By.xpath("//button[@onclick='return total()']"));
        getTotal.click();
        sleep(1000);

        WebElement total = driver.findElement(By.id("displayvalue"));
        Assert.assertEquals("3", total.getText());
    }

}
