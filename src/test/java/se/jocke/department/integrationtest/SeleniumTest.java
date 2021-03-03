package se.jocke.department.integrationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import se.jocke.common.OSType;

@SpringBootApplication
public class SeleniumTest {
    public static void main(String[] args) {
        WebDriver webdriver;
        try {
            System.setProperty("webdriver.chrome.driver", getChromeWebDriver().getFile().getPath());
            ChromeOptions capabilities = new ChromeOptions();
            webdriver = new ChromeDriver(capabilities);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&");
            SpringApplication.run(SeleniumTest.class, args);
            webdriver.navigate().to("http://www.melpa-jocke.com");
            webdriver.findElement(By.id("DrpDwnMn03linkElement")).click();
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%");
        } catch (Exception e) {
            System.out.println("Not able to load Chrome web browser " + e.getMessage());
        }

    }

    public static Resource getChromeWebDriver() {
        OSType osName = OSType.getOperatingSystemType();
        if (osName == OSType.Windows)
            return new ClassPathResource("chromedriver.exe", SeleniumTest.class.getClass().getClassLoader());
        return new ClassPathResource("chromedriver", SeleniumTest.class.getClass().getClassLoader());
    }
}