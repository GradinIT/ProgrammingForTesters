package se.jocke.department.integrationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Locale;

@SpringBootApplication
public class SeleniumTest {

    public enum OSType {
        Windows, MacOS, Linux, Other
    }

    ;

    public static OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            return OSType.MacOS;
        }
        if (OS.indexOf("win") >= 0) {
            return OSType.Windows;
        }
        if (OS.indexOf("nux") >= 0) {
            return OSType.Linux;
        }
        return OSType.Other;
    }

    public static void main(String[] args) {
        WebDriver webdriver;
        try {
            Resource resource = null;
            OSType osName = getOperatingSystemType();

            if (osName == OSType.Windows){
                resource = new ClassPathResource("chromedriver.exe", SeleniumTest.class.getClass().getClassLoader());

            }
            else
                resource = new ClassPathResource("chromedriver", SeleniumTest.class.getClass().getClassLoader());

            System.setProperty("webdriver.chrome.driver", resource.getFile().getPath());
            ChromeOptions capabilities = new ChromeOptions();
           webdriver = new ChromeDriver(capabilities);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&");
            SpringApplication.run(SeleniumTest.class, args);
            webdriver.navigate().to("http://www.google.com");
            webdriver.findElement(By.name("q")).sendKeys("melpa-jockes jam club");
            webdriver.findElement(By.name("btnK")).submit();
            
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%");
        } catch (Exception e) {
            System.out.println("Not able to load Chrome web browser " + e.getMessage());
        }

    }
}