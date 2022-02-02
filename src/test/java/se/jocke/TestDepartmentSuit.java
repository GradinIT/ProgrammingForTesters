package se.jocke;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "se.jocke.department"
})
@SpringBootTest(classes = SpringTestConfig.class)
public class TestDepartmentSuit {
}

