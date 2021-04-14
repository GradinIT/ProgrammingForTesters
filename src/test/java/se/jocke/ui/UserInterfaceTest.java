package se.jocke.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.core.io.ClassPathResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTest {
    @ParameterizedTest
    @ValueSource(strings = {"1","2","3"})
    public void testGetEmployee(String employeeId) {
        System.setProperty("webdriver.chrome.driver", new ClassPathResource("src/test/resources/chromedriver").getPath());
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8082/employee.html");
        Select employeeSelect = new Select(webDriver.findElement(By.id("employeeSelect")));
        employeeSelect.selectByVisibleText(employeeId);
        WebElement element = webDriver.findElement(By.id("name"));

        Assertions.assertAll(
                () -> assertEquals(employeeId, employeeSelect.getAllSelectedOptions().get(0).getText()),
                () -> assertEquals(String.format("firstName%s lastName%s",employeeId,employeeId),element.getAttribute("value"))
        );
        webDriver.quit();
    }
}
