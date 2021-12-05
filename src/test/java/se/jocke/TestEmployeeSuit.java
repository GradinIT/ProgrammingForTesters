package se.jocke;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)

@SelectPackages({
        "se.jocke.employee"
})
@ExcludePackages({
        "se.jocke.misc_grupp3"
})
public class TestEmployeeSuit {
}