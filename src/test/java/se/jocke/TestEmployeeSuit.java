package se.jocke;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * Created by Oscar Bergström 01-03-2021
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({
        "se.jocke.employee"
})
public class TestEmployeeSuit {
}

