package se.jocke.test.category;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Testing all methods for department and employee packages")
@SelectPackages({"se.jocke.department", "se.jocke.employee"})
public class TestSuite {
}